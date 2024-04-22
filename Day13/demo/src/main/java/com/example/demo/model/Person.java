package com.example.demo.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Person {
    int id;
    String fullName;
    String job;
    String gender;
    String city;
    int salary;
    LocalDate birthDay;
    List<Child> children;

    public double getAge() {
        return LocalDate.now().getYear() - birthDay.getYear();
    }

    public int getRandomChildNumber() {
        Random random = new Random();
        return random.nextInt(4);
    }
}
