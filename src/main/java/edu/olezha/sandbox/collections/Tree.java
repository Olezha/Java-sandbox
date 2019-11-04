package edu.olezha.sandbox.collections;

import java.util.Comparator;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import lombok.Data;

public class Tree {

    public static void main(String[] args) throws IllegalAccessException {
        TreeSet<Employee> employees = new TreeSet<>(new Tree.EmployeeComparator());
        employees.add(new Tree.Employee("Oleh", 1500));
        employees.add(new Tree.Employee("Michael", 1600));
        employees.add(new Tree.Employee("Paul", 1400));
        employees.add(new Tree.Employee("Mark", 1550));
        employees.add(new Tree.Employee("Gregory", 1800));
        employees.add(new Tree.Employee("Kevin", 1350));
        employees.add(new Tree.Employee("Henry", 1450));
        employees.add(new Tree.Employee("Terry", 1650));
        employees.add(new Tree.Employee("Gerald", 1700));
        employees.add(new Tree.Employee("Eugene", 1300));
        
        System.out.println(employees + "\n");
        
        printNode(FieldUtils.readField(FieldUtils.readField(employees, "m", true), "root", true), 0, "root");
    }
    
    private static void printNode(Object node, int offset, String label) throws IllegalAccessException {
        System.out.println(StringUtils.repeat(" ", offset)
                + FieldUtils.readField(node, "key", true)
                + " " + label + " "
                + ((Boolean)FieldUtils.readField(node, "color", true) ? "black" : "red"));
        
        Object left = FieldUtils.readField(node, "left", true);
        if (left != null)
            printNode(left, offset + 4, "left");
        
        Object right = FieldUtils.readField(node, "right", true);
        if (right != null)
            printNode(right, offset + 4, "right");
    }

    @Data
    static class Employee {
        private String name;
        private int salary;
        
        public Employee(String name, int salary) {
            this.name = name;
            this.salary = salary;
        }
    }

    static class EmployeeComparator implements Comparator<Employee> {
        
        @Override
        public int compare(Employee o1, Employee o2) {
            return o1.getSalary() - o2.getSalary();
        }
        
    }

}
