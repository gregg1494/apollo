package com.greggvandycke.Apollo.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="theaters")
public class Theater {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String location;
}
