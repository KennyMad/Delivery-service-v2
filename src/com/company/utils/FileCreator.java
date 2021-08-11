package com.company.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.util.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;

@Component
public class FileCreator {

    @Autowired
    private ObjectMapper objectMapper;

    public void createFolder(String path){
        File file = new File(path);
        if (!file.exists()){
            file.mkdir();
        }
    }

    public void createJson(Object object, String path) throws Throwable {
        File file = new File(path + File.separator + "object.json");
        if (!file.exists()){
            file.createNewFile();
        }
        try (FileWriter writer = new FileWriter(file)){
            writer.write(objectMapper.writeValueAsString(object));
        }
    }

}
