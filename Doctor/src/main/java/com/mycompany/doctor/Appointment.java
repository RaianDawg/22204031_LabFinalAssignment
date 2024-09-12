/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.doctor;

/**
 *
 * @author homed
 */
public class Appointment {  
   private Doctor doctor;  
   private Patient patient;  
   private String date;  
   private String time;  
  
   public Appointment(Doctor doctor, Patient patient, String date, String time) {  
      this.doctor = doctor;  
      this.patient = patient;  
      this.date = date;  
      this.time = time;  
   }  
  
   public Doctor getDoctor() {  
      return doctor;  
   }  
  
   public Patient getPatient() {  
      return patient;  
   }  
  
   public String getDate() {  
      return date;  
   }  
  
   public String getTime() {  
      return time;  
   }  
}

