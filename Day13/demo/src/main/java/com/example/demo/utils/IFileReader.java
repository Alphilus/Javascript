package com.example.demo.utils;

import com.example.demo.model.Child;
import com.example.demo.model.Person;

import java.util.List;

public interface IFileReader {
    List<Person> readFiles(String path);
    List<Child> readChildren(String path);
}
