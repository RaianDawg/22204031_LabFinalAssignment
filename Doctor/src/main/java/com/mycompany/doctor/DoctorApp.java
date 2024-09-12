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

public class DoctorApp {
    private List<Doctor> doctors;
    private List<Patient> patients;
    private List<Appointment> appointments;

    public DoctorApp() {
        doctors = new ArrayList<>();
        patients = new ArrayList<>();
        appointments = new ArrayList<>();
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
        saveAppointments();
    }

    private void loadDoctors() {
        try (BufferedReader reader = new BufferedReader(new FileReader("doctors.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                doctors.add(new Doctor(parts[0], parts[1]));
            }
        } catch (IOException e) {
            System.out.println("Error loading doctors: " + e.getMessage());
        }
    }

    private void saveDoctors() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("doctors.txt"))) {
            for (Doctor doctor : doctors) {
                writer.write(doctor.getName() + "," + doctor.getSpecialization());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving doctors: " + e.getMessage());
        }
    }

    private void loadPatients() {
        try (BufferedReader reader = new BufferedReader(new FileReader("patients.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                patients.add(new Patient(parts[0], parts[1]));
            }
        } catch (IOException e) {
            System.out.println("Error loading patients: " + e.getMessage());
        }
    }

    private void savePatients() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("patients.txt"))) {
            for (Patient patient : patients) {
                writer.write(patient.getName() + "," + patient.getContactNumber());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving patients: " + e.getMessage());
        }
    }

    private void loadAppointments() {
        try (BufferedReader reader = new BufferedReader(new FileReader("appointments.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Doctor doctor = findDoctorByName(parts[0]);
                Patient patient = findPatientByName(parts[1]);
                if (doctor != null && patient != null) {
                    appointments.add(new Appointment(doctor, patient, parts[2], parts[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading appointments: " + e.getMessage());
        }
    }

    private void saveAppointments() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("appointments.txt"))) {
            for (Appointment appointment : appointments) {
                writer.write(appointment.getDoctor().getName() + "," +
                        appointment.getPatient().getName() + "," +
                        appointment.getDate() + "," +
                        appointment.getTime());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving appointments: "+e.getMessage());
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
        DoctorApp app = new DoctorApp();

        app.addDoctor(new Doctor("Dr. Alice", "Dermatologist"));
        app.addPatient(new Patient("John Doe", "1234567890"));
        app.bookAppointment(new Appointment(
            app.findDoctorByName("Dr. Alice"),
            app.findPatientByName("John Doe"),
            "2024-09-13",
            "10:00 AM"
        )
        );

        for (Doctor doctor : app.doctors) {
            System.out.println("Doctor: " + doctor.getName() + ", Specialization: " + doctor.getSpecialization());
        }

        for (Appointment appointment : app.appointments) {
            System.out.println("Doctor: " + appointment.getDoctor().getName()+", Patient: "+appointment.getPatient().getName()+", Date: "+appointment.getDate()+", Time: "+appointment.getTime());
        }
    }
}