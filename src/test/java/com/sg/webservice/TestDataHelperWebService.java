package com.sg.webservice;

import com.sg.dto.*;
import com.sg.service.*;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TestDataHelperWebService {

    private LocationService locationService;
    private OrganizationService organizationService;
    private PowerService powerService;
    private SightingService sightingService;
    private HeroService heroService;


    LocalDate date = LocalDate.parse("01/01/2018", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    LocalDate date2 = LocalDate.parse("22/03/2018", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    @Inject
    public TestDataHelperWebService(LocationService locationService, OrganizationService organizationService, PowerService powerService,
                                    SightingService sightingService, HeroService heroService) {
        this.locationService = locationService;
        this.organizationService = organizationService;
        this.powerService = powerService;
        this.sightingService = sightingService;
        this.heroService = heroService;
    }


    // ---- LOCATION HELPER METHODS ----
    public Location createTestLocation() {

        Location location = new Location();
        location.setName("Starbucks");
        location.setDescription("coffee shop");
        location.setStreet("123 Main St");
        location.setCity("Deerfield");
        location.setState("MA");
        location.setZip("01373");
        location.setLatitude("24.010");
        location.setLongitude("45.987");
        Location createdLocation = locationService.addLocation(location);

        return createdLocation;
    }

    public Location createSecondTestLocation() {

        Location location = new Location();
        location.setName("Hair Salon");
        location.setDescription("hair station");
        location.setStreet("123 Main St");
        location.setCity("Deerfield");
        location.setState("MA");
        location.setZip("01373");
        location.setLatitude("24.010");
        location.setLongitude("45.987");
        Location createdLocation = locationService.addLocation(location);

        return createdLocation;
    }

    public List<Location> createTestLocations(int numberOfLocations) {

        List<Location> locations = new ArrayList<>();

        for (int i = 0; i < numberOfLocations; i++) {
            Location location = new Location();
            location.setName("Starbucks" + i);
            location.setDescription("grey building" + i);
            location.setStreet("123 Main St" + i);
            location.setCity("Deerfield" + i);
            location.setState("MA" + i);
            location.setZip("01373" + i);
            location.setLatitude("24.010" + i);
            location.setLongitude("45.987" + i);
            Location createdLocation = locationService.addLocation(location);

            locations.add(createdLocation);
        }

        return locations;
    }

    // ---- ORGANIZATION HELPER METHODS ----
    public Organization createTestOrganization(Location location) {

        Organization organization = new Organization();
        organization.setName("Crime Fighters");
        organization.setDescription("Fighting Crime every day");
        organization.setLocation(location);

        Organization createdOrganization = organizationService.addOrganization(organization);

        return createdOrganization;
    }

    public List<Organization> createTestOrganizations(int numberOfOrganizations, Location location) {

        List<Organization> organizationList = new ArrayList<>();

        for (int i = 0; i < numberOfOrganizations; i++) {

            Organization organization = new Organization();
            organization.setName("Crime Fighters" + i);
            organization.setDescription("Fighting Crime every day" + i);
            organization.setLocation(location);

            Organization createdOrganization = organizationService.addOrganization(organization);

            organizationList.add(createdOrganization);
        }
        return organizationList;
    }

    public List<Organization> createSecondTestOrganizations(int numberOfOrganizations, Location location) {

        List<Organization> organizationList = new ArrayList<>();

        for (int i = 0; i < numberOfOrganizations; i++) {

            Organization organization = new Organization();
            organization.setName("Avengers" + i);
            organization.setDescription("we avenge stuff" + i);
            organization.setLocation(location);

            Organization createdOrganization = organizationService.addOrganization(organization);

            organizationList.add(createdOrganization);
        }
        return organizationList;
    }

    // ---- POWER HELPER METHODS ----
    public Power createTestPower() {
        Power power = new Power();
        power.setName("Mind Reader");
        power.setDescription("ability to read minds");

        Power createdPower = powerService.addPower(power);

        return createdPower;
    }

    public List<Power> createTestPowers(int numberOfPowers) {

        List<Power> powers = new ArrayList<>();

        for (int i = 0; i < numberOfPowers; i++) {
            Power power = new Power();
            power.setName("Mind Reader" + i);
            power.setDescription("ability to read minds" + i);

            Power createdPower = powerService.addPower(power);
            powers.add(createdPower);
        }
        return powers;
    }

    public List<Power> createSecondTestPowers(int numberOfPowers) {

        List<Power> powers = new ArrayList<>();

        for (int i = 0; i < numberOfPowers; i++) {
            Power power = new Power();
            power.setName("Flight" + i);
            power.setDescription("ability to fly" + i);

            Power createdPower = powerService.addPower(power);
            powers.add(createdPower);
        }
        return powers;
    }

    // ---- SIGHTING HELPER METHODS ----
    public Sighting createTestSighting(Location location) {

        Sighting sighting = new Sighting();
        sighting.setDate(date);
        sighting.setDescription("saw Superman at chick-fil-a");
        sighting.setLocation(location);

        Sighting createdSighting = sightingService.addSighting(sighting);

        return createdSighting;

    }

    public List<Sighting> createTestSightings(int numberOfSightings, Location location) {

        List<Sighting> sightings = new ArrayList<>();

        for (int i = 0; i < numberOfSightings; i++) {
            Sighting sighting = new Sighting();
            sighting.setDate(date);
            sighting.setDescription("saw Superman at chick-fil-a" + i);
            sighting.setLocation(location);

            Sighting createdSighting = sightingService.addSighting(sighting);
            sightings.add(createdSighting);
        }
        return sightings;
    }


    // ---- HERO HELPER METHODS ----
    public Hero createTestHero() {

        Hero hero = new Hero();
        hero.setName("Batman");
        hero.setDescription("black suit with wings");

        Hero createdHero = heroService.addHero(hero);

        return createdHero;
    }

    public List<Hero> createTestHeroes(int numberOfHeroes) {
        List<Hero> heroList = new ArrayList<>();

        for (int i = 0; i < numberOfHeroes; i++) {
            Hero hero = new Hero();
            hero.setName("Batman" + i);
            hero.setDescription("black suit with wings" + i);

            Hero createdHero = heroService.addHero(hero);
            heroList.add(createdHero);
        }
        return heroList;
    }

    public List<Hero> createSecondTestHeroes(int numberOfHeroes) {
        List<Hero> heroList = new ArrayList<>();

        for (int i = 0; i < numberOfHeroes; i++) {
            Hero hero = new Hero();
            hero.setName("Invisible Woman" + i);
            hero.setDescription("can you see her?" + i);

            Hero createdHero = heroService.addHero(hero);
            heroList.add(createdHero);
        }
        return heroList;
    }
}
