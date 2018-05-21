package dvdlibrary.service;


import dvdlibrary.dao.DVDLibraryAuditDao;
import dvdlibrary.dao.DVDLibraryDao;
import dvdlibrary.dao.DVDLibraryPersistenceException;
import dvdlibrary.dto.DVD;

import java.util.List;

public class DVDLibraryServiceLayerImpl implements DVDLibraryServiceLayer {

    private DVDLibraryDao dao;
    private DVDLibraryAuditDao auditDao;

    public DVDLibraryServiceLayerImpl(DVDLibraryDao myDao, DVDLibraryAuditDao myAuditDao) {
        this.dao = myDao;
        this.auditDao = myAuditDao;
    }

    @Override
    public DVD createDVD(String title, DVD dvd) throws DVDLibraryPersistenceException {
        return dao.createDVD(title, dvd);
    }

    @Override
    public DVD deleteDVD(String title) throws DVDLibraryPersistenceException {
        return dao.deleteDVD(title);
    }

    @Override
    public DVD editDVD(String title, DVD dvd) throws DVDLibraryPersistenceException {
        return dao.editDVD(title, dvd);
    }

    @Override
    public List<DVD> getAllDVD() throws DVDLibraryPersistenceException {
        return dao.getAllDVD();
    }

    @Override
    public DVD getDVD(String title) throws DVDLibraryPersistenceException {
        return dao.getDVD(title);
    }
}
