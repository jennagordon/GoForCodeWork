package dvdlibrary.dto;

import java.time.LocalDate;
import java.util.Objects;

public class DVD {
    private String title;
    private String releaseDate;
    private LocalDate releaseDateLocal;
    private String mpaa;
    private String directorName;
    private String studio;
    private String userRating;


    public DVD(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getReleaseDateLocal() {
        return releaseDateLocal;
    }

    public void setReleaseDateLocal(LocalDate releaseDateLocal) {
        this.releaseDateLocal = releaseDateLocal;
    }

    public String getMpaa() {
        return mpaa;
    }

    public void setMpaa(String mpaa) {
        this.mpaa = mpaa;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public String toString() {
        return "Title: " + title + " |Release Date: " + releaseDateLocal +
                " |MPAA: " + mpaa + " |Director's Name: " + directorName + " |Studio: "
                + studio + " |User Rating: " + userRating;
    }

    // Adding these so we can compare the object to the other object
    // that is stored in file (AKA to use assertEquals)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DVD)) return false;
        DVD dvd = (DVD) o;
        return Objects.equals(title, dvd.title) &&
                Objects.equals(releaseDate, dvd.releaseDate) &&
                Objects.equals(mpaa, dvd.mpaa) &&
                Objects.equals(directorName, dvd.directorName) &&
                Objects.equals(studio, dvd.studio) &&
                Objects.equals(userRating, dvd.userRating);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, releaseDate, mpaa, directorName, studio, userRating);
    }
}
