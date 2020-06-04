package com.greggvandycke.Apollo.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name="movies")
public class Movie implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String title;
	private int length;

	@ManyToMany(mappedBy = "movies", fetch = FetchType.EAGER)
	private List<User> users;
}