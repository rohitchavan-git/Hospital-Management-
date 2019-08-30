package com.rohit.chavan.hospital.hospital.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohit.chavan.hospital.hospital.entity.Doctor;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {

}


