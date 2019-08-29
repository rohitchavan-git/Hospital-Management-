package com.rohit.chavan.hospital.hospital.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Timestamp appointmentTimestamp;
	private boolean stared;
	private boolean ended;
	private String reason;

	@ManyToOne(targetEntity =Patient.class )
	private Patient patients;
	@ManyToOne(targetEntity = Doctor.class)
	private Doctor doctors;

	public Appointment() {
	}

	public Appointment(Timestamp appointmentTimestamp, boolean stared, boolean ended, String reason, Patient patient,
			Doctor doctor) {
		this.appointmentTimestamp = appointmentTimestamp;
		this.stared = stared;
		this.ended = ended;
		this.reason = reason;
		this.patients = patient;
		this.doctors = doctor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getAppointmentTimestamp() {
		return appointmentTimestamp;
	}

	public void setAppointmentTimestamp(Timestamp appointmentTimestamp) {
		this.appointmentTimestamp = appointmentTimestamp;
	}

	public boolean isStared() {
		return stared;
	}

	public void setStared(boolean stared) {
		this.stared = stared;
	}

	public boolean isEnded() {
		return ended;
	}

	public void setEnded(boolean ended) {
		this.ended = ended;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Patient getPatient() {
		return patients;
	}

	public void setPatient(Patient patient) {
		this.patients = patient;
	}

	public Doctor getDoctor() {
		return doctors;
	}

	public void setDoctor(Doctor doctor) {
		this.doctors = doctor;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", appointmentTimestamp=" + appointmentTimestamp + ", stared=" + stared
				+ ", ended=" + ended + ", reason=" + reason + ", patient=" + patients + ", doctor=" + doctors + "]";
	}

}
