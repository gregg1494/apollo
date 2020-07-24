package com.greggvandycke.neutron.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="movies")
@EntityListeners(AuditingEntityListener.class)
public class Movie extends Auditable<String> implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String title;
	private String length;
	private String trailerUrl;
	private String coverPhotoUrl;
	private boolean newRelease;
	private String releaseDate;
	private double rating;

	@ManyToMany
	@JoinTable(name = "movie_genre",
			joinColumns = {
					@JoinColumn(name = "movie_id",referencedColumnName = "id")},
			inverseJoinColumns = {
					@JoinColumn(name = "genre_id", referencedColumnName = "id")})
	private List<Genre> genres = new ArrayList();


	@ManyToMany(mappedBy = "favorites", fetch = FetchType.LAZY)
	private List<User> users = new ArrayList<>();

	public Movie(String title, String length, String trailerUrl, String coverPhotoUrl, boolean newRelease, String releaseDate, double rating) {
		this.title = title;
		this.length = length;
		this.trailerUrl = trailerUrl;
		this.coverPhotoUrl = coverPhotoUrl;
		this.newRelease = newRelease;
		this.releaseDate = releaseDate;
		this.rating = rating;
	}
}