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
	@JoinTable(name="patient_doctor",
	joinColumns = @JoinColumn(name="pid",referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name="did",referencedColumnName = "id"))
	private List<Doctor> doctors;
	
	@OneToMany
	private List<Appointment> appointments;
	
	

	public Patient() {
	}

	public Patient(String fname, String lname, String phone, Insurance insurance) {
		this.fname = fname;
		this.lname = lname;
		this.phone = phone;
		this.insurance = insurance;
	}

	public Patient(Long id, String fname, String lname, String phone, Insurance insurance) {
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.phone = phone;
		this.insurance = insurance;
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

}
