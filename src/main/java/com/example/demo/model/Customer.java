package com.example.demo.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.Setter;

@Data
@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, unique = true)
	private String nama;
	@Column(nullable = false)
	private Integer balance;
	private Integer owed_to_id;
	private Integer owed_to_nominal;
	private String keterangan;

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(String nama, Integer balance, Integer owed_to_id, Integer owed_to_nominal, String keterangan) {
		this.nama = nama;
		this.balance = balance;
		this.owed_to_id = owed_to_id;
		this.owed_to_nominal = owed_to_nominal;
		this.keterangan = keterangan;
	}

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

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public Integer getOwed_to_id() {
		return owed_to_id;
	}

	public void setOwed_to_id(Integer owed_to_id) {
		this.owed_to_id = owed_to_id;
	}

	public Integer getOwed_to_nominal() {
		return owed_to_nominal;
	}

	public void setOwed_to_nominal(Integer owed_to_nominal) {
		this.owed_to_nominal = owed_to_nominal;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	@Override
	public int hashCode() {
		return Objects.hash(balance, id, keterangan, nama, owed_to_id, owed_to_nominal);
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
		return Objects.equals(balance, other.balance) && id == other.id && Objects.equals(keterangan, other.keterangan)
				&& Objects.equals(nama, other.nama) && Objects.equals(owed_to_id, other.owed_to_id)
				&& Objects.equals(owed_to_nominal, other.owed_to_nominal);
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", nama=" + nama + ", balance=" + balance + ", owed_to_id=" + owed_to_id
				+ ", owed_to_nominal=" + owed_to_nominal + ", keterangan=" + keterangan + "]";
	}

}
