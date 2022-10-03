package com.example.Tan2x.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String userName;
	private String name;
	private boolean orderd;
	private String dateTime;
	private int totalCost;
	private boolean received;
	private boolean cancelled;
	private String address;
	private String payment;
	
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Orders(long id, String userName, String name, boolean orderd, String dateTime, int totalCost,
			boolean received, boolean cancelled, String address, String payment) {
		super();
		this.id = id;
		this.userName = userName;
		this.name = name;
		this.orderd = orderd;
		this.dateTime = dateTime;
		this.totalCost = totalCost;
		this.received = received;
		this.cancelled = cancelled;
		this.address = address;
		this.payment = payment;
	}

	public Orders(String userName, String name, boolean orderd, String dateTime, int totalCost, boolean received,
			boolean cancelled, String address, String payment) {
		super();
		this.userName = userName;
		this.name = name;
		this.orderd = orderd;
		this.dateTime = dateTime;
		this.totalCost = totalCost;
		this.received = received;
		this.cancelled = cancelled;
		this.address = address;
		this.payment = payment;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOrderd() {
		return orderd;
	}

	public void setOrderd(boolean orderd) {
		this.orderd = orderd;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	public boolean isReceived() {
		return received;
	}

	public void setReceived(boolean received) {
		this.received = received;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
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
		return "Orders [id=" + id + ", userName=" + userName + ", name=" + name + ", orderd=" + orderd + ", dateTime="
				+ dateTime + ", totalCost=" + totalCost + ", received=" + received + ", cancelled=" + cancelled
				+ ", address=" + address + ", payment=" + payment + "]";
	}
	
}
