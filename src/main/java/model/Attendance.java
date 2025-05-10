package com.attendance.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attendance> attendanceList;


    @Temporal(TemporalType.DATE)
    private Date date;
    
    


    private boolean present;

    // Constructors
    public Attendance() {}
    public Attendance(Employee employee, Date date, boolean present) {
        this.employee = employee;
        this.date = date;
        this.present = present;
    }

    // Getters and Setters
    public int getId() { return id; }
    public Employee getEmployee() { return employee; }
    public Date getDate() { return date; }
    public boolean isPresent() { return present; }
    

   


    public void setId(int id) { this.id = id; }
    public void setEmployee(Employee employee) { this.employee = employee; }
    public void setDate(Date date) { this.date = date; }
    public void setPresent(boolean present) { this.present = present; }
   
}


