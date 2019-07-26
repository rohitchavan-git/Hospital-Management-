package com.rohit.chavan.hospital.hospital.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String fname;
	private String lname;
	private String speciality;
	@ManyToMany(mappedBy = "doctors")
	private List<Patient> patients;
	@OneToMany
	private List<Appointment> appointments;
	
	public Doctor() {
	}
	public Doctor(String fname, String lname, String speciality) {
		this.fname = fname;
		this.lname = lname;
		this.speciality = speciality;
	}
	public Doctor(Long id, String fname, String lname, String speciality) {
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.speciality = speciality;
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
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	@Override
	public String toString() {
		return "Doctor [id=" + id + ", fname=" + fname + ", lname=" + lname + ", speciality=" + speciality + "]";
	}
	
}
