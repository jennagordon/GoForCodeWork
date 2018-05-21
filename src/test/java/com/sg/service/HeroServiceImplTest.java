package com.sg.service;

import com.sg.dao.HeroDao;
import com.sg.dto.*;
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
public class HeroServiceImplTest {

    @Inject
    HeroService heroService;

    @Inject
    LocationService locationService;

    @Inject
    OrganizationService organizationService;

    @Inject
    HeroOrganizationService heroOrganizationService;

    @Inject
    SightingService sightingService;

    @Inject
    HeroSightingService heroSightingService;

    @Inject
    PowerService powerService;

    @Inject
    HeroPowerService heroPowerService;

    LocalDate date = LocalDate.parse("01/01/2018", DateTimeFormatter.ofPattern("dd/MM/yyyy"));


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addHero() {

        // Arrange
        // Act
        Hero createdHero = createTestHero();

        // Assert
        assertEquals("Batman", createdHero.getName());
        assertEquals("Black suit with wings", createdHero.getDescription());
        assertNotNull(createdHero.getHeroId());

    }

    @Test
    public void removeHero() {

        // Arrange
        Hero createdHero = createTestHero();

        // Act
        heroService.removeHero(createdHero);
        Hero readHero = heroService.retrieveHero(createdHero.getHeroId());

        // Assert
        assertNull(readHero);
    }

    @Test
    public void updateHero() {

        // Arrange
        Hero createdHero = createTestHero();

        Hero readHero = heroService.retrieveHero(createdHero.getHeroId());

        readHero.setName("Superman");
        readHero.setDescription("Red and Blue suit with cape");

        heroService.updateHero(readHero);

        assertEquals("Superman", readHero.getName());
        assertEquals("Red and Blue suit with cape", readHero.getDescription());
        assertEquals(createdHero.getHeroId(), readHero.getHeroId());
    }

    @Test
    public void retrieveAllHero() {

        // Arrange
        createTestHero();

        Hero hero = new Hero();
        hero.setName("Superman");
        hero.setDescription("Red and Blue suit with cape");

        heroService.addHero(hero);

        List<Hero> heroList = heroService.retrieveAllHero(Integer.MAX_VALUE, 0);

        assertEquals(2, heroList.size());

    }

    @Test
    public void retrieveHero() {

        Hero createHero = createTestHero();

        assertNotNull(createHero.getHeroId());

        Hero readHero = heroService.retrieveHero(createHero.getHeroId());

        assertEquals("Batman", readHero.getName());
        assertEquals("Black suit with wings", readHero.getDescription());
        assertEquals(createHero.getHeroId(), readHero.getHeroId());

    }

    @Test
    public void retrieveHeroesByPowerPage() {

        // Arrange
        // paging in 2 but creating 3
        int numberOfHeroes = 2;
        Power power = createTestPower();


        Hero hero1 = new Hero();
        hero1.setName("Batman");
        hero1.setDescription("Black suit with wings");

        Hero createdHero1 = heroService.addHero(hero1);

        HeroPower heroPower1 = new HeroPower();
        heroPower1.setHero(createdHero1);
        heroPower1.setPower(power);
        heroPowerService.addHeroPower(heroPower1);

        Hero hero2 = new Hero();
        hero2.setName("Wonder Woman");
        hero2.setDescription("has a rope");

        Hero createdHero2 = heroService.addHero(hero2);

        HeroPower heroPower2 = new HeroPower();
        heroPower2.setHero(createdHero2);
        heroPower2.setPower(power);
        heroPowerService.addHeroPower(heroPower2);

        Hero hero3 = new Hero();
        hero3.setName("Thor");
        hero3.setDescription("has a hammer");

        Hero createdHero3 = heroService.addHero(hero3);

        HeroPower heroPower3 = new HeroPower();
        heroPower3.setHero(createdHero3);
        heroPower3.setPower(power);
        heroPowerService.addHeroPower(heroPower3);



        // Act
        List<Hero> heroList = heroService.retrieveHeroByPower(power, 2, 0);

        // Assert
        // confirm that we get back the expected size of heroes with the same power
        assertHeroesOnPower(power, numberOfHeroes, heroList);
    }


    @Test
    public void retrieveHeroByPower() {

        // we want to test that we can get all heroes with the same power

        // Arrange
        // add multiple heroes with the same power
        int numberOfHeroes = 3;

        Power power = createTestPower();

        Hero hero1 = new Hero();
        hero1.setName("Batman");
        hero1.setDescription("Black suit with wings");

        Hero createdHero1 = heroService.addHero(hero1);

        HeroPower heroPower1 = new HeroPower();
        heroPower1.setHero(createdHero1);
        heroPower1.setPower(power);
        heroPowerService.addHeroPower(heroPower1);

        Hero hero2 = new Hero();
        hero2.setName("Wonder Woman");
        hero2.setDescription("has a rope");

        Hero createdHero2 = heroService.addHero(hero2);

        HeroPower heroPower2 = new HeroPower();
        heroPower2.setHero(createdHero2);
        heroPower2.setPower(power);
        heroPowerService.addHeroPower(heroPower2);

        Hero hero3 = new Hero();
        hero3.setName("Thor");
        hero3.setDescription("has a hammer");

        Hero createdHero3 = heroService.addHero(hero3);

        HeroPower heroPower3 = new HeroPower();
        heroPower3.setHero(createdHero3);
        heroPower3.setPower(power);
        heroPowerService.addHeroPower(heroPower3);


        // Act
        // get the list back
        List<Hero> heroList = heroService.retrieveHeroByPower(power, Integer.MAX_VALUE, 0);

        // Assert
        // confirm that we get back the expected size of heroes with the same power
        assertHeroesOnPower(power, numberOfHeroes, heroList);
    }

    @Test
    public void retrieveHeroesBySightingPage() {
        // we want to test that we can get all heroes with the same sighting

        // paging in 2 but creating 3
        int numberOfHeroes = 2;

        Location location = createTestLocation();
        Sighting sighting = createTestSighting(location);

        Hero hero1 = new Hero();
        hero1.setName("Batman");
        hero1.setDescription("Black suit with wings");

        Hero createdHero1 = heroService.addHero(hero1);

        HeroSighting heroSighting1 = new HeroSighting();
        heroSighting1.setHero(createdHero1);
        heroSighting1.setSighting(sighting);
        heroSightingService.addHeroSighting(heroSighting1);

        Hero hero2 = new Hero();
        hero2.setName("Wonder Woman");
        hero2.setDescription("has a rope");

        Hero createdHero2 = heroService.addHero(hero2);

        HeroSighting heroSighting2 = new HeroSighting();
        heroSighting2.setHero(createdHero2);
        heroSighting2.setSighting(sighting);
        heroSightingService.addHeroSighting(heroSighting2);

        Hero hero3 = new Hero();
        hero3.setName("Thor");
        hero3.setDescription("has a hammer");

        Hero createdHero3 = heroService.addHero(hero3);

        HeroSighting heroSighting3 = new HeroSighting();
        heroSighting3.setHero(createdHero3);
        heroSighting3.setSighting(sighting);
        heroSightingService.addHeroSighting(heroSighting3);


        // Act
        // get the list back
        List<Hero> heroList = heroService.retrieveHeroBySighting(sighting, 2, 0);

        // Assert
        // confirm that we get back the expected size of heroes with the same organization
        assertHeroesOnSighting(sighting, numberOfHeroes, heroList);

    }

    @Test
    public void retrieveHeroBySighting() {
        // we want to test that we can get all heroes with the same sighting

        int numberOfHeroes = 3;

        Location location = createTestLocation();
        Sighting sighting = createTestSighting(location);

        Hero hero1 = new Hero();
        hero1.setName("Batman");
        hero1.setDescription("Black suit with wings");

        Hero createdHero1 = heroService.addHero(hero1);

        HeroSighting heroSighting1 = new HeroSighting();
        heroSighting1.setHero(createdHero1);
        heroSighting1.setSighting(sighting);
        heroSightingService.addHeroSighting(heroSighting1);

        Hero hero2 = new Hero();
        hero2.setName("Wonder Woman");
        hero2.setDescription("has a rope");

        Hero createdHero2 = heroService.addHero(hero2);

        HeroSighting heroSighting2 = new HeroSighting();
        heroSighting2.setHero(createdHero2);
        heroSighting2.setSighting(sighting);
        heroSightingService.addHeroSighting(heroSighting2);

        Hero hero3 = new Hero();
        hero3.setName("Thor");
        hero3.setDescription("has a hammer");

        Hero createdHero3 = heroService.addHero(hero3);

        HeroSighting heroSighting3 = new HeroSighting();
        heroSighting3.setHero(createdHero3);
        heroSighting3.setSighting(sighting);
        heroSightingService.addHeroSighting(heroSighting3);


        // Act
        // get the list back
        List<Hero> heroList = heroService.retrieveHeroBySighting(sighting, Integer.MAX_VALUE, 0);

        // Assert
        // confirm that we get back the expected size of heroes with the same organization
        assertHeroesOnSighting(sighting, numberOfHeroes, heroList);
    }

    @Test
    public void retrieveHeroesByOrganizationPage() {
        // paging in 2 but creating 3
        int numberOfHeroes = 2;

        Location location = createTestLocation();
        Organization organization = createTestOrganization(location);

        Hero hero1 = new Hero();
        hero1.setName("Batman");
        hero1.setDescription("Black suit with wings");

        Hero createdHero1 = heroService.addHero(hero1);

        HeroOrganization heroOrganization1 = new HeroOrganization();
        heroOrganization1.setOrganization(organization);
        heroOrganization1.setHero(createdHero1);
        heroOrganizationService.addHeroOrganization(heroOrganization1);

        Hero hero2 = new Hero();
        hero2.setName("Wonder Woman");
        hero2.setDescription("has a rope");

        Hero createdHero2 = heroService.addHero(hero2);

        HeroOrganization heroOrganization2 = new HeroOrganization();
        heroOrganization2.setOrganization(organization);
        heroOrganization2.setHero(createdHero2);
        heroOrganizationService.addHeroOrganization(heroOrganization2);

        Hero hero3 = new Hero();
        hero3.setName("Thor");
        hero3.setDescription("has a hammer");

        Hero createdHero3 = heroService.addHero(hero3);

        HeroOrganization heroOrganization3 = new HeroOrganization();
        heroOrganization3.setOrganization(organization);
        heroOrganization3.setHero(createdHero3);
        heroOrganizationService.addHeroOrganization(heroOrganization3);


        // Act
        // get the list back
        List<Hero> heroList = heroService.retrieveHeroByOrganization(organization, 2, 0);

        // Assert
        // confirm that we get back the expected size of heroes with the same organization
        assertHeroesOnOrganization(organization, numberOfHeroes, heroList);

    }

    @Test
    public void retrieveHeroByOrganization() {
        // we want to test that we can get all heroes with the same organization

        // Arrange
        // add multiple heroes with the same organization
        int numberOfHeroes = 3;

        Location location = createTestLocation();
        Organization organization = createTestOrganization(location);

        Hero hero1 = new Hero();
        hero1.setName("Batman");
        hero1.setDescription("Black suit with wings");

        Hero createdHero1 = heroService.addHero(hero1);

        HeroOrganization heroOrganization1 = new HeroOrganization();
        heroOrganization1.setOrganization(organization);
        heroOrganization1.setHero(createdHero1);
        heroOrganizationService.addHeroOrganization(heroOrganization1);

        Hero hero2 = new Hero();
        hero2.setName("Wonder Woman");
        hero2.setDescription("has a rope");

        Hero createdHero2 = heroService.addHero(hero2);

        HeroOrganization heroOrganization2 = new HeroOrganization();
        heroOrganization2.setOrganization(organization);
        heroOrganization2.setHero(createdHero2);
        heroOrganizationService.addHeroOrganization(heroOrganization2);

        Hero hero3 = new Hero();
        hero3.setName("Thor");
        hero3.setDescription("has a hammer");

        Hero createdHero3 = heroService.addHero(hero3);

        HeroOrganization heroOrganization3 = new HeroOrganization();
        heroOrganization3.setOrganization(organization);
        heroOrganization3.setHero(createdHero3);
        heroOrganizationService.addHeroOrganization(heroOrganization3);


        // Act
        // get the list back
        List<Hero> heroList = heroService.retrieveHeroByOrganization(organization, Integer.MAX_VALUE, 0);

        // Assert
        // confirm that we get back the expected size of heroes with the same organization
        assertHeroesOnOrganization(organization, numberOfHeroes, heroList);
    }


    // HELPER METHODS TO CREATE TEST DATA

    private Hero createTestHero() {

        Hero hero = new Hero();
        hero.setName("Batman");
        hero.setDescription("Black suit with wings");

        Hero createdHero = heroService.addHero(hero);

        return createdHero;
    }

    private Organization createTestOrganization(Location location) {

        Organization organization = new Organization();
        organization.setName("Crime Fighters");
        organization.setDescription("Fighting Crime every day");
        organization.setLocation(location);

        Organization createdOrganization = organizationService.addOrganization(organization);

        return createdOrganization;
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

    private void assertHeroesOnOrganization(Organization organization, int numberOfHeroes, List<Hero> resultHeroes) {
        assertEquals(numberOfHeroes, resultHeroes.size());

        for (Hero hero : resultHeroes) {
            List<Organization> organizationList = organizationService.retrieveOrganizationByHero(hero, Integer.MAX_VALUE, 0);

            // Loop through hero's organization and verify it contains the one we added
            boolean containsOrganization = false;
            for (Organization org : organizationList) {
                if (org.getOrganizationId().equals(organization.getOrganizationId())) {
                    containsOrganization = true;

                }

                assertEquals(true, containsOrganization);
            }

        }
    }

    private Sighting createTestSighting(Location location) {

        Sighting sighting = new Sighting();
        sighting.setDate(date);
        sighting.setDescription("saw batman...i think");
        sighting.setLocation(location);

        Sighting createdSighting = sightingService.addSighting(sighting);

        return createdSighting;
    }

    private void assertHeroesOnSighting(Sighting sighting, int numberOfHeroes, List<Hero> resultHeroes) {
        assertEquals(numberOfHeroes, resultHeroes.size());

        for (Hero hero : resultHeroes) {
            List<Sighting> sightingList = sightingService.retrieveSightingByHero(hero, Integer.MAX_VALUE, 0);

            // Loop through hero's organization and verify it contains the one we added
            boolean containsSighting = false;
            for (Sighting sig : sightingList) {
                if (sig.getSightingId().equals(sighting.getSightingId())) {
                    containsSighting = true;

                }

                assertEquals(true, containsSighting);
            }

        }
    }

    private Power createTestPower() {
        Power power = new Power();
        power.setName("Mind Reader");
        power.setDescription("Reads minds");

        Power createdPower = powerService.addPower(power);

        return createdPower;
    }

    private void assertHeroesOnPower(Power power, int numberOfHeroes, List<Hero> resultHeroes) {
        assertEquals(numberOfHeroes, resultHeroes.size());

        for (Hero hero : resultHeroes) {
            List<Power> powerList = powerService.retrievePowerByHero(hero, Integer.MAX_VALUE, 0);

            // Loop through hero's power and verify it contains the one we added
            boolean containsPower = false;
            for (Power pow : powerList) {
                if (pow.getPowerId().equals(power.getPowerId())) {
                    containsPower = true;

                }

                assertEquals(true, containsPower);
            }

        }
    }
}