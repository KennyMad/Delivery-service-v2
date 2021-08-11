package com.company.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class ObjectGenerator {

    @Autowired
    private ObjectMapper objectMapper;

    public Object generate(String path, Class<?> objectClass) throws Throwable {
        File file = new File(path + File.separator + "object.json");
        return objectMapper.readValue(file,objectClass);
    }

}
