package dvdlibrary.service;

import dvdlibrary.dao.DVDLibraryDao;
import dvdlibrary.dao.DVDLibraryDaoStubImpl;
import dvdlibrary.dto.DVD;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;

public class DVDLibraryServiceLayerTest {

    private DVDLibraryServiceLayer service;

    public DVDLibraryServiceLayerTest () {
//        DVDLibraryDao dao = new DVDLibraryDaoStubImpl();
//
//        service = new DVDLibraryServiceLayerImpl(dao);

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", DVDLibraryServiceLayer.class);

    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreateDVD() throws Exception {
        // Arrange
        // create a new DVD object
        DVD dvd = new DVD("Second Movie");
        dvd.setReleaseDateLocal(LocalDate.parse("2001-01-03"));
        dvd.setMpaa("PG");
        dvd.setDirectorName("Steve");
        dvd.setStudio("Lion");
        dvd.setUserRating("C, average movie");

        // Act
        // Assert
        service.createDVD(dvd.getTitle(), dvd);
    }

    @Test
    public void deleteDVD() {
    }

    @Test
    public void editDVD() {
    }

    @Test
    public void getAllDVD() {
    }

    @Test
    public void getDVD() {
    }
}