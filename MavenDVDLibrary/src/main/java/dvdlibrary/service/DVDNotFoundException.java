package dvdlibrary.service;

public class DVDNotFoundException extends Exception {
    public DVDNotFoundException(String message) {
        super(message);
    }

    public DVDNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
