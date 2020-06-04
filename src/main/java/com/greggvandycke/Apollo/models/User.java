package com.greggvandycke.Apollo.models;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "users")

public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String username;
	private String password;
	private String email;
	private String token;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_favorites",
			joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") },
			inverseJoinColumns = { @JoinColumn(name = "movie_id", referencedColumnName = "id") })
	private List<Movie> movies;
}