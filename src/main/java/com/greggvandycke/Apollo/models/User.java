package com.greggvandycke.Apollo.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
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

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "user_favorites",
			joinColumns = {
					@JoinColumn(name = "user_id", referencedColumnName = "id",
							nullable = false, updatable = false)},
			inverseJoinColumns = {
					@JoinColumn(name = "movie_id", referencedColumnName = "id",
							nullable = false, updatable = false)})
	private List<Movie> movies = new ArrayList<>();

	public User(String name, String username, String password, String email, String token) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.token = token;

	}

}