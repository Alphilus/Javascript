package com.example.demo.service.impl;

import com.example.demo.dao.PersonDAO;
import com.example.demo.model.Person;
import com.example.demo.response.PageResponse;
import com.example.demo.response.PageResponseImpl;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonDAO personDAO;

    @Override
    public List<Person> getAllPeople() {
        return personDAO.getAll();
    }

    @Override
    public List<Person> getAllPeopleFullName() {
        return personDAO.sortPeopleByFullName();
    }

    @Override
    public List<Person> getAllPeopleFullNameReversed() {
        return personDAO.sortPeopleByFullNameReversed();
    }

    @Override
    public List<String> getSortedJobs() {
        return personDAO.findSortedJobs();
    }

    @Override
    public List<String> getSortedCities() {
        return personDAO.findSortedCities();
    }

    @Override
    public List<String> getFemaleName() {
        return personDAO.femaleNames();
    }

    @Override
    public Person getHighestEarner() {
        return personDAO.highestEarner();
    }

    @Override
    public List<Person> getBornAfter1990() {
        return personDAO.bornAfter1990();
    }

    @Override
    public double getAverageSalary() {
        return personDAO.averageSalary();
    }

    @Override
    public double getAverageAge() {
        return personDAO.averageAge();
    }

    @Override
    public List<Person> getNamesContains(String keyword) {
        return personDAO.nameContains(keyword);
    }

    @Override
    public List<Person> getSortedByAgeForMale() {
        return personDAO.sortedByAgeForMale();
    }

    @Override
    public Person getLongestName() {
        return personDAO.longestName();
    }

    @Override
    public List<Person> getAboveAverageSalary() {
        return personDAO.aboveAverageSalary();
    }

    @Override
    public Map<String, List<Person>> getGroupPeopleByCity() {
        return personDAO.groupPeopleByCity();
    }

    @Override
    public Map<String, Integer> getGroupJobByCount() {
        return personDAO.groupJobByCount();
    }

    @Override
    public PageResponse<Person> getAllPeople(int page, int pageSize) {
        return new PageResponseImpl<>(personDAO.getAll(), page, pageSize);
    }
}
