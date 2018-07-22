package com.aurum.bitcoin.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
/**
 * 
 * @author momo
 *
 */
@Entity
@Data
public class Bitcoin implements Comparable<Bitcoin> {

	private long date;
	private float price;
	private float amount;
	@Id
	@GeneratedValue
	private int tid;
	private String type;

	public Bitcoin() {
	}

	public Bitcoin(long date, float price, float amount, int tid, String type) {
		super();
		this.date = date;
		this.price = price;
		this.amount = amount;
		this.tid = tid;
		this.type = type;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int compareTo(Bitcoin o) {
		if (price < o.price) {
			return -1;
		}

		if (price > o.price) {
			return 1;
		}
		return 0;
	}

	@Override
	public String toString() {
		return "Bitcoin [date=" + date + ", price=" + price + ", amount=" + amount + ", tid=" + tid + ", type=" + type
				+ "]";
	}

}
