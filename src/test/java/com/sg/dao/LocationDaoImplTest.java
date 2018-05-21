package com.sg.dao;

import com.sg.dto.Location;
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
public class LocationDaoImplTest {

    @Inject
    LocationDao locationDao;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addLocation() {

        // Arrange
        // Act
        Location createdLocation = createTestLocation();

        // Assert
        assertEquals("Starbucks", createdLocation.getName());
        assertEquals("grey building", createdLocation.getDescription());
        assertEquals("123 Main St", createdLocation.getStreet());
        assertEquals("Deerfield", createdLocation.getCity());
        assertEquals("MA", createdLocation.getState());
        assertEquals("01373", createdLocation.getZip());
        assertEquals("24.010", createdLocation.getLatitude());
        assertEquals("45.987", createdLocation.getLongitude());
        assertNotNull(createdLocation.getLocationId());

    }

    @Test
    public void removeLocation() {

        // Arrange
        Location createdLocation = createTestLocation();

        // Act
        locationDao.removeLocation(createdLocation);
        Location readLocation = locationDao.retrieveLocation(createdLocation.getLocationId());

        // Arrange
        assertNull(readLocation);
    }

    @Test
    public void updateLocation() {

        // Arrange
        Location createdLocation = createTestLocation();

        Location readLocation = locationDao.retrieveLocation(createdLocation.getLocationId());

        readLocation.setName("Hair Salon");
        readLocation.setDescription("red building");
        readLocation.setStreet("150 Main St");
        readLocation.setCity("South Deerfield");
        readLocation.setState("NH");
        readLocation.setZip("03856");
        readLocation.setLatitude("54.010");
        readLocation.setLongitude("81.987");

        // Act
        locationDao.updateLocation(readLocation);

        // Assert
        assertEquals("Hair Salon", readLocation.getName());
        assertEquals("red building", readLocation.getDescription());
        assertEquals("150 Main St", readLocation.getStreet());
        assertEquals("South Deerfield", readLocation.getCity());
        assertEquals("NH", readLocation.getState());
        assertEquals("03856", readLocation.getZip());
        assertEquals("54.010", readLocation.getLatitude());
        assertEquals("81.987", readLocation.getLongitude());
        assertEquals(createdLocation.getLocationId(), readLocation.getLocationId());


    }

    @Test
    public void retrieveAllLocations() {

        Location location1 = createTestLocation();

        Location location2 = new Location();
        location2.setName("Hair Salon");
        location2.setDescription("red building");
        location2.setStreet("150 Main St");
        location2.setCity("South Deerfield");
        location2.setState("NH");
        location2.setZip("03856");
        location2.setLatitude("54.010");
        location2.setLongitude("81.987");

        locationDao.addLocation(location2);

        // Act
        List<Location> locationList = locationDao.retrieveAllLocations(Integer.MAX_VALUE, 0);

        // Assert
        assertEquals(2, locationList.size());


    }

    @Test
    public void retrieveLocation() {

        // Arrange
        Location createdLocation = createTestLocation();

        // Act
        assertNotNull(createdLocation.getLocationId());
        Location readLocation = locationDao.retrieveLocation(createdLocation.getLocationId());

        // Assert
        assertEquals("Starbucks", readLocation.getName());
        assertEquals("grey building", readLocation.getDescription());
        assertEquals("123 Main St", readLocation.getStreet());
        assertEquals("Deerfield", readLocation.getCity());
        assertEquals("MA", readLocation.getState());
        assertEquals("01373", readLocation.getZip());
        assertEquals("24.010", readLocation.getLatitude());
        assertEquals("45.987", readLocation.getLongitude());
        assertEquals(createdLocation.getLocationId(), readLocation.getLocationId());
    }


    // HELPER METHODS TO GENERATE TEST DATA
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
        Location createdLocation = locationDao.addLocation(location);

        return createdLocation;
    }
}