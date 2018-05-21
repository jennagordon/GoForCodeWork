package com.sg.dao;

import com.sg.dto.Hero;
import com.sg.dto.HeroSighting;
import com.sg.dto.Location;
import com.sg.dto.Sighting;
import com.sg.service.HeroService;
import com.sg.service.HeroSightingService;
import com.sg.service.LocationService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class SightingDaoImplTest {

    @Inject
    SightingDao sightingDao;

    @Inject
    LocationService locationService;

    @Inject
    HeroSightingService heroSightingService;

    @Inject
    HeroService heroService;

    LocalDate date = LocalDate.parse("01/01/2018", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    LocalDate date2 = LocalDate.parse("22/03/2018", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addSighting() {

        // Arrange
        // Act
        Location location = createTestLocation();
        Sighting createdSighting = createTestSighting(location);

        // Assert
        assertEquals("saw batman...i think", createdSighting.getDescription());
        assertEquals(LocalDate.parse("2018-01-01"), createdSighting.getDate());
        assertEquals(location.getLocationId(), createdSighting.getLocation().getLocationId());
        assertNotNull(createdSighting.getSightingId());
    }



    @Test
    public void removeSighting() {

        // Arrange
        Location location = createTestLocation();
        Sighting createdSighting = createTestSighting(location);

        // Act
        sightingDao.removeSighting(createdSighting);
        Sighting readSighting = sightingDao.retrieveSighting(createdSighting.getSightingId());

        // Assert
        assertNull(readSighting);

    }

    @Test
    public void updateSighting() {

        // Arrange
        Location location = createTestLocation();
        Sighting createdSighting = createTestSighting(location);

        Sighting readSighting = sightingDao.retrieveSighting(createdSighting.getSightingId());

        Location location2 = new Location();
        location2.setName("Hair Salon");
        location2.setDescription("red building");
        location2.setStreet("150 Main St");
        location2.setCity("South Deerfield");
        location2.setState("NH");
        location2.setZip("03856");
        location2.setLatitude("54.010");
        location2.setLongitude("81.987");

        locationService.addLocation(location2);

        readSighting.setDescription("just kidding i saw wonder women");
        readSighting.setDate(date2);
        readSighting.setLocation(location2);

        // Act
        sightingDao.updateSighting(readSighting);

        // Assert
        assertEquals("just kidding i saw wonder women", readSighting.getDescription());
        assertEquals(LocalDate.parse("2018-03-22"), readSighting.getDate());
        assertEquals(location2.getLocationId(), readSighting.getLocation().getLocationId());
        assertEquals(createdSighting.getSightingId(), readSighting.getSightingId());


    }

    @Test
    public void retrieveAllSightings() {

        // Arrange
        Location location = createTestLocation();
        createTestSighting(location);

        Location location2 = new Location();
        location2.setName("Hair Salon");
        location2.setDescription("red building");
        location2.setStreet("150 Main St");
        location2.setCity("South Deerfield");
        location2.setState("NH");
        location2.setZip("03856");
        location2.setLatitude("54.010");
        location2.setLongitude("81.987");

        locationService.addLocation(location2);

        Sighting sighting2 = new Sighting();
        sighting2.setDate(date2);
        sighting2.setDescription("saw wonder women...i think");
        sighting2.setLocation(location2);

        sightingDao.addSighting(sighting2);

        // Act
        List<Sighting> sightingList = sightingDao.retrieveAllSightings(Integer.MAX_VALUE, 0);

        // Assert
        assertEquals(2, sightingList.size());



    }

    @Test
    public void retrieveSighting() {

        // Arrange
        Location location = createTestLocation();
        Sighting createdSighting = createTestSighting(location);

        // Act
        Sighting readSighting = sightingDao.retrieveSighting(createdSighting.getSightingId());

        // Assert
        assertEquals("saw batman...i think", readSighting.getDescription());
        assertEquals(LocalDate.parse("2018-01-01"), readSighting.getDate());
        assertEquals(location.getLocationId(), readSighting.getLocation().getLocationId());
        assertEquals(createdSighting.getSightingId(), readSighting.getSightingId());

    }

    @Test
    public void retrieveSightingByHeroPage() {

        // testing we can get all sightings for one hero
        // Arrange
        // creating 3 but paging by 2
        int numberOfSighting = 2;
        Hero hero = createTestHero();
        Location location = createTestLocation();

        Sighting sighting1 = createTestSighting(location);

        HeroSighting heroSighting1 = new HeroSighting();
        heroSighting1.setSighting(sighting1);
        heroSighting1.setHero(hero);
        heroSightingService.addHeroSighting(heroSighting1);

        Sighting sighting2 = createTestSighting(location);

        HeroSighting heroSighting2 = new HeroSighting();
        heroSighting2.setSighting(sighting2);
        heroSighting2.setHero(hero);
        heroSightingService.addHeroSighting(heroSighting2);

        Sighting sighting3 = createTestSighting(location);

        HeroSighting heroSighting3 = new HeroSighting();
        heroSighting3.setSighting(sighting3);
        heroSighting3.setHero(hero);
        heroSightingService.addHeroSighting(heroSighting3);

        // Act
        List<Sighting> sightingList = sightingDao.retrieveSightingByHero(hero, 2, 0);

        // Assert
        assertSightingOnHero(hero, numberOfSighting, sightingList);

    }

    @Test
    public void retrieveSightingByHero() {

        // testing we can get all sightings for one hero
        // Arrange
        int numberOfSighting = 3;
        Hero hero = createTestHero();
        Location location = createTestLocation();

        Sighting sighting1 = createTestSighting(location);

        HeroSighting heroSighting1 = new HeroSighting();
        heroSighting1.setSighting(sighting1);
        heroSighting1.setHero(hero);
        heroSightingService.addHeroSighting(heroSighting1);

        Sighting sighting2 = createTestSighting(location);

        HeroSighting heroSighting2 = new HeroSighting();
        heroSighting2.setSighting(sighting2);
        heroSighting2.setHero(hero);
        heroSightingService.addHeroSighting(heroSighting2);

        Sighting sighting3 = createTestSighting(location);

        HeroSighting heroSighting3 = new HeroSighting();
        heroSighting3.setSighting(sighting3);
        heroSighting3.setHero(hero);
        heroSightingService.addHeroSighting(heroSighting3);

        // Act
        List<Sighting> sightingList = sightingDao.retrieveSightingByHero(hero, Integer.MAX_VALUE, 0);

        // Assert
        assertSightingOnHero(hero, numberOfSighting, sightingList);
    }

    @Test
    public void retrieveSightingByLocationPage() {

        // testing we can get all sightings for one location

        // Arrange
        // create sightings with the same location
        // creating 3 but paging by 2
        int numberOfSightings = 2;
        Location location = createTestLocation();

        Sighting sighting1 = createTestSighting(location);
        Sighting sighting2 = createTestSighting(location);
        Sighting sighting3 = createTestSighting(location);

        // Act
        // return the list
        List<Sighting> sightingList = sightingDao.retrieveSightingByLocation(location, 2, 0);

        // Assert
        // loop through the sightings and make sure the location id is the same as what we created
        assertEquals(numberOfSightings, sightingList.size());
        for(Sighting sighting : sightingList) {

            boolean containsLocationId = false;
            if (sighting.getLocation().getLocationId().equals(location.getLocationId())) {
                containsLocationId = true;
            }

            assertEquals(true, containsLocationId);
        }
    }

    @Test
    public void retrieveSightingByLocation() {

        // testing we can get all sightings for one location

        // Arrange
        // create sightings with the same location
        int numberOfSightings = 3;
        Location location = createTestLocation();

        Sighting sighting1 = createTestSighting(location);
        Sighting sighting2 = createTestSighting(location);
        Sighting sighting3 = createTestSighting(location);

        // Act
        // return the list
        List<Sighting> sightingList = sightingDao.retrieveSightingByLocation(location, Integer.MAX_VALUE, 0);

        // Assert
        // loop through the sightings and make sure the location id is the same as what we created
        assertEquals(numberOfSightings, sightingList.size());
        for(Sighting sighting : sightingList) {

            boolean containsLocationId = false;
            if (sighting.getLocation().getLocationId().equals(location.getLocationId())) {
                containsLocationId = true;
            }

            assertEquals(true, containsLocationId);
        }
    }

    @Test
    public void retrieveSightingByDatePage() {
        // testing we can get all sightings for one date
        // Arrange
        // create sightings with the same date
        // using date declared at top
        // creating 3 but paging by 2
        int numberOfSightings = 2;
        Location location = createTestLocation();

        Sighting sighting1 = createTestSighting(location);
        Sighting sighting2 = createTestSighting(location);
        Sighting sighting3 = createTestSighting(location);

        // Act
        List<Sighting> sightingList = sightingDao.retrieveSightingByDate(date, 2, 0);

        // Assert
        assertEquals(numberOfSightings, sightingList.size());
        for(Sighting sighting : sightingList) {

            boolean containsDate = false;
            if (sighting.getDate().equals(LocalDate.parse("2018-01-01"))) {
                containsDate = true;
            }

            assertEquals(true, containsDate);
        }


    }


    @Test
    public void retrieveSightingByDate() {
        // testing we can get all sightings for one date
        // Arrange
        // create sightings with the same date
        // using date declared at top
        int numberOfSightings = 3;
        Location location = createTestLocation();

        Sighting sighting1 = createTestSighting(location);
        Sighting sighting2 = createTestSighting(location);
        Sighting sighting3 = createTestSighting(location);

        // Act
        List<Sighting> sightingList = sightingDao.retrieveSightingByDate(date, Integer.MAX_VALUE, 0);

        // Assert
        assertEquals(numberOfSightings, sightingList.size());
        for(Sighting sighting : sightingList) {

            boolean containsDate = false;
            if (sighting.getDate().equals(LocalDate.parse("2018-01-01"))) {
                containsDate = true;
            }

            assertEquals(true, containsDate);
        }

    }


    // HELPER METHODS TO CREATE TEST DATA
    private Sighting createTestSighting(Location location) {

        Sighting sighting = new Sighting();
        sighting.setDate(date);
        sighting.setDescription("saw batman...i think");
        sighting.setLocation(location);

        Sighting createdSighting = sightingDao.addSighting(sighting);

        return createdSighting;
    }


    private Location createTestLocation() {
        Location location = new Location();
        location.setName("Starbucks");
        location.setDescription("grey building");
        location.setStreet("123 Main St");
        location.setCity("Deerfield");
        location.setState("MA");
        location.setZip("01373");
        location.setLatitude("24.010");
        location.setLongitude("45.987");
        Location createdLocation = locationService.addLocation(location);

        return createdLocation;
    }

    private Hero createTestHero() {

        Hero hero = new Hero();
        hero.setName("Batman");
        hero.setDescription("Black suit with wings");

        Hero createdHero = heroService.addHero(hero);

        return createdHero;
    }

    private void assertSightingOnHero(Hero hero, int numberOfSighting, List<Sighting> resultSighting) {
        assertEquals(numberOfSighting, resultSighting.size());

        for (Sighting sighting : resultSighting) {
            List<Hero> heroList = heroService.retrieveHeroBySighting(sighting, Integer.MAX_VALUE, 0);

            // Loop through hero's organization and verify it contains the one we added
            boolean containsHero = false;
            for (Hero her : heroList) {
                if (her.getHeroId().equals(hero.getHeroId())) {
                    containsHero = true;

                }

                assertEquals(true, containsHero);
            }

        }
    }
}