package com.greggvandycke.Apollo.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="movies")
public class Movie extends Auditable<String> implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String title;
	private int length;

	@ManyToMany(mappedBy = "favorites", fetch = FetchType.LAZY)
	private List<User> users = new ArrayList<>();

	public Movie(String title, int length) {
		this.title = title;
		this.length = length;
	}
}
