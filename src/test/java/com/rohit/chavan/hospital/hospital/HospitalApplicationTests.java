package com.rohit.chavan.hospital.hospital;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rohit.chavan.hospital.hospital.Exceptions.MyException;
import com.rohit.chavan.hospital.hospital.entity.Appointment;
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
	 * Adding doctors into database
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

	/**
	 * Adding Patients into databases
	 */

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

	/**
	 * Update the Patients Mobile number using id
	 */
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

	@Autowired
	private AppointmentRepo appointmentRepo;

	/**
	 * make appointments and assign doctors and patient.
	 * 
	 */
	@Test
	public void addAppointment() {

		Appointment appointment = new Appointment();
		Timestamp appointmentTimestamp = new Timestamp(new Date().getTime());
		appointment.setAppointmentTimestamp(appointmentTimestamp);
		appointment.setReason("I have Problem");
		appointment.setStared(true);
		appointment.setEnded(false);
		appointment.setDoctor(doctorRepo.findById(1L).get());
		appointment.setPatient(patientRepo.findById(5L).get());
		appointmentRepo.save(appointment);

	}

	/**
	 * Fetch the appointment if doctor_id=1 for this we have to make the custom
	 * queue
	 */
	@Test
	public void getAppointment() {

		Appointment appoint = appointmentRepo.getAppoint(1L);
		assertNotNull(appoint);
		System.out.println("Appoint id :-" + appoint.getId());
		System.out.println("Appoint resion :-" + appoint.getReason());
		System.out.println("Appoint Assigned Doctor info :- " + appoint.getDoctor());
		System.out.println("Patient Info :-" + appoint.getPatient());
		System.out.println("Insurance Info:-" + appoint.getPatient().getInsurance());

	}

	/**
	 * Find all Doctors info and print it use java 8 concepts java 8 Concept used :
	 * Supplier ,Consumer ,Functional Interface ,Streams
	 *
	 * @author Rohit Chavan
	 */
	@Test
	public void testDoctor() {

		Supplier<List<Doctor>> suppliDoctorInfo = () -> doctorRepo.findAll();

		suppliDoctorInfo.get().stream().forEach(p -> {
			System.out.println(p);
		});
	}

	/**
	 * @author Rohit chavan How to use Stream and Optional in Spring boot
	 *         application Examples
	 */
	@Test
	public void streamJavaExamples() {

		Long[] ids = { 1L, 2L, 3L, 4L, 5L };

		// retrive all the data where id in(1,2,3,4,5)
		List<Optional<Doctor>> collect = Stream.of(ids).map(doctorRepo::findById).collect(Collectors.toList());
		// check the length of list and ids array is equal or not
		assertEquals(collect.size(), ids.length);

		collect.stream().flatMap(Optional::stream).forEach(p -> {
		//	String fname = Optional.ofNullable(p).orElseThrow(NullPointerException::new).getFname();
			System.out.println(p.getFname());
			
		});

		// retrive all name where speciality = 'psychiatric'

		collect.stream().filter(p -> p != null)
				.filter(p1 -> Optional.ofNullable(p1).get().get().getSpeciality().equalsIgnoreCase("psychiatric"))
				.map(m1 -> m1.get().getFname())
				.peek(p->System.out.println(p))
				.collect(Collectors.toList());

		// another way

		List<Doctor> allDoctors = collect.stream().flatMap(Optional::stream).collect(Collectors.toList());
		allDoctors.stream().filter(p -> p.getSpeciality().equalsIgnoreCase("psychiatric")).forEach(o -> {
			System.out.println(o.getFname());
		});

	}
}
