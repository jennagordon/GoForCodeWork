package com.sg.webservice;

import com.sg.dto.*;
import com.sg.model.commandmodel.hero.edithero.HeroEditCommandModel;
import com.sg.model.commandmodel.hero.heropage.HeroPageCreateCommandModel;
import com.sg.model.viewmodel.hero.detailshero.HeroDetailsOrganizationViewModel;
import com.sg.model.viewmodel.hero.detailshero.HeroDetailsPowerViewModel;
import com.sg.model.viewmodel.hero.detailshero.HeroDetailsViewModel;
import com.sg.model.viewmodel.hero.edithero.HeroEditOrganizationViewModel;
import com.sg.model.viewmodel.hero.edithero.HeroEditPowerViewModel;
import com.sg.model.viewmodel.hero.edithero.HeroEditViewModel;
import com.sg.model.viewmodel.hero.heropage.HeroPageOrganizationViewModel;
import com.sg.model.viewmodel.hero.heropage.HeroPagePowerViewModel;
import com.sg.model.viewmodel.hero.heropage.HeroPageViewModel;
import com.sg.service.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class HeroWebServiceImplTest {

    @Inject
    HeroWebService heroWebService;

    @Inject
    PowerService powerService;

    @Inject
    OrganizationService organizationService;

    @Inject
    HeroPowerService heroPowerService;

    @Inject
    HeroOrganizationService heroOrganizationService;

    @Inject
    TestDataHelperWebService testData;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void retrieveHeroPageViewModel() {

        // Arrange
        Location location = testData.createTestLocation();
        List<Power> powers = testData.createTestPowers(12);
        List<Organization> organizations = testData.createTestOrganizations(12, location);
        List<Hero> heroes = testData.createTestHeroes(12);

        // Act
        HeroPageViewModel viewModel = heroWebService.retrieveHeroPageViewModel(Integer.MAX_VALUE, 0, 1);

        // Assert
        assertEquals(powers.size(), viewModel.getPowers().size());
        assertEquals(organizations.size(), viewModel.getOrganizations().size());
        assertEquals(heroes.size(), viewModel.getHeroes().size());
        assertNotNull(viewModel.getHeroPageCreateCommandModel());

        for (HeroPagePowerViewModel power : viewModel.getPowers()) {
            assertNotNull(power.getId());
            assertNotNull(power.getName());
        }

        for (HeroPageOrganizationViewModel organization : viewModel.getOrganizations()) {
            assertNotNull(organization.getId());
            assertNotNull(organization.getName());
        }
    }

    @Test
    public void retrieveHeroPageViewModelPaging() {

        // Arrange
        Location location = testData.createTestLocation();
        List<Power> powers = testData.createTestPowers(12);
        List<Organization> organizations = testData.createTestOrganizations(12, location);
        List<Hero> heroes = testData.createTestHeroes(12);
        int numberOfHeroes = 3;

        // Act
        HeroPageViewModel viewModel = heroWebService.retrieveHeroPageViewModel(3, 0, 1);

        // Assert
        assertEquals(powers.size(), viewModel.getPowers().size());
        assertEquals(organizations.size(), viewModel.getOrganizations().size());
        assertEquals(numberOfHeroes, viewModel.getHeroes().size());
        assertNotNull(viewModel.getHeroPageCreateCommandModel());

        for (HeroPagePowerViewModel power : viewModel.getPowers()) {
            assertNotNull(power.getId());
            assertNotNull(power.getName());
        }

        for (HeroPageOrganizationViewModel organization : viewModel.getOrganizations()) {
            assertNotNull(organization.getId());
            assertNotNull(organization.getName());
        }
    }

    @Test
    public void saveHeroPageCreateCommandModel() {

        // Arrange
        Location location = testData.createTestLocation();
        List<Power> powers = testData.createTestPowers(3);
        List<Organization> organizations = testData.createTestOrganizations(3, location);


        HeroPageCreateCommandModel commandModel = new HeroPageCreateCommandModel();
        commandModel.setName("Batman");
        commandModel.setDescription("black suit scared of bats");
        // adding powerIds
        Long[] powerIds = new Long[3];
        powerIds[0] = powers.get(0).getPowerId();
        powerIds[1] = powers.get(1).getPowerId();
        powerIds[2] = powers.get(2).getPowerId();

        commandModel.setPowerIds(powerIds);

        // adding organizationIds
        Long[] organizationIds = new Long[3];
        organizationIds[0] = organizations.get(0).getOrganizationId();
        organizationIds[1] = organizations.get(1).getOrganizationId();
        organizationIds[2] = organizations.get(2).getOrganizationId();

        commandModel.setOrganizationIds(organizationIds);

        // Act
        Hero hero = heroWebService.saveHeroPageCreateCommandModel(commandModel);

        // Assert
        assertEquals("Batman", hero.getName());
        assertEquals("black suit scared of bats", hero.getDescription());
        assertNotNull(hero.getHeroId());

        // testing for power
        boolean containsPowerOne = false;
        boolean containsPowerTwo = false;
        boolean containsPowerThree = false;
        List<Power> powerList = powerService.retrievePowerByHero(hero, Integer.MAX_VALUE, 0);
        for (Power power : powerList) {
            if (power.getPowerId().equals(powers.get(0).getPowerId())) {
                containsPowerOne = true;
            }

            if (power.getPowerId().equals(powers.get(1).getPowerId())) {
                containsPowerTwo = true;
            }

            if (power.getPowerId().equals(powers.get(2).getPowerId())) {
                containsPowerThree = true;
            }
        }

        assertTrue(containsPowerOne);
        assertTrue(containsPowerTwo);
        assertTrue(containsPowerThree);

        // testing for organization
        boolean containsOrganizationOne = false;
        boolean containsOrganizationTwo = false;
        boolean containsOrganizationThree = false;
        List<Organization> organizationList = organizationService.retrieveOrganizationByHero(hero, Integer.MAX_VALUE, 0);
        for (Organization organization : organizationList) {
            if (organization.getOrganizationId().equals(organizations.get(0).getOrganizationId())) {
                containsOrganizationOne = true;
            }

            if (organization.getOrganizationId().equals(organizations.get(1).getOrganizationId())) {
                containsOrganizationTwo = true;
            }

            if (organization.getOrganizationId().equals(organizations.get(2).getOrganizationId())) {
                containsOrganizationThree = true;
            }
        }

        assertTrue(containsOrganizationOne);
        assertTrue(containsOrganizationTwo);
        assertTrue(containsOrganizationThree);


    }

    @Test
    public void retrieveHeroEditViewModel() {

        // Arrange
        Hero hero = testData.createTestHero();
        List<Power> powers = testData.createTestPowers(12);
        Location location = testData.createTestLocation();
        List<Organization> organizations = testData.createTestOrganizations(12, location);

        // selected power and organization lists
        List<Power> selectedPowers = new ArrayList<>();
        selectedPowers.add(powers.get(0));
        selectedPowers.add(powers.get(1));
        selectedPowers.add(powers.get(2));

        List<Organization> selectedOrganizations = new ArrayList<>();
        selectedOrganizations.add(organizations.get(0));
        selectedOrganizations.add(organizations.get(1));
        selectedOrganizations.add(organizations.get(2));

        // adding selected powers to bridge table
        for (Power power : selectedPowers) {
            HeroPower heroPower = new HeroPower();
            heroPower.setHero(hero);
            heroPower.setPower(power);

            heroPowerService.addHeroPower(heroPower);
        }

        // adding selected organization lists
        for (Organization organization : selectedOrganizations) {
            HeroOrganization heroOrganization = new HeroOrganization();
            heroOrganization.setHero(hero);
            heroOrganization.setOrganization(organization);

            heroOrganizationService.addHeroOrganization(heroOrganization);
        }

        // Act
        HeroEditViewModel editViewModel = heroWebService.retrieveHeroEditViewModel(hero.getHeroId());

        // Assert
        // list sizes
        assertEquals(powers.size(), editViewModel.getPowers().size());
        assertEquals(organizations.size(), editViewModel.getOrganizations().size());

        // each item in list has an id and name
        for (HeroEditPowerViewModel power : editViewModel.getPowers()) {
            assertNotNull(power.getId());
            assertNotNull(power.getName());
        }

        for (HeroEditOrganizationViewModel organization : editViewModel.getOrganizations()) {
            assertNotNull(organization.getId());
            assertNotNull(organization.getName());
        }

        // command model asserts
        assertEquals(hero.getHeroId(), editViewModel.getHeroEditCommandModel().getId());
        assertEquals("Batman", editViewModel.getHeroEditCommandModel().getName());
        assertEquals("black suit with wings", editViewModel.getHeroEditCommandModel().getDescription());

        // power selections are on command model
        boolean containsPowerOne = false;
        boolean containsPowerTwo = false;
        boolean containsPowerThree = false;

        for (Long powerId : editViewModel.getHeroEditCommandModel().getPowerIds()) {
            if (powerId.equals(powers.get(0).getPowerId())) {
                containsPowerOne = true;
            }

            if (powerId.equals(powers.get(1).getPowerId())) {
                containsPowerTwo = true;
            }

            if (powerId.equals(powers.get(2).getPowerId())) {
                containsPowerThree = true;
            }
        }

        assertTrue(containsPowerOne);
        assertTrue(containsPowerTwo);
        assertTrue(containsPowerThree);

        // testing for organization
        boolean containsOrganizationOne = false;
        boolean containsOrganizationTwo = false;
        boolean containsOrganizationThree = false;

        for (Long organizationId : editViewModel.getHeroEditCommandModel().getOrganizationIds()) {
            if (organizationId.equals(organizations.get(0).getOrganizationId())) {
                containsOrganizationOne = true;
            }

            if (organizationId.equals(organizations.get(1).getOrganizationId())) {
                containsOrganizationTwo = true;
            }

            if (organizationId.equals(organizations.get(2).getOrganizationId())) {
                containsOrganizationThree = true;
            }
        }

        assertTrue(containsOrganizationOne);
        assertTrue(containsOrganizationTwo);
        assertTrue(containsOrganizationThree);

    }

    @Test
    public void saveHeroEditCommandModel() {

        // Arrange
        Location location = testData.createTestLocation();
        List<Power> existingPowers = testData.createTestPowers(2);
        List<Organization> existingOrganizations = testData.createTestOrganizations(2, location);
        Hero existingHero = testData.createTestHero();

        // adding selected powers to bridge table
        for (Power power : existingPowers) {
            HeroPower heroPower = new HeroPower();
            heroPower.setHero(existingHero);
            heroPower.setPower(power);

            heroPowerService.addHeroPower(heroPower);
        }

        // adding selected organization lists
        for (Organization organization : existingOrganizations) {
            HeroOrganization heroOrganization = new HeroOrganization();
            heroOrganization.setHero(existingHero);
            heroOrganization.setOrganization(organization);

            heroOrganizationService.addHeroOrganization(heroOrganization);
        }

        // creating command model with new values
        HeroEditCommandModel commandModel = new HeroEditCommandModel();
        commandModel.setId(existingHero.getHeroId());
        commandModel.setName("Invisible Woman");
        commandModel.setDescription("can you see her");

        // creating different powers
        List<Power> updatedPowers = testData.createSecondTestPowers(3);
        // converting powers into LONG[]
        Long[] powerIds = new Long[3];
        powerIds[0] = updatedPowers.get(0).getPowerId();
        powerIds[1] = updatedPowers.get(1).getPowerId();
        powerIds[2] = updatedPowers.get(2).getPowerId();

        commandModel.setPowerIds(powerIds);


        // creating different organizations
        List<Organization> updatedOrganization = testData.createSecondTestOrganizations(3, location);
        // converting organizations into LONG[]
        Long[] organizationIds = new Long[3];
        organizationIds[0] = updatedOrganization.get(0).getOrganizationId();
        organizationIds[1] = updatedOrganization.get(1).getOrganizationId();
        organizationIds[2] = updatedOrganization.get(2).getOrganizationId();

        commandModel.setOrganizationIds(organizationIds);

        // Act
        Hero editedHero = heroWebService.saveHeroEditCommandModel(commandModel);

        // Assert
        // asserts on command
        assertNotNull(editedHero.getHeroId());
        assertEquals("Invisible Woman", editedHero.getName());
        assertEquals("can you see her", editedHero.getDescription());

        // get powers on hero
        List<Power> powers = powerService.retrievePowerByHero(editedHero, Integer.MAX_VALUE, 0);

        boolean containsPowerOne = false;
        boolean containsPowerTwo = false;
        boolean containsPowerThree = false;
        boolean deletedPowerOne = true;
        boolean deletedPowerTwo = true;

        for (Power power : powers) {

            if (power.getPowerId().equals(updatedPowers.get(0).getPowerId())) {
                containsPowerOne = true;
            }

            if (power.getPowerId().equals(updatedPowers.get(1).getPowerId())) {
                containsPowerTwo = true;
            }

            if (power.getPowerId().equals(updatedPowers.get(2).getPowerId())) {
                containsPowerThree = true;
            }

            // verify we don't get the original powers
            if (power.getPowerId().equals(existingPowers.get(0).getPowerId())) {
                deletedPowerOne = false;
            }

            if (power.getPowerId().equals(existingPowers.get(1).getPowerId())) {
                deletedPowerTwo = false;
            }
        }
        assertTrue(containsPowerOne);
        assertTrue(containsPowerTwo);
        assertTrue(containsPowerThree);
        assertTrue(deletedPowerOne);
        assertTrue(deletedPowerTwo);

        // get powers on hero
        List<Organization> organizations = organizationService.retrieveOrganizationByHero(editedHero, Integer.MAX_VALUE, 0);

        boolean containsOrganizationOne = false;
        boolean containsOrganizationTwo = false;
        boolean containsOrganizationThree = false;
        boolean deletedOrganizationOne = true;
        boolean deletedOrganizationTwo = true;

        for (Organization organization : organizations) {

            if (organization.getOrganizationId().equals(updatedOrganization.get(0).getOrganizationId())) {
                containsOrganizationOne = true;
            }

            if (organization.getOrganizationId().equals(updatedOrganization.get(1).getOrganizationId())) {
                containsOrganizationTwo = true;
            }

            if (organization.getOrganizationId().equals(updatedOrganization.get(2).getOrganizationId())) {
                containsOrganizationThree = true;
            }

            // verify the originals are deleted
            if (organization.getOrganizationId().equals(existingOrganizations.get(0).getOrganizationId())) {
                deletedOrganizationOne = false;
            }

            if (organization.getOrganizationId().equals(existingOrganizations.get(1).getOrganizationId())) {
                deletedOrganizationTwo = false;
            }

        }

        assertTrue(containsOrganizationOne);
        assertTrue(containsOrganizationTwo);
        assertTrue(containsOrganizationThree);
        assertTrue(deletedOrganizationOne);
        assertTrue(deletedOrganizationTwo);
    }

    @Test
    public void retrieveHeroDetailsViewModel() {

        // Arrange
        Location location = testData.createTestLocation();
        Hero hero = testData.createTestHero();
        List<Power> powers = testData.createTestPowers(2);
        List<Organization> organizations = testData.createTestOrganizations(2, location);

        for (Power power : powers) {
            HeroPower heroPower = new HeroPower();
            heroPower.setPower(power);
            heroPower.setHero(hero);

            heroPowerService.addHeroPower(heroPower);
        }

        for (Organization organization : organizations) {
            HeroOrganization heroOrganization = new HeroOrganization();
            heroOrganization.setOrganization(organization);
            heroOrganization.setHero(hero);

            heroOrganizationService.addHeroOrganization(heroOrganization);
        }

        // Act
        HeroDetailsViewModel viewModel = heroWebService.retrieveHeroDetailsViewModel(hero.getHeroId());

        // Assert
        assertEquals(hero.getHeroId(), viewModel.getId());
        assertEquals(hero.getName(), viewModel.getName());
        assertEquals(hero.getDescription(), viewModel.getDescription());

        boolean containsPowerOne = false;
        boolean containsPowerTwo = false;
        for (HeroDetailsPowerViewModel power: viewModel.getPowers()) {
            if (power.getName().equals(powers.get(0).getName())) {
                containsPowerOne = true;
            }

            if (power.getName().equals(powers.get(1).getName())) {
                containsPowerTwo = true;
            }
        }

        assertTrue(containsPowerOne);
        assertTrue(containsPowerTwo);

        boolean containsOrganizationOne = false;
        boolean containsOrganizationTwo = false;
        for (HeroDetailsOrganizationViewModel organization : viewModel.getOrganizations()) {
            if (organization.getName().equals(organizations.get(0).getName())) {
                containsOrganizationOne = true;
            }

            if (organization.getName().equals(organizations.get(1).getName())) {
                containsOrganizationTwo = true;
            }
        }
        assertTrue(containsOrganizationOne);
        assertTrue(containsOrganizationTwo);
    }

    @Test
    public void removeHeroViewModel() {
        // Arrange
        Location location = testData.createTestLocation();
        List<Power> powers = testData.createTestPowers(12);
        List<Organization> organizations = testData.createTestOrganizations(12, location);
        Hero hero = testData.createTestHero();

        // Act
        HeroPageViewModel viewModel = heroWebService.retrieveHeroPageViewModel(Integer.MAX_VALUE, 0, 1);
        heroWebService.removeHeroViewModel(hero.getHeroId());

        HeroPageViewModel read = heroWebService.retrieveHeroPageViewModel(Integer.MAX_VALUE, 0, 1);

        // Assert
        assertNotEquals(viewModel.getHeroes().size(), read.getHeroes().size());
        assertEquals(0, read.getHeroes().size());

    }
}