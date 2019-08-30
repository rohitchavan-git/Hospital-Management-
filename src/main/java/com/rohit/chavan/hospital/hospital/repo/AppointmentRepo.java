package com.rohit.chavan.hospital.hospital.repo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rohit.chavan.hospital.hospital.entity.Appointment;

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
	@Query(value = "select * from appointment where doctors_id=:docid", nativeQuery = true)
	public Appointment getAppoint(@Param("docid") Long id);

	@Query("select new Map(u.reason as resion ,p.fname as patientname,d.fname as doctorname) from Appointment as u inner join Patient as p on u.patients=p.id   inner join Doctor as d on u.doctors=d.id ")
	public List<Map<String, Object>> getAppointmentPatientAndDoctorInfo();
}
