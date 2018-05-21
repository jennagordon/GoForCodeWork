package com.sg.dvd.library.service;

import com.sg.dvd.library.dao.DVDLibraryDaoException;
import com.sg.dvd.library.dto.DVD;

import java.util.List;

public interface DVDLibraryServiceLayer {
    // Pass through method for create dvd
    DVD createDVD(String title, DVD dvd) throws DVDLibraryDaoException;

    // Pass through method for delete dvd
    DVD deleteDVD(String title) throws DVDLibraryDaoException;

    // Pass through method for edit dvd
    DVD editDVD(String title, DVD dvd) throws DVDLibraryDaoException;

    // Pass through method for lists all DVDs in Library
    List<DVD> getAllDVD() throws DVDLibraryDaoException;

    // Pass through method for lists one DVD by it's title
    DVD getDVD(String title)throws DVDLibraryDaoException;

}
