package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.response.PageResponse;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/people")
    public String getPeople(Model model,
                            @RequestParam(required = false, defaultValue = "1") int page,
                            @RequestParam(required = false, defaultValue = "10") int pageSize) {
        PageResponse<Person> pageData = personService.getAllPeople(page, pageSize);
        model.addAttribute("pageData", pageData);
        return "people"; // Assuming "people" is the name of your view/template
    }


    @GetMapping("/people/fullName")
    public ResponseEntity<List<Person>> getAllPeopleFullName(){
        List<Person> people = personService.getAllPeopleFullName();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @GetMapping("/people/fullName/reversed")
    public ResponseEntity<List<Person>> getAllPeopleFullNameReversed(){
        List<Person> people = personService.getAllPeopleFullNameReversed();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<String>> getSortedJobs(){
        List<String> jobs = personService.getSortedJobs();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping("/cities")
    public ResponseEntity<List<String>> getSortedCities(){
        List<String> cities = personService.getSortedCities();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping("names/female")
    public ResponseEntity<List<String>> getFemaleName(){
        List<String> femaleNames = personService.getFemaleName();
        return new ResponseEntity<>(femaleNames, HttpStatus.OK);
    }

    @GetMapping("salary/highest")
    public ResponseEntity<Person> getHighestEarner(){
        Person highestEarner = personService.getHighestEarner();
        return new ResponseEntity<>(highestEarner, HttpStatus.OK);
    }

    @GetMapping("/people/bornAfter1990")
    public ResponseEntity<List<Person>> getBornAfter1990(){
        List<Person> bornAfter1990 = personService.getBornAfter1990();
        return new ResponseEntity<>(bornAfter1990, HttpStatus.OK);
    }

    @GetMapping("/salary/average")
    public ResponseEntity<Double> getAverageSalary(){
        double averageSalary = personService.getAverageSalary();
        return new ResponseEntity<>(averageSalary, HttpStatus.OK);
    }

    @GetMapping("/people/averageAge")
    public ResponseEntity<Double> getAverageAge(){
        double averageAge = personService.getAverageAge();
        return new ResponseEntity<>(averageAge, HttpStatus.OK);
    }

    @GetMapping("/names/{keyword}")
    public ResponseEntity<List<Person>> getNamesContains(@PathVariable String keyword){
        List<Person> people = personService.getNamesContains(keyword);
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @GetMapping("/people/sortedByAgeForMale")
    public ResponseEntity<List<Person>> getSortedByAgeForMale(){
        List<Person> people = personService.getSortedByAgeForMale();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @GetMapping("/names/longestName")
    public ResponseEntity<Person> getLongestName(){
        Person longestName = personService.getLongestName();
        return new ResponseEntity<>(longestName, HttpStatus.OK);
    }

    @GetMapping("/salary/aboveAverage")
    public ResponseEntity<List<Person>> getAboveAverageSalary(){
        List<Person> people = personService.getAboveAverageSalary();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @GetMapping("/groups/cities")
    public ResponseEntity<Map<String, List<Person>>> getGroupPeopleByCity(){
        Map<String, List<Person>> groupPeopleByCity = personService.getGroupPeopleByCity();
        return new ResponseEntity<>(groupPeopleByCity, HttpStatus.OK);
    }

    @GetMapping("/groups/jobsCount")
    public ResponseEntity<Map<String, Integer>> getGroupJobsCount(){
        Map<String, Integer> groupJobsCount = personService.getGroupJobByCount();
        return new ResponseEntity<>(groupJobsCount, HttpStatus.OK);
    }
}
