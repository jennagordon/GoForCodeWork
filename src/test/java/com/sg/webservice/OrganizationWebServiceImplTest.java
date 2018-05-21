package com.sg.webservice;

import com.sg.dto.Hero;
import com.sg.dto.Location;
import com.sg.dto.Organization;
import com.sg.model.commandmodel.organization.editorganization.OrganizationEditCommandModel;
import com.sg.model.commandmodel.organization.organizationpage.OrganizationPageCreateCommandModel;
import com.sg.model.viewmodel.organization.detailsorganization.OrganizationDetailsViewModel;
import com.sg.model.viewmodel.organization.editorganization.OrganizationEditViewModel;
import com.sg.model.viewmodel.organization.organizationpage.OrganizationPageLocationViewModel;
import com.sg.model.viewmodel.organization.organizationpage.OrganizationPageViewModel;
import com.sg.model.viewmodel.organization.organizationpage.OrganizationViewModel;
import com.sg.service.LocationService;
import com.sg.service.OrganizationService;
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
public class OrganizationWebServiceImplTest {

    @Inject
    OrganizationWebService organizationWebService;

    @Inject
    TestDataHelperWebService testData;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void retrieveOrganizationPageViewModel() {

        // Arrange
        List<Location> locations = testData.createTestLocations(12);
        List<Organization> organizations = testData.createTestOrganizations(12, locations.get(0));

        // Act
        OrganizationPageViewModel opvm = organizationWebService.retrieveOrganizationPageViewModel(Integer.MAX_VALUE, 0, 1);

        // Assert
        // we have a createCommandModel and the size of organization lists and locations lists match
        assertNotNull(opvm.getOrganizationPageCreateCommandModel());
        assertEquals(organizations.size(), opvm.getOrganizations().size());
        assertEquals(locations.size(), opvm.getLocations().size());

        // asserting that each organization has a name and id
        for (OrganizationViewModel organizationViewModel : opvm.getOrganizations()) {
            assertNotNull(organizationViewModel.getId());
            assertNotNull(organizationViewModel.getName());
        }

        // asserting that each location has a name and id
        for (OrganizationPageLocationViewModel locationViewModel : opvm.getLocations()) {
            assertNotNull(locationViewModel.getId());
            assertNotNull(locationViewModel.getName());
        }
    }

    @Test
    public void retrieveOrganizationPageViewModelPaging() {

        // Arrange
        List<Location> locations = testData.createTestLocations(12);
        List<Organization> organizations = testData.createTestOrganizations(12, locations.get(0));
        int numberOfOrganizations = 3;

        // Act
        OrganizationPageViewModel opvm = organizationWebService.retrieveOrganizationPageViewModel(3, 0, 1);

        // Assert
        // we have a createCommandModel and the size of organization lists and locations lists match
        assertNotNull(opvm.getOrganizationPageCreateCommandModel());
        assertEquals(numberOfOrganizations, opvm.getOrganizations().size());
        assertEquals(locations.size(), opvm.getLocations().size());

        // asserting that each organization has a name and id
        for (OrganizationViewModel organizationViewModel : opvm.getOrganizations()) {
            assertNotNull(organizationViewModel.getId());
            assertNotNull(organizationViewModel.getName());
        }

        // asserting that each location has a name and id
        for (OrganizationPageLocationViewModel locationViewModel : opvm.getLocations()) {
            assertNotNull(locationViewModel.getId());
            assertNotNull(locationViewModel.getName());
        }
    }


    @Test
    public void saveOrganizationPageCreateCommandModel() {

        // Arrange
        List<Location> locations = testData.createTestLocations(12);
        OrganizationPageCreateCommandModel commandModel = new OrganizationPageCreateCommandModel();
        commandModel.setName("Avengers");
        commandModel.setDescription("Fight Crime");
        commandModel.setLocationId(locations.get(0).getLocationId());

        // Act
        Organization organization = organizationWebService.saveOrganizationPageCreateCommandModel(commandModel);

        // Assert
        assertNotNull(organization.getOrganizationId());
        assertEquals("Avengers", organization.getName());
        assertEquals("Fight Crime", organization.getDescription());
        assertEquals(locations.get(0).getLocationId(), organization.getLocation().getLocationId());

    }

    @Test
    public void retrieveOrganizationEditViewModel() {

        // Arrange
        List<Location> locations = testData.createTestLocations(12);
        Organization organization = testData.createTestOrganization(locations.get(0));

        // Act
        OrganizationEditViewModel viewModel = organizationWebService.retrieveOrganizationEditViewModel(organization.getOrganizationId());

        // Assert
        assertEquals(locations.size(), viewModel.getLocations().size());
        assertEquals(organization.getName(), viewModel.getOrganizationEditCommandModel().getName());
        assertEquals(organization.getDescription(), viewModel.getOrganizationEditCommandModel().getDescription());
        assertEquals(organization.getLocation().getLocationId(), viewModel.getOrganizationEditCommandModel().getLocationId());
        assertNotNull(viewModel.getOrganizationEditCommandModel().getOrganizationId());
    }

    @Test
    public void saveOrganizationEditCommandModel() {

        // Arrange
        Location updatedLocation = testData.createTestLocation();
        Organization organization = testData.createTestOrganization(updatedLocation);


        OrganizationEditCommandModel commandModel = new OrganizationEditCommandModel();
        commandModel.setOrganizationId(organization.getOrganizationId());
        commandModel.setName("Avengers");
        commandModel.setDescription("We Avenge Stuff");
        commandModel.setLocationId(updatedLocation.getLocationId());

        // Act
        Organization updatedOrg = organizationWebService.saveOrganizationEditCommandModel(commandModel);

        // Assert
        assertEquals("Avengers", updatedOrg.getName());
        assertEquals("We Avenge Stuff", updatedOrg.getDescription());
        assertEquals(updatedLocation.getLocationId(), updatedOrg.getLocation().getLocationId());
        assertEquals(organization.getOrganizationId(), updatedOrg.getOrganizationId());
    }

    @Test
    public void retrieveOrganizationDetailsViewModel() {

        // Arrange
        Location location = testData.createTestLocation();
        Organization organization = testData.createTestOrganization(location);

        // Act
        OrganizationDetailsViewModel viewModel = organizationWebService.retrieveOrganizationDetailsViewModel(organization.getOrganizationId());

        // Assert
        assertEquals(organization.getOrganizationId(), viewModel.getId());
        assertEquals(organization.getName(), viewModel.getName());
        assertEquals(organization.getDescription(), viewModel.getDescription());
        assertEquals(organization.getLocation().getName(), viewModel.getLocationName());

    }

    @Test
    public void removeOrganizationViewModel() {

        // Arrange
        List<Location> locations = testData.createTestLocations(12);
        Organization organization = testData.createTestOrganization(locations.get(0));

        // Act
        OrganizationPageViewModel opvm = organizationWebService.retrieveOrganizationPageViewModel(Integer.MAX_VALUE, 0, 1);

        organizationWebService.removeOrganizationViewModel(organization.getOrganizationId());

        OrganizationPageViewModel read = organizationWebService.retrieveOrganizationPageViewModel(Integer.MAX_VALUE, 0, 1);



        // Assert
        assertNotEquals(opvm.getOrganizations().size(), read.getOrganizations().size());
        assertEquals(0, read.getOrganizations().size());

    }
}