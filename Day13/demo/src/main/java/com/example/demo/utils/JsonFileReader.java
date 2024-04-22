package com.example.demo.utils;

import com.example.demo.model.Child;
import com.example.demo.model.Person;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;
import java.util.List;
@Component
public class JsonFileReader implements IFileReader{
    @Override
    public List<Person> readFiles(String path) {
        System.out.println("Đọc file JSON");
        ObjectMapper mapper = new ObjectMapper();

        //TODO
        mapper.registerModule(new JavaTimeModule());

        try {
            return mapper.readValue(Paths.get(path).toFile(), new TypeReference<List<Person>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi khi đọc file JSON: " + e.getMessage());
        }
        return List.of();
    }

    @Override
    public List<Child> readChildren(String path) {
        System.out.println("Đọc file JSON");
        ObjectMapper mapper = new ObjectMapper();

        //TODO
        mapper.registerModule(new JavaTimeModule());

        try {
            return mapper.readValue(Paths.get(path).toFile(), new TypeReference<List<Child>>() {});
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Lỗi khi đọc file JSON: " + e.getMessage());
        }
        return List.of();
    }
}
