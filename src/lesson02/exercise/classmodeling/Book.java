package lesson02.exercise.classmodeling;

public class Book {
    private String authorFirst;
    private String authorLast;
    private String title;
    private String bookNumber;
    private String genre;
    private String type;

    public Book (String authorFirst, String authorLast, String title) {
        this.authorFirst = authorFirst;
        this.authorLast = authorLast;
        this.title = title;
    }


    public String getAuthorFirst() {
        return authorFirst;
    }

    public void setAuthorFirst(String authorFirst) {
        this.authorFirst = authorFirst;
    }

    public String getAuthorLast() {
        return authorLast;
    }

    public void setAuthorLast(String authorLast) {
        this.authorLast = authorLast;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(String bookNumber) {
        this.bookNumber = bookNumber;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
