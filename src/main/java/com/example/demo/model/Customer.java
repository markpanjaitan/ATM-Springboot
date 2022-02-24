package com.example.demo.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Objects;

import javax.persistence.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String nama;
	private Double balance;
	private Integer owed_to_id;
	private Double owed_nominal;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Integer getOwed_to_id() {
		return owed_to_id;
	}

	public void setOwed_to_id(Integer owed_to_id) {
		this.owed_to_id = owed_to_id;
	}

	public Double getOwed_nominal() {
		return owed_nominal;
	}

	public void setOwed_nominal(Double owed_nominal) {
		this.owed_nominal = owed_nominal;
	}

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		return Objects.hash(balance, id, nama, owed_nominal, owed_to_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(balance, other.balance) && id == other.id && Objects.equals(nama, other.nama)
				&& Objects.equals(owed_nominal, other.owed_nominal) && Objects.equals(owed_to_id, other.owed_to_id);
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", nama=" + nama + ", balance=" + balance + ", owed_to_id=" + owed_to_id
				+ ", owed_nominal=" + owed_nominal + "]";
	}

	public Customer(String nama, Double balance, Integer owed_to_id, Double owed_nominal) {
		this.nama = nama;
		this.balance = balance;
		this.owed_to_id = owed_to_id;
		this.owed_nominal = owed_nominal;
	}

}
