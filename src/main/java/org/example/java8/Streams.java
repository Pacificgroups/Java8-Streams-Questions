package org.example.java8;

import org.example.entity.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class Streams {
    public static void main(String[] args) {

        List<Employee> employeeList=new ArrayList<>();

        employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
        employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
        employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
        employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
        employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
        employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
        employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
        employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
        employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
        employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
        employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
        employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
        employeeList.add(new Employee(233, "Jyoti Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
        employeeList.add(new Employee(244, "Nicolas Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
        employeeList.add(new Employee(255, "Ali Bag", 23, "Male", "Infrastructure", 2018, 12700.0));
        employeeList.add(new Employee(266, "Riya Singh", 26, "Female", "Product Development", 2015, 28900.0));
        employeeList.add(new Employee(277, "Anooj Chattier", 31, "Male", "Product Development", 2012, 35700.0));

        // How many male and female employees are there in the organization
        Map<String,Long> res=employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getGender,Collectors.counting()));
        System.out.println(res);

        //Print the name of all departments in the organization
        employeeList.stream().
                map(Employee::getDepartment)
                .distinct()
                .forEach(System.out::println);

        //What is the average age of male and female employees?
        Map<String,Double> res3=employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingInt(Employee::getAge)));
        System.out.println(res3);

        //Get the details of highest paid employee in the organization?
        Optional<Employee> res4=employeeList.stream()
                .collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)));
        System.out.println(res4);

        //Get the names of all employees who have joined after 2015?
                employeeList.stream()
                .filter(employee -> employee.getYearOfJoining()>2015)
                .map(Employee::getName)
                .forEach(System.out::println);

        //Count the number of employees in each department?
       Map<String,Long> res6= employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting()));
        System.out.println(res6);

       //What is the average salary of each department?
        Map<String,Double> res7=employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(res7);

       //Get the details of youngest male employee in the product development department?
       Optional<Employee> res8=employeeList.stream()
               .filter(employee -> employee.getDepartment().equals("Product Development") && employee.getGender().equals("Male"))
               .min(Comparator.comparingInt(Employee::getAge));
        System.out.println(res8);


       //Who has the most working experience in the organization?
        Optional<Employee> res9= employeeList.stream().min(Comparator.comparingInt(Employee::getYearOfJoining));
        System.out.println(res9);

       //How many male and female employees are there in the sales and marketing team?
        Map<String,Long> res10=employeeList.stream().
                filter(employee -> employee.getDepartment()=="Sales And Marketing")
                .collect(Collectors.groupingBy(Employee::getGender,Collectors.counting()));
        System.out.println(res10);

       // What is the average salary of male and female employees?
        Map<String,Double> res11=employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(res11);

        //List down the names of all employees in each department?
        Map<String, List<Employee>> res12= employeeList.stream()
               .collect(Collectors.groupingBy(Employee::getDepartment));

        res12.forEach((dept,employees)-> {
            System.out.println("---"+dept+"---");
            employees.forEach(e-> System.out.println(e.getName()));
        });

       //What is the average salary and total salary of the whole organization?
        DoubleSummaryStatistics est=employeeList.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println("---Average Salary---"+est.getAverage());
        System.out.println("---Total Salary---"+est.getSum());

       //Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years.
        Map<Boolean,List<Employee>> partitioned=employeeList.stream()
                .collect(Collectors.partitioningBy(e->e.getAge()>25));
        System.out.println("---Employees aged Greater than 25---");
        partitioned.get(true).forEach(e-> System.out.println(e.getName()));
        System.out.println("---Employees less than or equal to 25---");
        partitioned.get(false).forEach(e-> System.out.println(e.getName()));

       //Who is the oldest employee in the organization? What is his age and which department he belongs to?
       Optional<Employee> emp=employeeList.stream()
               .collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getAge)));
        System.out.println(emp);

    }
}
