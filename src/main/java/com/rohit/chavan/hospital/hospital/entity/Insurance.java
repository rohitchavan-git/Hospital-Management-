package com.rohit.chavan.hospital.hospital.entity;

import javax.persistence.Embeddable;

/**
 * Insurance pojo for component inheritance
 * 
 * @author Rohit Chavan
 *
 */
@Embeddable
public class Insurance {
	private String providerName;
	private double copay;

	public Insurance() {
	}

	public Insurance(String providerName, double copay) {
		this.providerName = providerName;
		this.copay = copay;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public double getCopay() {
		return copay;
	}

	public void setCopay(double copay) {
		this.copay = copay;
	}
}
