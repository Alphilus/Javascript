package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		List<Integer> numbers = List.of(10, 5, 3, 12, 6, 7, 5, 3);

		//1. Duyệt numbers
		numbers.forEach(System.out::println);

		//2. Tìm các giá trị chẵn trong list
		List<Integer> evenNumbers = numbers.stream()
				.filter(n -> n % 2 ==0)
				.toList();
		System.out.println("Các giá trị chẵn: " + evenNumbers);

		//3. Tìm các giá trị > 5 trong list
		List<Integer> greaterThan5 = numbers.stream()
				.filter(n -> n > 5)
				.toList();
		System.out.println("Các giá trị > 5: " + greaterThan5);

		//4. Tìm giá trị max trong list
		int maxNumber = numbers.stream()
				.max(Integer::compareTo)
				.orElseThrow();
		System.out.println("Giá trị max: " + maxNumber);

		//5. Tìm giá trị min trong list
		int minNumbers = numbers.stream()
				.min(Integer::compareTo)
				.orElseThrow();
		System.out.println("Giá trị min: " + minNumbers);

		// 6. Tính tổng các phần tử của mảng
		int sum = numbers.stream()
				.mapToInt(Integer::intValue)
				.sum();
		System.out.println("Giá trị sum: " + sum);

		// 7. Lấy danh sách các phần tử không trùng nhau
		List<Integer> distinctNumbers = numbers.stream()
				.distinct()
                .toList();
        System.out.println("Danh sách các phần tử không trùng nhau: " + distinctNumbers);

		// 8. Lấy 5 phần tử đầu tiên trong mảng
		List<Integer> first5Numbers = numbers.stream()
				.limit(5)
				.toList();
		System.out.println("5 phần tử đầu tiên trong mảng: " + first5Numbers);

		// 9. Lấy phần tử từ thứ 3 -> thứ 5
		List<Integer> list3to5Numbers = numbers.stream()
				.skip(2)
                .limit(3)
                .toList();
        System.out.println("Phần tử từ thứ 3 -> thứ 5: " + list3to5Numbers);

		// 10. Lấy phần tử đầu tiên > 5
		int firstGreaterThan5 = numbers.stream()
				.filter(n -> n > 5)
                .findFirst()
                .orElseThrow();
        System.out.println("Phần tử đầu tiên > 5: " + firstGreaterThan5);

		// 11. Kiểm tra xem list có phải là list chẵn hay không
		boolean allEven = numbers.stream()
				.allMatch(n -> n % 2 ==0);
        System.out.println("Kiểm tra xem list có phải là list chẵn hay không: " + allEven);

		// 12. Kiểm tra xem list có phần tử > 10 hay không
		boolean anyGreaterThan10 = numbers.stream()
                .anyMatch(n -> n > 10);
        System.out.println("Kiểm tra xem list có phần tử > 10 hay không: " + anyGreaterThan10);

		// 13. Có bao nhiêu phần tử > 5
		long count5 = numbers.stream()
				.filter(n -> n > 5)
                .count();
        System.out.println("Có bao nhiêu phần tử > 5: " + count5);

		// 14. Nhân đôi các phần tử trong list và trả về list mới
		List<Integer> doubleNumber = numbers.stream()
				.map(n -> n * 2)
				.toList();
		System.out.println("Danh sách sau khi nhân đôi: " + doubleNumber);

		// 15. Kiểm tra xem list không chứa giá trị nào = 8 hay không
		boolean noNumber8 = numbers.stream()
				.noneMatch(n -> n == 8);
		System.out.println("Kiểm tra xem list không chứa giá trị nào = 8 hay không: " + noNumber8);
	}

}
