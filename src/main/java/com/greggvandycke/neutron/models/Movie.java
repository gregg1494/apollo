package com.greggvandycke.neutron.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
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


	@ManyToMany
	@JoinTable(name = "movie_genre",
			joinColumns = {
					@JoinColumn(name = "movie_id",referencedColumnName = "id")},
			inverseJoinColumns = {
					@JoinColumn(name = "genre_id", referencedColumnName = "id")})
	private List<Genre> genres = new ArrayList();


	@ManyToMany(mappedBy = "favorites", fetch = FetchType.LAZY)
	private List<User> users = new ArrayList<>();

	public Movie(String title, String length, String trailerUrl, String coverPhotoUrl) {
		this.title = title;
		this.length = length;
		this.trailerUrl = trailerUrl;
		this.coverPhotoUrl = coverPhotoUrl;
	}
}