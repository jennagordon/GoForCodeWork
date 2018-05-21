package com.sg.dao;

import com.sg.model.DVD;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.*;

public class DvdDaoTest {

    DvdDao dao;

    @Before
    public void setUp() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("dao", DvdDao.class);


        List<DVD> dvdList = dao.retrieveAllDvds();
        for (DVD currentDvd : dvdList) {
            dao.removeDvd(currentDvd.getDvdId());
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addDvd() {
        LocalDate date = LocalDate.parse("01/01/2008", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        DVD dvd = new DVD();
        dvd.setTitle("Love Actually");
        dvd.setReleaseYear(date);
        dvd.setDirector("Steve");
        dvd.setRating("R");
        dvd.setNotes("Great Film");

        dao.addDvd(dvd);

        DVD dvdTest = dao.retrieveDvdById(dvd.getDvdId());

        assertEquals(dvd.getTitle(), dvdTest.getTitle());
    }

    @Test
    public void removeDvd() {
        LocalDate date = LocalDate.parse("01/01/2008", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        DVD dvd = new DVD();
        dvd.setTitle("Love Actually");
        dvd.setReleaseYear(date);
        dvd.setDirector("Steve");
        dvd.setRating("R");
        dvd.setNotes("Great Film");

        dao.addDvd(dvd);
        assertEquals(1, dao.retrieveAllDvds().size());
        dao.removeDvd(dvd.getDvdId());
        assertEquals(0, dao.retrieveAllDvds().size());
        assertNull(dao.retrieveDvdById(dvd.getDvdId()));
    }

    @Test
    public void updateDvd() {
        LocalDate date = LocalDate.parse("01/01/2008", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate date2 = LocalDate.parse("01/12/2000", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        DVD dvd = new DVD();
        dvd.setTitle("Love Actually");
        dvd.setReleaseYear(date);
        dvd.setDirector("Steve");
        dvd.setRating("R");
        dvd.setNotes("Great Film");

        dao.addDvd(dvd);

        DVD dvdToEdit = dao.retrieveDvdById(dvd.getDvdId());
        dvdToEdit.setReleaseYear(date2);
        dvdToEdit.setDirector("JJ");
        dao.updateDvd(dvdToEdit);

        assertEquals(LocalDate.parse("2000-12-01"), dvdToEdit.getReleaseYear());
        assertEquals("JJ", dvdToEdit.getDirector());
    }

    @Test
    public void retrieveAllDvds() {
        LocalDate date = LocalDate.parse("01/01/2008", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        DVD dvd = new DVD();
        dvd.setTitle("Love Actually");
        dvd.setReleaseYear(date);
        dvd.setDirector("Steve");
        dvd.setRating("R");
        dvd.setNotes("Great Film");

        dao.addDvd(dvd);

        LocalDate date2 = LocalDate.parse("01/12/2000", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        DVD dvd2 = new DVD();
        dvd2.setTitle("See Spot Run");
        dvd2.setReleaseYear(date2);
        dvd2.setDirector("JJ");
        dvd2.setRating("PG");
        dvd2.setNotes("Great Film");

        dao.addDvd(dvd2);

        assertEquals(2, dao.retrieveAllDvds().size());
    }

    @Test
    public void retrieveDvdById() {
        LocalDate date = LocalDate.parse("01/01/2008", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        DVD dvd = new DVD();
        dvd.setTitle("Love Actually");
        dvd.setReleaseYear(date);
        dvd.setDirector("Steve");
        dvd.setRating("R");
        dvd.setNotes("Great Film");

        dao.addDvd(dvd);
        DVD dvdTest = dao.retrieveDvdById(dvd.getDvdId());
        assertEquals(dvd.getTitle(), dvdTest.getTitle());
        assertEquals(dvd.getDirector(), dvdTest.getDirector());
    }

    @Test
    public void searchContacts() {
    }
}