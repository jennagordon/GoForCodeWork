package com.sg.dvd.library.service;

import com.sg.dvd.library.dao.DVDLibraryDao;
import com.sg.dvd.library.dao.DVDLibraryDaoException;
import com.sg.dvd.library.dao.DVDLibraryDaoFileImpl;
import com.sg.dvd.library.dto.DVD;

import java.util.List;

public class DVDLibraryServiceLayerImpl implements DVDLibraryServiceLayer {

    private DVDLibraryDao dao;

    public DVDLibraryServiceLayerImpl(DVDLibraryDao myDao) {
        this.dao = myDao;
    }

    @Override
    public DVD createDVD(String title, DVD dvd) throws DVDLibraryDaoException {
        return dao.createDVD(title, dvd);
    }

    @Override
    public DVD deleteDVD(String title) throws DVDLibraryDaoException {
        return dao.deleteDVD(title);
    }

    @Override
    public DVD editDVD(String title, DVD dvd) throws DVDLibraryDaoException {
        return dao.editDVD(title, dvd);
    }

    @Override
    public List<DVD> getAllDVD() throws DVDLibraryDaoException {
        return dao.getAllDVD();
    }

    @Override
    public DVD getDVD(String title) throws DVDLibraryDaoException {
        return getDVD(title);
    }
}
