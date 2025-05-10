package com.attendance.employeeattendancetrack;

import com.attendance.dao.AttendanceDAO;
import com.attendance.dao.EmployeeDAO;
import com.attendance.model.Employee;
import com.attendance.model.Attendance;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmployeeDAO employeeDAO = new EmployeeDAO();
        AttendanceDAO attendanceDAO = new AttendanceDAO();

        while (true) {
            System.out.println("\n--- Employee Attendance Tracker ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Mark Attendance");
            System.out.println("4. View Attendance by Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Exit");

            System.out.print("Choose: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sc.nextLine();
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter department: ");
                    String dept = sc.nextLine();
                    employeeDAO.addEmployee(new Employee(name, dept));
                    break;

                case 2:
                    List<Employee> emps = employeeDAO.getAllEmployees();
                    for (Employee e : emps) System.out.println(e);
                    break;

                case 3:
                    System.out.print("Enter Employee ID: ");
                    int empId = sc.nextInt();
                    sc.nextLine(); // consume newline

                    System.out.print("Mark attendance (P for Present, A for Absent): ");
                    String mark = sc.nextLine().trim().toUpperCase();

                    boolean present;
                    if (mark.equals("P")) {
                        present = true;
                    } else if (mark.equals("A")) {
                        present = false;
                    } else {
                        System.out.println("Invalid input. Use 'P' or 'A'.");
                        break;
                    }

                    Employee emp = employeeDAO.getEmployeeById(empId);
                    if (emp != null) {
                        attendanceDAO.markAttendance(emp, new Date(), present);
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;


                case 4:
                    System.out.print("Enter Employee ID: ");
                    int empId2 = sc.nextInt();
                    Employee emp2 = employeeDAO.getEmployeeById(empId2);
                    if (emp2 != null) {
                        List<Attendance> records = attendanceDAO.getAttendanceByEmployee(emp2);
                        for (Attendance a : records)
                            System.out.println(a.getDate() + " - " + (a.isPresent() ? "P" : "A"));
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;

                case 5:
                	 System.out.print("Enter Employee ID to delete: ");
                     int delId = sc.nextInt();
                     employeeDAO.deleteEmployeeById(delId);
                     break;
                    
                case 6:
                    System.exit(0);


                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
