package com.example.imdbapi.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Getter
@Setter
public class Movie implements Serializable {
    private String id;
    private Integer rank;
    private String title;
    private String fullTitle;
    private Integer year;
    private String image;
    private String crew;
    private Double imDbRating;
    private Long imDbRatingCount;
}
