package com.company.checker;

import com.company.repository.CustomerDao;
import com.company.repository.OrderDao;
import com.company.repository.ProductDao;
import com.company.repository.StoreDao;
import com.company.thread.CheckThread;
import com.company.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class EntityChecker {

    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    FileUtil fileUtil;

    @Scheduled(initialDelay = 5000, fixedDelay = 1000 * 60)
    public void checkEntities(){
        Arrays.stream(fileUtil.getFolders()).forEach(folder -> {
            CheckThread thread = applicationContext.getBean(CheckThread.class);
            thread.setFolderPath(folder);
            thread.start();
        });
    }

}
