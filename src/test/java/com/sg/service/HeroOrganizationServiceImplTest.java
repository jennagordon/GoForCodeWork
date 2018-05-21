package com.sg.service;

import com.sg.dao.HeroOrganizationDao;
import com.sg.dto.Hero;
import com.sg.dto.HeroOrganization;
import com.sg.dto.Location;
import com.sg.dto.Organization;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class HeroOrganizationServiceImplTest {

    @Inject
    HeroOrganizationService heroOrganizationService;

    @Inject
    HeroService heroService;

    @Inject
    OrganizationService organizationService;

    @Inject
    LocationService locationService;




    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addHeroOrganization() {

        // Arrange
        // Act
        Location location = createTestLocation();
        Organization organization = createTestOrganization(location);
        Hero hero = createTestHero();

        HeroOrganization heroOrganization = createTestHeroOrganization(hero, organization);

        // Assert
        assertNotNull(heroOrganization.getHeroOrganizationId());
        assertEquals(hero.getHeroId(), heroOrganization.getHero().getHeroId());
        assertEquals(organization.getOrganizationId(), heroOrganization.getOrganization().getOrganizationId());
    }



    @Test
    public void removeHeroOrganization() {

        // Arrange
        Location location = createTestLocation();
        Organization organization = createTestOrganization(location);
        Hero hero = createTestHero();

        HeroOrganization heroOrganization = createTestHeroOrganization(hero, organization);

        // Act
        heroOrganizationService.removeHeroOrganization(heroOrganization);
        HeroOrganization readHeroOrganization = heroOrganizationService.retrieveHeroOrganizationById(heroOrganization.getHeroOrganizationId());

        // Assert
        assertNull(readHeroOrganization);
    }

    @Test
    public void updateHeroOrganization() {

        // Arrange
        Location location = createTestLocation();
        Organization organization = createTestOrganization(location);
        Hero hero = createTestHero();

        HeroOrganization heroOrganization = createTestHeroOrganization(hero, organization);

        HeroOrganization readHeroOrganization = heroOrganizationService.retrieveHeroOrganizationById(heroOrganization.getHeroOrganizationId());

        // setting updates
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

        Organization organization2 = new Organization();
        organization2.setName("Avengers");
        organization2.setDescription("we avenge stuff");
        organization2.setLocation(location2);
        organizationService.addOrganization(organization2);

        Hero hero2 = new Hero();
        hero2.setName("Superman");
        hero2.setDescription("Red and Blue suit with cape");
        heroService.addHero(hero2);

        readHeroOrganization.setHero(hero2);
        readHeroOrganization.setOrganization(organization2);

        // Act
        heroOrganizationService.updateHeroOrganization(readHeroOrganization);

        // Assert
        assertEquals(hero2.getHeroId(), readHeroOrganization.getHero().getHeroId());
        assertEquals(organization2.getOrganizationId(), readHeroOrganization.getOrganization().getOrganizationId());
        assertEquals(heroOrganization.getHeroOrganizationId(), readHeroOrganization.getHeroOrganizationId());
    }

    @Test
    public void retrieveHeroOrganizationById() {

        // Arrange
        Location location = createTestLocation();
        Organization organization = createTestOrganization(location);
        Hero hero = createTestHero();

        HeroOrganization heroOrganization = createTestHeroOrganization(hero, organization);

        // Act
        HeroOrganization readHeroOrganization = heroOrganizationService.retrieveHeroOrganizationById(heroOrganization.getHeroOrganizationId());

        // Assert
        assertEquals(hero.getHeroId(), readHeroOrganization.getHero().getHeroId());
        assertEquals(organization.getOrganizationId(), readHeroOrganization.getOrganization().getOrganizationId());
        assertEquals(heroOrganization.getHeroOrganizationId(), readHeroOrganization.getHeroOrganizationId());

    }

    @Test
    public void retrieveHeroOrganizationByOrganization() {
        Location location = createTestLocation();
        Organization organization = createTestOrganization(location);
        Hero hero = createTestHero();
        HeroOrganization heroOrg = createTestHeroOrganization(hero, organization);

        // Act
        List<HeroOrganization> heroOrgs = heroOrganizationService.retrieveHeroOrganizationByOrganization(organization.getOrganizationId());

        // Assert
        assertEquals(1, heroOrgs.size());
        assertEquals(organization.getOrganizationId(), heroOrgs.get(0).getOrganization().getOrganizationId());
        assertEquals(hero.getHeroId(), heroOrgs.get(0).getHero().getHeroId());

    }

    @Test
    public void retrieveHeroOrganizationByHero() {
        Location location = createTestLocation();
        Organization organization = createTestOrganization(location);
        Hero hero = createTestHero();
        HeroOrganization heroOrg = createTestHeroOrganization(hero, organization);

        // Act
        List<HeroOrganization> heroOrgs = heroOrganizationService.retrieveHeroOrganizationByHero(hero.getHeroId());

        // Assert
        assertEquals(1, heroOrgs.size());
        assertEquals(organization.getOrganizationId(), heroOrgs.get(0).getOrganization().getOrganizationId());
        assertEquals(hero.getHeroId(), heroOrgs.get(0).getHero().getHeroId());

    }

    // HELPER METHODS
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

    private Organization createTestOrganization(Location location) {

        Organization organization = new Organization();
        organization.setName("Crime Fighters");
        organization.setDescription("Fighting Crime every day");
        organization.setLocation(location);

        Organization createdOrganization = organizationService.addOrganization(organization);

        return createdOrganization;
    }

    private HeroOrganization createTestHeroOrganization(Hero hero, Organization organization) {
        HeroOrganization heroOrganization = new HeroOrganization();
        heroOrganization.setHero(hero);
        heroOrganization.setOrganization(organization);

        HeroOrganization createdHeroOrganization = heroOrganizationService.addHeroOrganization(heroOrganization);
        return createdHeroOrganization;
    }
}