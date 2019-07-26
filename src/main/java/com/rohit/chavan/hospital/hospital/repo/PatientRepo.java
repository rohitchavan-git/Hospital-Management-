package com.rohit.chavan.hospital.hospital.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohit.chavan.hospital.hospital.entity.Patient;

public interface PatientRepo extends JpaRepository<Patient, Long> {

}
