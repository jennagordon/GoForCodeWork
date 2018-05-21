package dvdlibrary.dao;


import dvdlibrary.dto.DVD;

import java.util.List;

public interface DVDLibraryDao {

    // adds the given DVD to the dvdlibrary and associates it with the given
    // title.
    DVD createDVD(String title, DVD dvd) throws DVDLibraryPersistenceException;

    // deletes given DVD
    DVD deleteDVD(String title) throws DVDLibraryPersistenceException;

    //edits a dvd's info
    DVD editDVD(String title, DVD dvd) throws DVDLibraryPersistenceException;

    //lists all DVDs in Library
    List<DVD> getAllDVD() throws DVDLibraryPersistenceException;

    //lists one DVD by it's title
    DVD getDVD(String title)throws DVDLibraryPersistenceException;

}
