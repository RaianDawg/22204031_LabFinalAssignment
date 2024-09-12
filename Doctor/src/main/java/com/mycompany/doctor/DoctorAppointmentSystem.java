/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.doctor;

/**
 *
 * @author homed
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorAppointmentSystem {
    private List<Doctor>doctors;
    private List<Patient>patients;
    private List<Appointment> appointments;
    private static final String DOCTORS_FILE = "doctors.txt";
    private static final String PATIENTS_FILE = "patients.txt";
    private static final String APPOINTMENTS_FILE = "appointments.txt";
    
public DoctorAppointmentSystem() {
        this.doctors = new ArrayList<>();
        this.patients = new ArrayList<>();
        this.appointments = new ArrayList<>();
        loadDoctors();
        loadPatients();
        loadAppointments();
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
        saveDoctors();
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
        savePatients();
    }

    public void bookAppointment(Appointment appointment) {
        appointments.add(appointment);
        appointment.getDoctor().addAppointment(appointment);
        saveAppointments();
    }

    public void displayDoctors() {
        for (Doctor doctor : doctors) {
            System.out.println("Doctor: " + doctor.getName() + ", Specialization: " + doctor.getSpecialization());
        }
    }

    public void displayAppointments() {
        for (Appointment appointment : appointments) {
            System.out.println("Doctor: " + appointment.getDoctor().getName() + ", Patient: " + appointment.getPatient().getName()+ ", Date: "+appointment.getDate() + ", Time: " +appointment.getTime());
        }
    }

    public void displayDoctorAvailability() {
        for (Doctor doctor : doctors) {
            doctor.displayAvailability();
        }
    }

    private void loadDoctors() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DOCTORS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 2) {
                    System.out.println("Invalid line in doctors file: " + line);
                    continue;
                }
                String name = parts[0].trim();
                String specialization = parts[1].trim();
                Doctor doctor = new Doctor(name, specialization);
                doctors.add(doctor);
            }
        } catch (IOException e) {
            System.out.println("Error loading doctors: " + e.getMessage());
        }
    }

    private void saveDoctors() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DOCTORS_FILE))) {
            for (Doctor doctor : doctors) {
                writer.write(doctor.getName() + "," + doctor.getSpecialization());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving doctors: " + e.getMessage());
        }
    }

    private void loadPatients() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PATIENTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 2) {
                    System.out.println("Invalid line in patients file: " + line);
                    continue;
                }
                String name = parts[0].trim();
                String contactNumber = parts[1].trim();
                Patient patient = new Patient(name, contactNumber);
                patients.add(patient);
            }
        } catch (IOException e) {
            System.out.println("Error loading patients: " + e.getMessage());
        }
    }

    private void savePatients() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATIENTS_FILE))) {
            for (Patient patient : patients) {
                writer.write(patient.getName() + "," + patient.getContactNumber());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving patients: " + e.getMessage());
        }
    }

    private void loadAppointments() {
        try (BufferedReader reader = new BufferedReader(new FileReader(APPOINTMENTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 4) {
                    System.out.println("Invalid line in appointments file: " + line);
                    continue;
                }
                Doctor doctor = findDoctorByName(parts[0].trim());
                Patient patient = findPatientByName(parts[1].trim());
                String date = parts[2].trim();
                String time = parts[3].trim();
                if (doctor != null && patient != null) {
                    Appointment appointment = new Appointment(doctor, patient, date, time);
                    appointments.add(appointment);
                    doctor.addAppointment(appointment);
                } else {
                    System.out.println("Invalid doctor or patient in appointment file: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading appointments: " + e.getMessage());
        }
    }

    private void saveAppointments() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(APPOINTMENTS_FILE))) {
            for (Appointment appointment : appointments) {
                writer.write(appointment.getDoctor().getName() + "," +
                        appointment.getPatient().getName() + "," +
                        appointment.getDate() + "," +
                        appointment.getTime());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving appointments: " + e.getMessage());
        }
    }

    private Doctor findDoctorByName(String name) {
        for (Doctor doctor : doctors) {
            if (doctor.getName().equals(name)) {
                return doctor;
            }
        }
        return null;
    }

    private Patient findPatientByName(String name) {
        for (Patient patient : patients) {
            if (patient.getName().equals(name)) {
                return patient;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        DoctorAppointmentSystem system = new DoctorAppointmentSystem();

        // Example usage:
        system.displayDoctors();
        system.displayAppointments();
        system.displayDoctorAvailability();
    }
}
