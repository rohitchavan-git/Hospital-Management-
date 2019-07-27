package com.rohit.chavan.hospital.hospital;

import org.junit.Test;
import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
//
//import com.rohit.chavan.hospital.hospital.entity.Appointment;
//import com.rohit.chavan.hospital.hospital.entity.Doctor;
//import com.rohit.chavan.hospital.hospital.entity.Insurance;
//import com.rohit.chavan.hospital.hospital.entity.Patient;
//import com.rohit.chavan.hospital.hospital.repo.DoctorRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HospitalApplicationTests {

	// @Autowired
	// private DoctorRepo doctorRepo;
	@Test
	public void contextLoads() {
	}

//	@Test
//	public void testDotorRepo() {
//		
//		Doctor doctor = new Doctor();
//		doctor.setFname("rohit");
//		doctor.setLname("Chavan");
//		doctor.setSpeciality("mind");
//		ArrayList<Patient> arrayList = new ArrayList<Patient>();
//		Patient patient = new Patient();
//		arrayList.add(patient);
//		patient.setFname("rko");
//		patient.setLname("janson");
//		Insurance insurance = new Insurance();
//		insurance.setCopay(7848.555);
//		insurance.setProviderName("sbi bank");
//		patient.setInsurance(insurance);
//		
//		Patient patient1 = new Patient();
//		arrayList.add(patient1);
//		patient.setFname("urmila");
//		patient.setLname("kadam");
//		
//		patient.setInsurance(insurance);
//		ArrayList<Appointment> list = new ArrayList<Appointment>();
//		Appointment appointment = new Appointment();
//		appointment.setAppointmentTimestamp(new Timestamp(0));
//		appointment.setReason("maleria");
//		appointment.setStared(true);
//		appointment.setEnded(false);
//
//		list.add(appointment);
//		patient.setAppointments(list);
//		doctor.setPatients(arrayList);
//		doctorRepo.save(doctor);
//		
//	}

}
