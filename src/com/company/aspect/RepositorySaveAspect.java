package com.company.aspect;

import com.company.models.Store;
import com.company.utils.FileUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Aspect
@Component
public class RepositorySaveAspect {

    @Autowired
    FileUtil fileUtil;

    @AfterReturning(value = "execution(public * com.company.repository.*Dao.save*(..))", returning = "result")
    public void aroundSave(Object result) throws Throwable{
        fileUtil.save(result);
    }

}
