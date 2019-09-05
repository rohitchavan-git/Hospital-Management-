package com.rohit.chavan.hospital.hospital;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
	 * join using jpql
	 * select new Map(u.reason as resion ,p.fname as patientname,d.fname as doctorname) 
	 * from Appointment as u 
	 * inner join Patient as p on u.patients=p.id   
	 * inner join Doctor as d on u.doctors=d.id 
	 */
	@Test
	public void testJpql() {
		
		
		/**
		 * 
		 * find doctor_name ,resion ,patient_name from appointment
		 */
		List<Map<String, Object>> appointmentPatientAndDoctorInfo = appointmentRepo.getAppointmentPatientAndDoctorInfo(); 
		for (Map<String, Object> map : appointmentPatientAndDoctorInfo) {
		        for (Map.Entry<String, Object> entry : map.entrySet()) {
		            String key = entry.getKey();
		            Object value = entry.getValue();
		            System.out.println(key + " = " + value);
		        }

		   }
		/**
		 * 
		 * using java stream 
		 * 
		 */
			
		List<Appointment> findAll = appointmentRepo.findAll();
		findAll.forEach(p->{
			System.out.println(p.getDoctor().getFname());
			System.out.println(p.getPatient().getFname());
			System.out.println(p.getReason());
		});
		
		
	}
	
	
	/**
	 * 
	 * List<Map<String,Object>> to List<Object> using java 8
	 * 
	 */
	
	
	@Test
	public void testListMapStringObject() {
		
	     List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("foo", "bar1");
	        map.put("foo1", "bar2");
	        map.put("foo2", "bar3");
	        Map<String, Object> map1 = new HashMap<String, Object>();
	        
	        map1.put("foo", "bar1");
	        map1.put("foo1", "bar2");
	        map1.put("foo2", "bar3");
	        Map<String, Object> map2 = new HashMap<String, Object>();
	        map2.put("foo", "bar1");
	        map2.put("foo1", "bar2");
	        map2.put("foo2", "bar3");
	        
	        list.add(map);
	        list.add(map1);
	        list.add(map2);
	        
	        
	      List<Object> flatMappedResult = list.stream().flatMap(
	    		  									p->p.entrySet().stream().map(p1->p1.getValue()).distinct()
	    		  						).collect(Collectors.toList());
	      
	      flatMappedResult.forEach(p->{
	    	 System.out.println(p);
	      });
	      	
	}
	
	
	/**
	 * @author rohit Chavan 
	 * 
	 */
	@Test
	public void checkPerformanceOfQuery() {
		
		List<Long> ids=new ArrayList<>();
		ids.add(1L);
		ids.add(2L);
		ids.add(3L);
		ids.add(4L);
		ids.add(5L);
		List<List<Doctor>> emps=new ArrayList<>();
		//mysql database IN limit
		if(ids.size() > 1000) {
			int k=0,j=1000;
			int iteration=ids.size()%1000;
			for(int i=0;i<iteration;i++) {
				List<Long> subList=new ArrayList<>();
				if(j<ids.size()) {
					 subList = ids.subList(k, j);
				}else {
					subList=ids.subList(k, ids.size());
				}
				k=k+1000;
				j=j+1000;
				List<List<Doctor>> doctors = Stream.of(subList).map(doctorRepo::getAllDoctor).collect(Collectors.toList());
				emps.addAll(doctors);
			}
		}else {
			
			
			
			emps=Stream.of(ids).map(doctorRepo::getAllDoctor).collect(Collectors.toList());
		}
		List<Doctor> flatEmps = emps.stream().flatMap(List::stream).collect(Collectors.toList());
		flatEmps.stream().peek(System.out::println);
	}
	
	
	
	
	/**
	 * @author Rohit chavan
	 *  How to use Stream and Optional in Spring boot application Examples
	 */
	@Test
	public void streamJavaExamples() {

		Long[] ids = { 1L, 2L, 3L, 4L, 5L };

		List<Optional<Doctor>> collect = Stream.of(ids)
					.map(doctorRepo::findById)
					.collect(Collectors.toList());

		
		/**
		 *  
		 * for understanding how List<Optional<Something> Build internally
		 * 
		 * 
		 *  List<Optional<Doctor>> = Arrays.asList(
		 *  	Optional.empty(),
		 *  	Optional.of(new Doctor("fname","lname","speci","id")),
		 *  	Optional.empty(),
		 *  	Optional.empty(),
		 *  	Optional.of(new Doctor("fname","lname","speci","id")
		 *  )
 		 *
		 */
		
		assertEquals(collect.size(), ids.length);

		List<String> allNames = collect.stream().filter(p -> p.isPresent()).map(p -> {
			System.out.println(p.get().getFname());
			return p.get().getFname();
		}).collect(Collectors.toList());

		/**
		 * safer side code if you don't know the resultant structure
		 * 
		 */

			List<String> allNamesWithIDsArray = collect.stream()
				.flatMap(p -> p.isPresent() ? Stream.of(p.get()) : Stream.empty())
				.peek(p -> System.out.println(p.getFname()))
				.map(p -> p.getFname()).collect(Collectors.toList());

		/*
		 * java 9 way 
		 * collect.stream().flatMap(Optional::stream).forEach(p -> {
		 * 		System.out.println(p.getFname()); 
		 * });
		 */
		

		List<String> allName = collect.stream()
				.filter(p -> p != null)
				.map(m1 -> m1.get().getFname())
				.peek(p -> System.out.println(p))
				.collect(Collectors.toList());
		
		
		
		/**
		 *  
		 * effictive way in java 8
		 * 
		 *  retrive all name where speciality = 'psychiatric'
		 */
		 List<String> allNamesWithSpecialityPsy = collect.stream()
				 		.flatMap(p->p.isPresent()?Stream.of(p.get()):Stream.empty())
				 		.filter(p->p.getSpeciality().equalsIgnoreCase("psychiatric"))
				 		.peek(p->System.out.println(p.getFname()))
				 		.map(p->p.getFname())
				 		.collect(Collectors.toList());
		 
		 
		 
		 
		 /**
		  * Simple Way java 8 
		  * 
		  */
		
		List<String> whereSpecificationPsy = collect.stream().filter(Optional::isPresent)
		 			.map(Optional::get)
		 			.filter(p->p.getSpeciality().equalsIgnoreCase("psychiatric"))
		 			.map(p->p.getFname())
		 			.collect(Collectors.toList());

		/*
		 * java 9 way
		 * 
		 * retrive all name where speciality = 'psychiatric'
		 * List<Doctor> allDoctors =collect.stream().flatMap(Optional::stream)
		 * .collect(Collectors.toList());
		 * 
		 * allDoctors.stream().filter(p ->p.getSpeciality().equalsIgnoreCase("psychiatric"))
		 * 		.forEach(o -> {
		 * 					System.out.println(o.getFname()); 
		 * });
		 */

	}
}
