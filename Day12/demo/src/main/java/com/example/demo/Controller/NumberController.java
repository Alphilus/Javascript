package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
public class NumberController {
    private final List<Integer> numbers = List.of(10, 5, 3, 12, 6, 7, 5, 3);

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("numbers", numbers);
        return "index";
    }

    @GetMapping("/evenNumbers.html")
    public String getEvenNumbers(Model model) {
        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .toList();
        model.addAttribute("evenNumbers", evenNumbers);
        return "/evenNumbers";
    }
    @GetMapping("/greaterThan5.html")
    public String getGreaterThan5(Model model) {
        List<Integer> greaterThan5 = numbers.stream()
                .filter(n -> n > 5)
                .toList();
        model.addAttribute("greaterThan5", greaterThan5);
        return "/greaterThan5";
    }
    @GetMapping("/maxNumber.html")
    public String getMaxNumber(Model model){
        int maxNumber = numbers.stream()
                .max(Integer::compareTo)
                .orElseThrow();
        model.addAttribute("maxNumber" , maxNumber);
        return "/maxNumber";
    }
    @GetMapping("/minNumber.html")
    public String getMinNumber(Model model){
        int minNumber = numbers.stream()
                .min(Integer::compareTo)
                .orElseThrow();
        model.addAttribute("minNumber" , minNumber);
        return "/minNumber";
    }
    @GetMapping("/sum.html")
    public String getSum(Model model){
        int sum = numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
        model.addAttribute("sum" , sum);
        return "/sum";
    }
    @GetMapping("/distinctNumbers.html")
    public String getDistinctNumbers(Model model){
        List<Integer> distinctNumbers = numbers.stream()
                .distinct()
                .toList();
        model.addAttribute("distinctNumbers" , distinctNumbers);
        return "/distinctNumbers";
    }
    @GetMapping("/first5Numbers.html")
    public String getFirst5Numbers(Model model){
        List<Integer> first5Numbers = numbers.stream()
                .limit(5)
                .toList();
        model.addAttribute("first5Numbers" , first5Numbers);
        return "/first5Numbers";
    }
    @GetMapping("/list3to5Numbers.html")
    public String getList3to5Numbers(Model model) {
        List<Integer> list3to5Numbers = numbers.stream()
                .skip(2)
                .limit(3)
                .toList();
        model.addAttribute("list3to5Numbers" , list3to5Numbers);
        return "/list3to5Numbers";
    }
    @GetMapping("/firstGreaterThan5.html")
    public String getFirstGreaterThan5(Model model) {
        int firstGreaterThan5 = numbers.stream()
                .filter(n -> n > 5)
                .findFirst()
                .orElseThrow();
        model.addAttribute("firstGreaterThan5" , firstGreaterThan5);
        return "/firstGreaterThan5";
    }
}
