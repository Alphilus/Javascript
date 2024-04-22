package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.response.PageResponse;

import java.util.List;
import java.util.Map;

public interface PersonService{
    List<Person> getAllPeople();

    List<Person> getAllPeopleFullName();

    List<Person> getAllPeopleFullNameReversed();

    List<String> getSortedJobs();

    List<String> getSortedCities();

    List<String> getFemaleName();

    Person getHighestEarner();

    List<Person> getBornAfter1990();

    double getAverageSalary();

    double getAverageAge();

    List<Person> getNamesContains(String keyword);

    List<Person> getSortedByAgeForMale();

    Person getLongestName();

    List<Person> getAboveAverageSalary();

    Map<String, List<Person>> getGroupPeopleByCity();

    Map<String, Integer> getGroupJobByCount();

    PageResponse<Person> getAllPeople(int page, int pageSize);
}
