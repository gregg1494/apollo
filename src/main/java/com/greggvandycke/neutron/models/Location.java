package com.greggvandycke.neutron.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="locations")
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String address;
	private String city;
	private int zipCode;
	private String state;

	public Location(String address, String city, int zipCode, String state) {
		this.address = address;
		this.city = city;
		this.zipCode = zipCode;
		this.state = state;
	}
}
