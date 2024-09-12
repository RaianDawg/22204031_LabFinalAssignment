/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.doctor;

/**
 *
 * @author homed
 */
import java.util.ArrayList;

public class Doctor {
    private String name;
    private String specialization;
    private ArrayList<Appointment> appointments;
    
    public Doctor(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
        this.appointments = new ArrayList<>();
        
    }
    
    public String getName() {
        return name;
    }
    public String getSpecialization() {
        return specialization;
    }
    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }
    public void displayAvailability() {
        System.out.println("Doctor" + name + "is available for appointments.");
    }
    
    
    public static void main(String[] args) {
        Doctor doctor = new Doctor("John doe", "Cardiologist");
        doctor.displayAvailability();
    }
    
}
      
