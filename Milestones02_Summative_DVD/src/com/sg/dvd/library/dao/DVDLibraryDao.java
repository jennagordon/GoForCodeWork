package com.sg.dvd.library.dao;

import com.sg.dvd.library.dto.DVD;

import java.util.List;

public interface DVDLibraryDao {

    // adds the given DVD to the library and associates it with the given
    // title.
    DVD createDVD(String title, DVD dvd) throws DVDLibraryDaoException;

    // deletes given DVD
    DVD deleteDVD(String title) throws DVDLibraryDaoException;

    //edits a dvd's info
    DVD editDVD(String title, DVD dvd) throws DVDLibraryDaoException;

    //lists all DVDs in Library
    List<DVD> getAllDVD() throws DVDLibraryDaoException;

    //lists one DVD by it's title
    DVD getDVD(String title)throws DVDLibraryDaoException;
}
