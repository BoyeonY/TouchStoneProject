package com.nsccDAD.touchstone_demo.entity;

import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Order")
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "order_id")
	private int order_id;
	
	@Column(name = "order_date")
	private Timestamp order_date;
	
	@Column(name = "order_cus")
	private String order_cus;
	
	@Column(name = "order_process")
	private String order_process;
	
	public Order() {}
	public Order(String order_cus) {
		this.order_cus = order_cus;
		this.order_date = new Timestamp(new Date().getTime());
		this.order_process = "Order";
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public Timestamp getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Timestamp order_date) {
		this.order_date = order_date;
	}

	public String getOrder_cus() {
		return order_cus;
	}

	public void setOrder_cus(String order_cus) {
		this.order_cus = order_cus;
	}

	public String getOrder_process() {
		return order_process;
	}

	public void setOrder_process(String order_process) {
		this.order_process = order_process;
	};
	
	
}
