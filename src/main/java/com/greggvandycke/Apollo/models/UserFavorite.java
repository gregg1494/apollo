package com.greggvandycke.Apollo.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_favorites")
public class UserFavorite {

    @Id
    @Column(name="user_id")
    private Long userId;

    @Column(name="movie_id")
    private Long movieId;
}
