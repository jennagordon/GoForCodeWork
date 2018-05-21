package com.sg.webservice;

import com.sg.dto.Location;
import com.sg.model.commandmodel.location.editlocation.LocationEditCommandModel;
import com.sg.model.commandmodel.location.locationpage.LocationPageCreateCommandModel;
import com.sg.model.viewmodel.location.detailslocation.LocationDetailsViewModel;
import com.sg.model.viewmodel.location.editlocation.LocationEditViewModel;
import com.sg.model.viewmodel.location.locationpage.LocationPageViewModel;
import com.sg.model.viewmodel.location.locationpage.LocationViewModel;
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
public class LocationWebServiceImplTest {

    @Inject
    LocationWebService locationWebService;

    @Inject
    TestDataHelperWebService testData;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void retrieveLocationPageViewModel() {

        // testing we can retrieve the LPVM object

        // Arrange
        // create list of locations
        List<Location> locationList = testData.createTestLocations(12);

        // Act
        LocationPageViewModel lpvm = locationWebService.retrieveLocationPageViewModel(Integer.MAX_VALUE, 0, 1);

        // Assert
        assertEquals(locationList.size(), lpvm.getLocations().size());
        assertNotNull(lpvm.getLocationPageCreateCommandModel());

        for(LocationViewModel locationViewModel : lpvm.getLocations()) {
            // asserting that the locationViewModel object on LPVM list is not null
            assertNotNull(locationViewModel.getId());
            assertNotNull(locationViewModel.getName());
        }
    }

    @Test
    public void retrieveLocationPageViewModelPaging() {

        // testing we can retrieve the LPVM object

        // Arrange
        // create list of locations
        List<Location> locationList = testData.createTestLocations(12);
        int numberOfLocations = 3;

        // Act
        LocationPageViewModel lpvm = locationWebService.retrieveLocationPageViewModel(3, 0, 1);

        // Assert
        assertEquals(numberOfLocations, lpvm.getLocations().size());
        assertNotNull(lpvm.getLocationPageCreateCommandModel());

        for(LocationViewModel locationViewModel : lpvm.getLocations()) {
            // asserting that the locationViewModel object on LPVM list is not null
            assertNotNull(locationViewModel.getId());
            assertNotNull(locationViewModel.getName());
        }
    }

    @Test
    public void saveLocationPageCreateCommandModel() {

        // testing we can save the LPCCM object
        // Arrange
        LocationPageCreateCommandModel commandModel = new LocationPageCreateCommandModel();
        commandModel.setName("Starbucks");
        commandModel.setDescription("coffee shop");
        commandModel.setStreet("123 Main St");
        commandModel.setCity("Deerfield");
        commandModel.setState("MA");
        commandModel.setZip("01373");
        commandModel.setLatitude("24.010");
        commandModel.setLongitude("45.987");

        // Act
         Location createdLocation = locationWebService.saveLocationPageCreateCommandModel(commandModel);

        // Assert
        assertNotNull(createdLocation.getLocationId());
        assertEquals("Starbucks", createdLocation.getName());
        assertEquals("coffee shop", createdLocation.getDescription());
        assertEquals("123 Main St", createdLocation.getStreet());
        assertEquals("Deerfield", createdLocation.getCity());
        assertEquals("MA", createdLocation.getState());
        assertEquals("01373", createdLocation.getZip());
        assertEquals("24.010", createdLocation.getLatitude());
        assertEquals("45.987", createdLocation.getLongitude());
    }

    @Test
    public void retrieveLocationEditViewModel() {

        // Arrange
        Location location = testData.createTestLocation();

        // Act
        LocationEditViewModel levm = locationWebService.retrieveLocationEditViewModel(location.getLocationId());

        // Assert
        assertNotNull(levm.getLocationEditCommandModel().getLocationId());
        assertEquals("Starbucks", levm.getLocationEditCommandModel().getName());
        assertEquals("coffee shop", levm.getLocationEditCommandModel().getDescription());
        assertEquals("123 Main St", levm.getLocationEditCommandModel().getStreet());
        assertEquals("Deerfield", levm.getLocationEditCommandModel().getCity());
        assertEquals("MA", levm.getLocationEditCommandModel().getState());
        assertEquals("01373", levm.getLocationEditCommandModel().getZip());
        assertEquals("24.010", levm.getLocationEditCommandModel().getLatitude());
        assertEquals("45.987", levm.getLocationEditCommandModel().getLongitude());
    }

    @Test
    public void saveLocationEditCommandModel() {

        // Arrange
        Location location = testData.createTestLocation();

        LocationEditCommandModel editCommandModel = new LocationEditCommandModel();
        editCommandModel.setLocationId(location.getLocationId());
        editCommandModel.setName("Hair Salon");
        editCommandModel.setDescription("Hair Station");
        editCommandModel.setStreet("15 maple ave");
        editCommandModel.setCity("fork");
        editCommandModel.setState("OH");
        editCommandModel.setZip("45678");
        editCommandModel.setLatitude("34.827");
        editCommandModel.setLongitude("-38.897");

        // Act
        Location updatedLocation = locationWebService.saveLocationEditCommandModel(editCommandModel);

        // Assert
        assertEquals(location.getLocationId(), updatedLocation.getLocationId());
        assertEquals("Hair Salon", updatedLocation.getName());
        assertEquals("Hair Station", updatedLocation.getDescription());
        assertEquals("15 maple ave", updatedLocation.getStreet());
        assertEquals("fork", updatedLocation.getCity());
        assertEquals("OH", updatedLocation.getState());
        assertEquals("45678", updatedLocation.getZip());
        assertEquals("34.827", updatedLocation.getLatitude());
        assertEquals("-38.897", updatedLocation.getLongitude());

    }

    @Test
    public void retrieveLocationDetailsViewModel() {

        // test that we can retrieve ldvm object

        // Arrange
        Location location = testData.createTestLocation();

        // Act
        LocationDetailsViewModel ldvm = locationWebService.retrieveLocationDetailsViewModel(location.getLocationId());

        // Assert
        assertNotNull(ldvm.getId());
        assertEquals("Starbucks", ldvm.getName());
        assertEquals("coffee shop", ldvm.getDescription());
        assertEquals("123 Main St", ldvm.getStreet());
        assertEquals("Deerfield", ldvm.getCity());
        assertEquals("MA", ldvm.getState());
        assertEquals("01373", ldvm.getZip());
        assertEquals("24.010", ldvm.getLatitude());
        assertEquals("45.987", ldvm.getLongitude());

    }

    @Test
    public void removeLocationViewModel() throws DependencyException {
        // Arrange
        Location location = testData.createTestLocation();

        // Act
        LocationPageViewModel lpvm = locationWebService.retrieveLocationPageViewModel(Integer.MAX_VALUE, 0, 1);
        locationWebService.removeLocationViewModel(location.getLocationId());

        LocationPageViewModel read = locationWebService.retrieveLocationPageViewModel(Integer.MAX_VALUE, 0, 1);

        // Assert
        assertNotEquals(lpvm.getLocations().size(), read.getLocations().size());
        assertEquals(0, read.getLocations().size());

    }
}