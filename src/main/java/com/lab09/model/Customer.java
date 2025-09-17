package com.lab09.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="customer")

public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String name;
	@Column
	private String email;
	//===== Constructor ===== 
	public Customer() {}
	public Customer(Long id , String name ,String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}
	//===== GETTER SETTER =====
	//- Email
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	//- Id
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	//- Name
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
}
