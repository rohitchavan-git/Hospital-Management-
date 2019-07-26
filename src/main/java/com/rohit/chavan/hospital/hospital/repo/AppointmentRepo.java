package com.rohit.chavan.hospital.hospital.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohit.chavan.hospital.hospital.entity.Appointment;

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

}
