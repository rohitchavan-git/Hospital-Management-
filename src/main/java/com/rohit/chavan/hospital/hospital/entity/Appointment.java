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

	@ManyToOne
	private Patient patient;
	@ManyToOne
	private Doctor doctor;
	
	
	public Appointment() {
	}

	public Appointment(Timestamp appointmentTimestamp, boolean stared, boolean ended, String reason) {
		this.appointmentTimestamp = appointmentTimestamp;
		this.stared = stared;
		this.ended = ended;
		this.reason = reason;
	}

	public Appointment(Long id, Timestamp appointmentTimestamp, boolean stared, boolean ended, String reason) {
		this.id = id;
		this.appointmentTimestamp = appointmentTimestamp;
		this.stared = stared;
		this.ended = ended;
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", appointmentTimestamp=" + appointmentTimestamp + ", stared=" + stared
				+ ", ended=" + ended + ", reason=" + reason + "]";
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

}
