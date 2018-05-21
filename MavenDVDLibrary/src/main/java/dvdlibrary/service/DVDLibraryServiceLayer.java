package dvdlibrary.service;


import dvdlibrary.dao.DVDLibraryPersistenceException;
import dvdlibrary.dto.DVD;

import java.util.List;

public interface DVDLibraryServiceLayer {
    // Pass through method for create dvd
    DVD createDVD(String title, DVD dvd) throws DVDLibraryPersistenceException;

    // Pass through method for delete dvd
    DVD deleteDVD(String title) throws DVDLibraryPersistenceException;

    // Pass through method for edit dvd
    DVD editDVD(String title, DVD dvd) throws DVDLibraryPersistenceException;

    // Pass through method for lists all DVDs in Library
    List<DVD> getAllDVD() throws DVDLibraryPersistenceException;

    // Pass through method for lists one DVD by it's title
    DVD getDVD(String title)throws DVDLibraryPersistenceException;

}
