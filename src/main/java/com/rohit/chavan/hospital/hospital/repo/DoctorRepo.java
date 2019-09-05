package com.rohit.chavan.hospital.hospital.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rohit.chavan.hospital.hospital.entity.Doctor;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {
	
	@Query("select u from Doctor u where u.id in (:ids)")
	public List<Doctor> getAllDoctor(@Param("ids") List<Long> ids); 

}


