package org.example.java8;

import org.example.entity.Employee;
import org.example.entity.Person;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Streams2 {
    public static void main(String[] args) {
        //Find the longest string in a list of strings using Java streams:
        List<String> str = Arrays
                .asList("apple", "banana", "cherry", "date", "grapefruit");
        Optional<String> res = str.stream()
                .max(Comparator.comparingInt(String::length));
        System.out.println(res);

        //Calculate the average age of a list of Employee objects using Java streams:
        List<Person> persons = Arrays.asList(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 35)
        );
        Double res2 = persons.stream()
                .collect(Collectors.averagingInt(Person::getAge));
        System.out.println(res2);

        // Check if a list of integers contains a prime number using Java streams:
        List<Integer> numbers = Arrays.asList(2, 4, 6, 8, 10, 11, 12, 13, 14, 15);

        boolean hasprime = numbers.stream()
                .anyMatch(Streams2::isPrime);
        if (hasprime) {
            System.out.println("List contain at least 1 prime number");
        } else {
            System.out.println("List does not contain prime number");
        }

        //Remove duplicates from a list while preserving the order using Java streams:
        List<Integer> numbersWithDuplicates = Arrays.asList(1, 2,3,2,3, 3, 2, 4, 1, 5, 6, 5);

        List<Integer> res4=numbersWithDuplicates.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println(res4);

        //Find the kth smallest element in an array using Java streams:
        int[] arr={4,2,7,1,5,3,6};
        int k=3;
        int ans=Arrays.stream(arr)
                .sorted()
                .skip(k-1)
                .findFirst()
                .orElse(-1);
        System.out.println(ans);

        //K th largest
        List<Integer> numberas = Arrays.asList(10, 5, 20, 8, 25, 18);
        Optional<Integer> ans2=numberas.stream()
                .sorted(Comparator.reverseOrder())
                .skip(k-1)
                .findFirst();






    }

    private static boolean isPrime(int number) {
        if (number <= 1) return false;
        if (number == 2) return true;
        if (number % 2 == 0) return false;

        for (int i = 3; i <= Math.sqrt(number); i += 2) {
            if (number % i == 0) return false;
        }
        return true;
    }
}
