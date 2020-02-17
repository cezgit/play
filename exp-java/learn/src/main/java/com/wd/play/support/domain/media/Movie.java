package com.wd.play.support.domain.media;

import com.wd.play.support.domain.common.PrototypeCapable;

import java.util.Objects;

public class Movie implements PrototypeCapable {
    private String title;
    private Double rating;
    private Boolean starred;

    public Movie() {
    }

    public Movie(String title) {
        this.title = title;
    }

    public Movie(String title, Double rating) {
        this.title = title;
        this.rating = rating;
    }

    public Movie(String title, Double rating, Boolean starred) {
        this.title = title;
        this.rating = rating;
        this.starred = starred;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Boolean getStarred() {
        return starred;
    }

    public void setStarred(Boolean starred) {
        this.starred = starred;
    }

    @Override
    public PrototypeCapable clone() throws CloneNotSupportedException {
        System.out.println("Cloning Movie object..");
        return (Movie) super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", rating=" + rating +
                ", starred=" + starred +
                '}';
    }


}
