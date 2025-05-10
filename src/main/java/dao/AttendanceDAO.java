package com.attendance.dao;

import com.attendance.model.Attendance;
import com.attendance.model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Date;
import java.util.List;

public class AttendanceDAO {
    public void markAttendance(Employee emp, Date date, boolean present) {
        Transaction tx = null;
        Attendance attendance = new Attendance(emp, date, present);
        try (Session session = new Configuration().configure().buildSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(attendance);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public List<Attendance> getAttendanceByEmployee(Employee emp) {
        try (Session session = new Configuration().configure().buildSessionFactory().openSession()) {
            return session.createQuery("from Attendance where employee = :emp", Attendance.class)
                    .setParameter("emp", emp)
                    .list();
        }
    }
}
