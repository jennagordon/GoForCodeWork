package com.sg.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class DVD {

    @NotEmpty(message = "You must supply a value for title.")
    @Length(max = 50, message = "Title must be no more than 50 characters in length.")
    private String title;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate releaseYear;

    @NotEmpty(message = "You must supply a value for director.")
    @Length(max = 50, message = "Director must be no more than 50 characters in length.")
    private String director;

    private String notes;

    @NotEmpty(message = "You must supply a value for rating.")
    private String rating;

    private int dvdId ;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(LocalDate releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getDvdId() {
        return dvdId;
    }

    public void setDvdId(int dvdId) {
        this.dvdId = dvdId;
    }
}
