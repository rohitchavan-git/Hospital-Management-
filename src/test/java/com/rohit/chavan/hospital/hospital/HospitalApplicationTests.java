package com.rohit.chavan.hospital.hospital;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rohit.chavan.hospital.hospital.entity.Doctor;
import com.rohit.chavan.hospital.hospital.entity.Insurance;
import com.rohit.chavan.hospital.hospital.entity.Patient;
import com.rohit.chavan.hospital.hospital.repo.AppointmentRepo;
import com.rohit.chavan.hospital.hospital.repo.DoctorRepo;
import com.rohit.chavan.hospital.hospital.repo.PatientRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
/**
 * Testing class
 * 
 * @author Rohit chavan
 *
 */
public class HospitalApplicationTests {

	@Autowired
	private DoctorRepo doctorRepo;

	@Test
	public void contextLoads() {
	}

	/**
	 * Test Dotor Entity
	 * 
	 * @author Rohit chavan
	 */
	@Test
	public void addDotors() {

		Doctor doctor1 = new Doctor();
		doctor1.setFname("urmila");
		doctor1.setLname("kadam");
		doctor1.setSpeciality("Heart");

		Doctor doctor2 = new Doctor();
		doctor2.setFname("Amol");
		doctor2.setLname("Gade");
		doctor2.setSpeciality("mind");

		Doctor doctor3 = new Doctor();
		doctor3.setFname("krishna");
		doctor3.setLname("kale");
		doctor3.setSpeciality("Heart");

		Doctor doctor4 = new Doctor();
		doctor4.setFname("rohit");
		doctor4.setLname("chavan");
		doctor4.setSpeciality("psychiatric");
		ArrayList<Doctor> list = new ArrayList<Doctor>();
		list.add(doctor1);
		list.add(doctor2);
		list.add(doctor3);
		list.add(doctor4);
		doctorRepo.saveAll(list);
	}

	@Autowired
	private PatientRepo patientRepo;

	@Test
	public void addPatient() {

		ArrayList<Patient> patients = new ArrayList<Patient>();
		Patient patient1 = new Patient();
		patient1.setFname("rahul");
		patient1.setLname("chavan");
		Insurance insurance = new Insurance();
		insurance.setCopay(25874125.25);
		insurance.setProviderName("sbi bank");
		patient1.setInsurance(insurance);

		
		Patient patient2 = new Patient();
		patient2.setFname("rahini");
		patient2.setLname("chavan");
		patient2.setInsurance(insurance);
		

		Patient patient3 = new Patient();
		patient3.setFname("seema");
		patient3.setLname("chavan");
		patient3.setInsurance(insurance);
		
		
		patients.add(patient1);
		patients.add(patient2);
		patients.add(patient3);
		patientRepo.saveAll(patients);
	}
	
	@Test
	public void updatePatient() {
		Patient patient1 = new Patient();
		patient1.setId(7L);
		patient1.setFname("rohini");
		patient1.setLname("chavan");
		Insurance insurance = new Insurance();
		insurance.setCopay(25874125.25);
		insurance.setProviderName("sbi bank");
		patient1.setInsurance(insurance);
		patient1.setPhone("8574857485");
		patientRepo.save(patient1);
	
	}
	
}
