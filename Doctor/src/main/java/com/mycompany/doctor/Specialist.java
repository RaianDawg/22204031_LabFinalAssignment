/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.doctor;

/**
 *
 * @author homed
 */
public class Specialist extends Doctor {
    public Specialist(String name, String specialization) {
        super(name, specialization);
    }

    @Override
    public void displayAvailability() {
        System.out.println("Doctor " + getName() + " requires an appointment confirmation first.");
    }
}
