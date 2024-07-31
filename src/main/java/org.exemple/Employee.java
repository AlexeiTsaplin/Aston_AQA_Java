package org.exemple;

public class Employee {
    private String full_name;
    private String position;
    private String e_mail;
    private String phone_number;
    private int salary;
    private int age;

    public Employee(String full_name, String position, String e_mail, String phone_number, int salary, int age) {
        this.full_name = full_name;
        this.position = position;
        this.e_mail = e_mail;
        this.phone_number = phone_number;
        this.salary = salary;
        this.age = age;
    }

    public void infoAboutEmployee() {
        System.out.println("ФИО: " + full_name);
        System.out.println("должность: " + position);
        System.out.println("email: " + e_mail);
        System.out.println("телефон: " + phone_number);
        System.out.println("зарплата: " + salary);
        System.out.println("возраст: " + age);
        System.out.println();
    }

    public static void main(String[] args) {
        Employee[] employees = new Employee[5];
        employees[0] = new Employee("Ivanov Ivan Ivanovich", "Engineer", "Iviviv@yahoo.com", "89997771122", 45000, 33);
        employees[1] = new Employee("Petrov Petr Petrovich", "Driver", "Ptptpt@yandex.ru", "89996665544", 35000, 27);
        employees[2] = new Employee("Egorov Egor Egorovich", "Security", "Egegeg@mailbox.com", "89998882244", 40000, 50);
        employees[3] = new Employee("Sedova Vera Egorovna", "Bookkeeper", "Sdveeg@mail.ru", "89995556677", 50000, 22);
        employees[4] = new Employee("Perova Irina Vadimovna", "CEO", "PeirVa@yahoo.com", "89994440011", 120000, 42);

        for (Employee employee : employees) {
            employee.infoAboutEmployee();
        }
    }
}