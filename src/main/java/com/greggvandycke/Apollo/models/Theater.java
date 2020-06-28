package com.greggvandycke.Apollo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="theaters")
public class Theater extends Auditable<String> implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;

	@OneToOne
	private Location location;

	public Theater(String name, Location location) {
		this.name = name;
		this.location = location;
	}
}
