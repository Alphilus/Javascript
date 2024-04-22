package com.example.demo.dao.impl;

import com.example.demo.dao.PersonDAO;
import com.example.demo.database.PersonDB;
import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class PersonDAOImpl implements PersonDAO {
    @Override
    public void printListPeople(List<Person> people) {
        people.forEach(System.out::println);
    }

    @Override
    public List<Person> getAll() {
        return PersonDB.personList;
    }

    @Override
    public List<Person> sortPeopleByFullName() {
        return PersonDB.personList.stream()
                .sorted(Comparator.comparing(Person::getFullName))
                .toList();
    }

    @Override
    public List<Person> sortPeopleByFullNameReversed() {
        return PersonDB.personList.stream()
                .sorted(Comparator.comparing(Person::getFullName).reversed())
                .toList();
    }

    @Override
    public List<String> findSortedJobs() {
        return PersonDB.personList.stream()
                .map(Person::getJob)
                .distinct()
                .sorted()
                .toList();
    }

    @Override
    public List<String> findSortedCities() {
        return PersonDB.personList.stream()
                .map(Person::getCity)
                .distinct()
                .sorted()
                .toList();
    }

    @Override
    public List<String> femaleNames() {
        return PersonDB.personList.stream()
                .filter(person -> person.getGender().equals("Female"))
                .map(Person::getFullName)
                .toList();
    }

    @Override
    public Person highestEarner() {
        return PersonDB.personList.stream()
                .max(Comparator.comparing(Person::getSalary))
                .orElse(null);
    }

    @Override
    public List<Person> bornAfter1990() {
        return PersonDB.personList.stream()
                .filter(person -> person.getBirthDay().isAfter(LocalDate.of(1990, 1, 1)))
                .toList();
    }

    @Override
    public double averageSalary() {
        return PersonDB.personList.stream()
                .mapToDouble(Person::getSalary)
                .average()
                .orElse(0);
    }

    @Override
    public double averageAge() {
        return PersonDB.personList.stream()
                .mapToDouble(Person::getAge)
                .average()
                .orElse(0);
    }

    @Override
    public List<Person> nameContains(String keyword) {
        return PersonDB.personList.stream()
                .filter(person -> person.getFullName().contains(keyword))
                .toList();
    }

    @Override
    public List<Person> sortedByAgeForMale() {
        return PersonDB.personList.stream()
                .filter(person -> person.getGender().equals("Male"))
                .sorted(Comparator.comparing(Person::getAge))
                .toList();
    }

    @Override
    public Person longestName() {
        return PersonDB.personList.stream()
                .max(Comparator.comparing(Person::getFullName))
                .orElse(null);
    }

    @Override
    public List<Person> aboveAverageSalary() {
        return PersonDB.personList.stream()
                .filter(person -> person.getSalary() > averageSalary())
                .toList();
    }

    @Override
    public Map<String, List<Person>> groupPeopleByCity() {
        return PersonDB.personList.stream()
                .collect(Collectors.groupingBy(Person::getCity));
    }

    @Override
    public Map<String, Integer> groupJobByCount() {
        return PersonDB.personList.stream()
                .collect(Collectors.groupingBy(Person::getJob, Collectors.summingInt(p -> 1)));
    }
}
