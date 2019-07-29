package com.rohit.chavan.hospital.hospital.entity;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String fname;
	private String lname;
	private String phone;
	@Embedded
	private Insurance insurance;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "patient_doctor", joinColumns = @JoinColumn(name = "pid", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "did", referencedColumnName = "id"))
	private List<Doctor> doctors;

	@OneToMany(mappedBy = "patients")
	private List<Appointment> appointments;

	public Patient() {
	}

	public Patient(String fname, String lname, String phone, Insurance insurance, List<Doctor> doctors,
			List<Appointment> appointments) {
		this.fname = fname;
		this.lname = lname;
		this.phone = phone;
		this.insurance = insurance;
		this.doctors = doctors;
		this.appointments = appointments;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Insurance getInsurance() {
		return insurance;
	}

	public void setInsurance(Insurance insurance) {
		this.insurance = insurance;
	}

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", fname=" + fname + ", lname=" + lname + ", phone=" + phone + "]";
	}

	
}
