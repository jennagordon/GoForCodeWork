package com.sg.dao;

import com.sg.dto.Hero;
import com.sg.dto.HeroOrganization;
import com.sg.dto.Location;
import com.sg.dto.Organization;
import com.sg.service.HeroOrganizationService;
import com.sg.service.HeroService;
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

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class OrganizationDaoImplTest {

    @Inject
    OrganizationDao organizationDao;

    @Inject
    LocationService locationService;

    @Inject
    HeroService heroService;

    @Inject
    HeroOrganizationService heroOrganizationService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addOrganization() {

        // Arrange
        // Act
        Location location = createTestLocation();

        Organization createdOrganization = createTestOrganization(location);

        // Assert
        assertEquals("Crime Fighters", createdOrganization.getName());
        assertEquals("Fighting Crime every day", createdOrganization.getDescription());
        assertEquals(location.getLocationId(), createdOrganization.getLocation().getLocationId());
        assertNotNull(createdOrganization.getOrganizationId());
    }

    @Test
    public void removeOrganization() {
        // Arrange
        Location location = createTestLocation();

        Organization createdOrganization = createTestOrganization(location);

        // Act
        organizationDao.removeOrganization(createdOrganization);
        Organization readOrganization = organizationDao.retrieveOrganization(createdOrganization.getOrganizationId());

        // Assert
        assertNull(readOrganization);

    }

    @Test
    public void updateOrganization() {
        // Arrange
        Location location = createTestLocation();

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

        Organization createdOrganization = createTestOrganization(location);
        Organization readOrganization = organizationDao.retrieveOrganization(createdOrganization.getOrganizationId());

        readOrganization.setName("Avengers");
        readOrganization.setDescription("we avenge stuff");
        readOrganization.setLocation(location2);

        // Act
        organizationDao.updateOrganization(readOrganization);

        // Assert
        assertEquals("Avengers", readOrganization.getName());
        assertEquals("we avenge stuff", readOrganization.getDescription());
        assertEquals(createdOrganization.getOrganizationId(), readOrganization.getOrganizationId());
        assertEquals(location2.getLocationId(), readOrganization.getLocation().getLocationId());

    }

    @Test
    public void retrieveAllOrganizations() {

        // Arrange
        Location location = createTestLocation();
        createTestOrganization(location);

        Organization organization2 = new Organization();
        organization2.setName("Avengers");
        organization2.setDescription("we avenge stuff");
        organization2.setLocation(location);
        organizationDao.addOrganization(organization2);

        // Act
        List<Organization> organizationList = organizationDao.retrieveAllOrganizations(Integer.MAX_VALUE, 0);

        // Assert
        assertEquals(2, organizationList.size());
    }

    @Test
    public void retrieveOrganization() {

        // Arrange
        Location location = createTestLocation();
        Organization createdOrganization = createTestOrganization(location);

        // Act
        Organization readOrganization = organizationDao.retrieveOrganization(createdOrganization.getOrganizationId());

        // Assert
        assertEquals("Crime Fighters", readOrganization.getName());
        assertEquals("Fighting Crime every day", readOrganization.getDescription());
        assertEquals(location.getLocationId(), readOrganization.getLocation().getLocationId());
        assertEquals(createdOrganization.getOrganizationId(), readOrganization.getOrganizationId());
    }

    @Test
    public void retrieveOrganizationByHeroPage() {

        // Arrange
        // paging by 2 but creating 3
        int numberOfOrganizations = 2;
        Hero hero = createTestHero();
        Location location = createTestLocation();

        // number 1
        Organization organization1 = new Organization();
        organization1.setName("Crime Fighters");
        organization1.setDescription("Fighting Crime every day");
        organization1.setLocation(location);

        Organization createdOrganization1 = organizationDao.addOrganization(organization1);

        HeroOrganization heroOrganization1 = new HeroOrganization();
        heroOrganization1.setHero(hero);
        heroOrganization1.setOrganization(createdOrganization1);
        heroOrganizationService.addHeroOrganization(heroOrganization1);

        // number 2
        Organization organization2 = new Organization();
        organization2.setName("Avengers");
        organization2.setDescription("let's avenge");
        organization2.setLocation(location);

        Organization createdOrganization2 = organizationDao.addOrganization(organization2);

        HeroOrganization heroOrganization2 = new HeroOrganization();
        heroOrganization2.setHero(hero);
        heroOrganization2.setOrganization(createdOrganization2);
        heroOrganizationService.addHeroOrganization(heroOrganization2);

        // number 3
        Organization organization3 = new Organization();
        organization3.setName("Justice League");
        organization3.setDescription("all about justice");
        organization3.setLocation(location);

        Organization createdOrganization3 = organizationDao.addOrganization(organization3);

        HeroOrganization heroOrganization3 = new HeroOrganization();
        heroOrganization3.setHero(hero);
        heroOrganization3.setOrganization(createdOrganization3);
        heroOrganizationService.addHeroOrganization(heroOrganization3);

        // Act
        List<Organization> organizationList = organizationDao.retrieveOrganizationByHero(hero, 2, 0);

        // Assert
        assertOrganizationsOnHero(hero, numberOfOrganizations, organizationList);

    }

    @Test
    public void retrieveOrganizationByHero() {

        // Arrange
        int numberOfOrganizations = 3;
        Hero hero = createTestHero();
        Location location = createTestLocation();

        // number 1
        Organization organization1 = new Organization();
        organization1.setName("Crime Fighters");
        organization1.setDescription("Fighting Crime every day");
        organization1.setLocation(location);

        Organization createdOrganization1 = organizationDao.addOrganization(organization1);

        HeroOrganization heroOrganization1 = new HeroOrganization();
        heroOrganization1.setHero(hero);
        heroOrganization1.setOrganization(createdOrganization1);
        heroOrganizationService.addHeroOrganization(heroOrganization1);

        // number 2
        Organization organization2 = new Organization();
        organization2.setName("Avengers");
        organization2.setDescription("let's avenge");
        organization2.setLocation(location);

        Organization createdOrganization2 = organizationDao.addOrganization(organization2);

        HeroOrganization heroOrganization2 = new HeroOrganization();
        heroOrganization2.setHero(hero);
        heroOrganization2.setOrganization(createdOrganization2);
        heroOrganizationService.addHeroOrganization(heroOrganization2);

        // number 3
        Organization organization3 = new Organization();
        organization3.setName("Justice League");
        organization3.setDescription("all about justice");
        organization3.setLocation(location);

        Organization createdOrganization3 = organizationDao.addOrganization(organization3);

        HeroOrganization heroOrganization3 = new HeroOrganization();
        heroOrganization3.setHero(hero);
        heroOrganization3.setOrganization(createdOrganization3);
        heroOrganizationService.addHeroOrganization(heroOrganization3);

        // Act
        List<Organization> organizationList = organizationDao.retrieveOrganizationByHero(hero, Integer.MAX_VALUE, 0);

        // Assert
        assertOrganizationsOnHero(hero, numberOfOrganizations, organizationList);

    }


    // HELPER METHODS TO CREATE TEST DATA

    private Organization createTestOrganization(Location location) {

        Organization organization = new Organization();
        organization.setName("Crime Fighters");
        organization.setDescription("Fighting Crime every day");
        organization.setLocation(location);

        Organization createdOrganization = organizationDao.addOrganization(organization);

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

    private Hero createTestHero() {

        Hero hero = new Hero();
        hero.setName("Batman");
        hero.setDescription("Black suit with wings");

        Hero createdHero = heroService.addHero(hero);

        return createdHero;
    }

    private void assertOrganizationsOnHero(Hero hero, int numberOfOrganizations, List<Organization> resultOrganizations) {
        assertEquals(numberOfOrganizations, resultOrganizations.size());

        for (Organization organization : resultOrganizations) {
            List<Hero> heroList = heroService.retrieveHeroByOrganization(organization, Integer.MAX_VALUE, 0);

            // Loop through organization's heroes and verify it contains the one we added
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