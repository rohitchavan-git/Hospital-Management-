package com.rohit.chavan.hospital.hospital.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rohit.chavan.hospital.hospital.entity.Appointment;

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
	@Query(value = "select * from appointment where doctors_id=:docid",nativeQuery = true)
	public Appointment getAppoint(@Param("docid") Long id);

}
