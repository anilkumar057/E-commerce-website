package com.example.Tan2x.dto;


public class OrderDetailsDTO {

	private String name;
	private String mobile;
	private String address;
	private String payment;
	
	public OrderDetailsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderDetailsDTO(String name, String mobile, String address, String payment) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.address = address;
		this.payment = payment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPayment() {
		return payment;
	}
	
	public void setPayment(String payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "OrderDetailsDTO [name=" + name + ", mobile=" + mobile + ", address=" + address + ", payment=" + payment + "]";
	}
	
	
}
