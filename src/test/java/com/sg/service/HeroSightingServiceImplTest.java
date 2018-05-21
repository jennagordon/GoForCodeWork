package com.sg.service;

import com.sg.dao.HeroSightingDao;
import com.sg.dto.Hero;
import com.sg.dto.HeroSighting;
import com.sg.dto.Location;
import com.sg.dto.Sighting;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class HeroSightingServiceImplTest {

    @Inject
    HeroSightingService heroSightingService;

    @Inject
    HeroService heroService;

    @Inject
    SightingService sightingService;

    @Inject
    LocationService locationService;

    LocalDate date = LocalDate.parse("01/01/2018", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    LocalDate date2 = LocalDate.parse("22/03/2018", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addHeroSighting() {

        // Arrange
        // Act
        Hero hero = createTestHero();
        Location location = createTestLocation();
        Sighting sighting = createTestSighting(location);

        HeroSighting createdHeroSighting = createTestHeroSighting(hero, sighting);

        // Assert
        assertEquals(hero.getHeroId(), createdHeroSighting.getHero().getHeroId());
        assertEquals(sighting.getSightingId(), createdHeroSighting.getSighting().getSightingId());
        assertNotNull(createdHeroSighting.getHeroSightingId());

    }

    @Test
    public void removeHeroSighting() {

        // Arrange
        Hero hero = createTestHero();
        Location location = createTestLocation();
        Sighting sighting = createTestSighting(location);

        HeroSighting createdHeroSighting = createTestHeroSighting(hero, sighting);

        // Act
        heroSightingService.removeHeroSighting(createdHeroSighting);
        HeroSighting readHeroSighting = heroSightingService.retrieveHeroSightingById(createdHeroSighting.getHeroSightingId());

        // Assert
        assertNull(readHeroSighting);
    }

    @Test
    public void updateHeroSighting() {

        // Arrange
        Hero hero = createTestHero();
        Location location = createTestLocation();
        Sighting sighting = createTestSighting(location);

        HeroSighting createdHeroSighting = createTestHeroSighting(hero, sighting);

        HeroSighting readHeroSighting = heroSightingService.retrieveHeroSightingById(createdHeroSighting.getHeroSightingId());

        // setting updates
        Location location2 = new Location();
        location2.setName("Hair Salon");
        location2.setDescription("hair station");
        location2.setStreet("150 Main St");
        location2.setCity("South Deerfield");
        location2.setState("NH");
        location2.setZip("03856");
        location2.setLatitude("54.010");
        location2.setLongitude("81.987");
        locationService.addLocation(location2);

        Hero hero2 = new Hero();
        hero2.setName("Superman");
        hero2.setDescription("Red and Blue suit with cape");
        heroService.addHero(hero2);

        Sighting sighting2 = new Sighting();
        sighting2.setDate(date2);
        sighting2.setDescription("Superman was in starbucks with wonder woman");
        sighting2.setLocation(location2);
        sightingService.addSighting(sighting2);

        readHeroSighting.setHero(hero2);
        readHeroSighting.setSighting(sighting2);

        // Act
        heroSightingService.updateHeroSighting(readHeroSighting);

        // Assert
        assertEquals(hero2.getHeroId(), readHeroSighting.getHero().getHeroId());
        assertEquals(sighting2.getSightingId(), readHeroSighting.getSighting().getSightingId());
        assertEquals(createdHeroSighting.getHeroSightingId(), readHeroSighting.getHeroSightingId());

    }

    @Test
    public void retrieveHeroSightingById() {

        // Arrange
        Hero hero = createTestHero();
        Location location = createTestLocation();
        Sighting sighting = createTestSighting(location);

        HeroSighting createdHeroSighting = createTestHeroSighting(hero, sighting);

        // Act
        HeroSighting readHeroSighting = heroSightingService.retrieveHeroSightingById(createdHeroSighting.getHeroSightingId());

        // Assert
        assertEquals(hero.getHeroId(), readHeroSighting.getHero().getHeroId());
        assertEquals(sighting.getSightingId(), readHeroSighting.getSighting().getSightingId());
        assertEquals(createdHeroSighting.getHeroSightingId(), readHeroSighting.getHeroSightingId());
    }

    @Test
    public void retrieveHeroSightingBySighting() {
        Location location = createTestLocation();
        Sighting sighting = createTestSighting(location);
        Hero hero = createTestHero();

        HeroSighting createdHeroSighting = createTestHeroSighting(hero, sighting);

        // Act
        List<HeroSighting> readHS = heroSightingService.retrieveHeroSightingBySighting(sighting.getSightingId());

        // Assert
        assertEquals(1, readHS.size());
        assertEquals(sighting.getSightingId(), readHS.get(0).getSighting().getSightingId());
        assertEquals(hero.getHeroId(), readHS.get(0).getHero().getHeroId());


    }

    @Test
    public void retrieveHeroSightingByHero() {

        Location location = createTestLocation();
        Sighting sighting = createTestSighting(location);
        Hero hero = createTestHero();

        HeroSighting createdHeroSighting = createTestHeroSighting(hero, sighting);

        // Act
        List<HeroSighting> readHS = heroSightingService.retrieveHeroSightingByHero(hero.getHeroId());

        // Assert
        assertEquals(1, readHS.size());
        assertEquals(sighting.getSightingId(), readHS.get(0).getSighting().getSightingId());
        assertEquals(hero.getHeroId(), readHS.get(0).getHero().getHeroId());

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

    private Sighting createTestSighting(Location location) {

        Sighting sighting = new Sighting();
        sighting.setDate(date);
        sighting.setDescription("saw batman...i think");
        sighting.setLocation(location);

        Sighting createdSighting = sightingService.addSighting(sighting);

        return createdSighting;
    }

    private HeroSighting createTestHeroSighting(Hero hero, Sighting sighting) {

        HeroSighting heroSighting = new HeroSighting();
        heroSighting.setHero(hero);
        heroSighting.setSighting(sighting);

        HeroSighting createdHeroSighting = heroSightingService.addHeroSighting(heroSighting);

        return createdHeroSighting;
    }
}