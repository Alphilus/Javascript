package com.example.demo.database;

import com.example.demo.model.Person;
import com.example.demo.utils.IFileReader;
import com.example.demo.utils.JsonFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitDB implements CommandLineRunner {
    @Autowired
    private IFileReader fileReader;


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Khởi tạo dữ liệu");
        PersonDB.personList = fileReader.readFiles("Person.json");

        // In ra danh sách các person
        for (Person person : PersonDB.personList) {
            System.out.println("Person: " + person);
        }

        // In ra số lượng person
        System.out.println("Số lượng person: " + PersonDB.personList.size());

        //Child
        ChildDB.childList = fileReader.readChildren("child.json");

        ChildDB.childList.forEach(System.out::println);

        System.out.println("Số lượng child: " + ChildDB.childList.size());
    }
}
