package com.attendance.dao;

import com.attendance.model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class EmployeeDAO {
    public void addEmployee(Employee emp) {
        Transaction tx = null;
        try (Session session = new Configuration().configure().buildSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(emp);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public List<Employee> getAllEmployees() {
        try (Session session = new Configuration().configure().buildSessionFactory().openSession()) {
            return session.createQuery("from Employee", Employee.class).list();
        }
    }

    public Employee getEmployeeById(int id) {
        try (Session session = new Configuration().configure().buildSessionFactory().openSession()) {
            return session.get(Employee.class, id);
        }
    }
    
    
    
    
    
    
    public void deleteEmployeeById(int id) {
        Transaction tx = null;
        try (Session session = new Configuration().configure().buildSessionFactory().openSession()) {
            tx = session.beginTransaction();

            Employee emp = session.get(Employee.class, id);
            if (emp != null) {
                // Delete all attendance records first
                session.createQuery("DELETE FROM Attendance WHERE employee = :emp")
                       .setParameter("emp", emp)
                       .executeUpdate();

                // Then delete the employee
                session.delete(emp);
                tx.commit();
                System.out.println("Employee and related attendance deleted.");
            } else {
                System.out.println("Employee not found.");
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    
    
    
}
