package dvdlibrary.dao;

import dvdlibrary.dto.DVD;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class DVDLibraryDaoTest {
    DVDLibraryDao dao = new DVDLibraryDaoFileImpl();

    public DVDLibraryDaoTest(){

    }

    @Before
    public void setUp() throws Exception {
        // Clears out map before every test
        // get dvds
        List<DVD> dvdList = dao.getAllDVD();
        // remove dvds from persistence
        for (DVD tempDVD : dvdList) {
            dao.deleteDVD(tempDVD.getTitle());
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreateDVD() throws Exception {
        // Arrange
        DVD dvd1 = new DVD("First Movie");
        dvd1.setReleaseDateLocal(LocalDate.parse("1990-01-01"));
        dvd1.setDirectorName("Steve");
        dvd1.setMpaa("G");
        dvd1.setStudio("Universal");
        dvd1.setUserRating("B-");

        // Act
        dao.createDVD(dvd1.getTitle(), dvd1);

        DVD fromDao = dao.getDVD(dvd1.getTitle());

        // Assert
        // confirming dvd1 is the same as dvd1 in the file
        assertEquals(dvd1, fromDao);
    }

    @Test
    public void testDeleteDVD() throws Exception {
        // Assert first dvd
        DVD dvd1 = new DVD("See Spot Run");
        dvd1.setReleaseDateLocal(LocalDate.parse("2000-01-30"));
        dvd1.setDirectorName("Steve");
        dvd1.setMpaa("G");
        dvd1.setStudio("Universal");
        dvd1.setUserRating("B+ great movie");

        // Assert second dvd
        DVD dvd2 = new DVD("Second Movie");
        dvd2.setReleaseDateLocal(LocalDate.parse("1990-01-02"));
        dvd2.setDirectorName("John");
        dvd2.setMpaa("R");
        dvd2.setStudio("Universal");
        dvd2.setUserRating("F!!!!");

        // Act
        dao.createDVD(dvd1.getTitle(), dvd1);
        dao.createDVD(dvd2.getTitle(), dvd2);

        // Act: remove dvd1
        // Assert: was dvd1 removed?
        dao.deleteDVD(dvd1.getTitle());
        assertEquals(1, dao.getAllDVD().size());
        assertNull(dao.getDVD(dvd1.getTitle()));

        // Act: remove dvd2
        // Assert: was dvd2 removed?
        dao.deleteDVD(dvd2.getTitle());
        assertEquals(0, dao.getAllDVD().size());
        assertNull(dao.getDVD(dvd2.getTitle()));

    }

    @Test
    public void testEditDVD() throws Exception{
        // Arrange
        // create dvds (1)
        DVD dvd1 = new DVD("Love Actually");
        dvd1.setReleaseDateLocal(LocalDate.parse("2008-04-01"));
        dvd1.setMpaa("R");
        dvd1.setDirectorName("John");
        dvd1.setStudio("Universal");
        dvd1.setUserRating("A+");
        dao.createDVD(dvd1.getTitle(), dvd1);

        DVD editDVD = new DVD("Love Actually");
        editDVD.setReleaseDateLocal(LocalDate.parse("2008-05-02"));
        editDVD.setMpaa("R");
        editDVD.setDirectorName("Tim");
        editDVD.setStudio("Unknown");
        editDVD.setUserRating("A+");
        dao.editDVD("Love Actually", editDVD);

        //DVD dvdWithChanges = dao.getDVD(editDVD.getTitle());

        // Act
        // add dvd1 to the file

        // edit the dvd we created in arrange


        // Assert
        // assertEquals: dvd1 , editDVD
        assertEquals(dvd1.getTitle(), editDVD.getTitle());
        assertEquals(dao.getDVD("Love Actually").getDirectorName(), editDVD.getDirectorName());
        assertEquals(dao.getDVD("Love Actually").getMpaa(), editDVD.getMpaa());
        assertEquals(dao.getDVD("Love Actually").getReleaseDateLocal(), editDVD.getReleaseDateLocal());
        assertEquals(dao.getDVD("Love Actually").getStudio(), editDVD.getStudio());
        assertEquals(dao.getDVD("Love Actually").getUserRating(), editDVD.getUserRating());

    }

    @Test
    public void testGetAllDVD() throws Exception {
        // Assert first dvd
        DVD dvd1 = new DVD("See Spot Run");
        dvd1.setReleaseDateLocal(LocalDate.parse("2000-02-01"));
        dvd1.setDirectorName("Steve");
        dvd1.setMpaa("G");
        dvd1.setStudio("Universal");
        dvd1.setUserRating("B+ great movie");

        // Assert second dvd
        DVD dvd2 = new DVD("Second Movie");
        dvd2.setReleaseDateLocal(LocalDate.parse("1990-01-01"));
        dvd2.setDirectorName("John");
        dvd2.setMpaa("R");
        dvd2.setStudio("Universal");
        dvd2.setUserRating("F!!!!");

        dao.createDVD(dvd1.getTitle(),dvd1);
        dao.createDVD(dvd2.getTitle(), dvd2);

        assertEquals(2, dao.getAllDVD().size());
    }

// DON'T NEED BECAUSE WE ARE TESTING THIS IN THE CREATE TEST
//    @Test
//    public void getDVD() {
//    }
}