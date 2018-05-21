package dvdlibrary.dao;

import dvdlibrary.dto.DVD;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DVDLibraryDaoStubImpl implements DVDLibraryDao {

    DVD onlyDVD;
    List<DVD> dvdList = new ArrayList<>();

    public DVDLibraryDaoStubImpl() {
        onlyDVD = new DVD("Movie Title");
        onlyDVD.setReleaseDateLocal(LocalDate.parse("2000-02-15"));
        onlyDVD.setMpaa("R");
        onlyDVD.setDirectorName("Joe");
        onlyDVD.setStudio("Universal");
        onlyDVD.setUserRating("good movie");

        dvdList.add(onlyDVD);
    }

    @Override
    public DVD createDVD(String title, DVD dvd) throws DVDLibraryPersistenceException {
        return onlyDVD;
    }

    @Override
    public DVD deleteDVD(String title) throws DVDLibraryPersistenceException {
        return onlyDVD;
    }

    @Override
    public DVD editDVD(String title, DVD dvd) throws DVDLibraryPersistenceException {
        return onlyDVD;
    }

    @Override
    public List<DVD> getAllDVD() throws DVDLibraryPersistenceException {
        return dvdList;
    }

    @Override
    public DVD getDVD(String title) throws DVDLibraryPersistenceException {
        return onlyDVD;
    }
}
