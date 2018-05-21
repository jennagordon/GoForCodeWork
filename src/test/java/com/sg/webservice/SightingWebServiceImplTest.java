package com.sg.webservice;

import com.sg.dto.Hero;
import com.sg.dto.HeroSighting;
import com.sg.dto.Location;
import com.sg.dto.Sighting;
import com.sg.model.commandmodel.sighting.editsighting.SightingEditCommandModel;
import com.sg.model.commandmodel.sighting.sightingpage.SightingPageCreateCommandModel;
import com.sg.model.viewmodel.sighting.detailsighting.SightingDetailsHeroViewModel;
import com.sg.model.viewmodel.sighting.detailsighting.SightingDetailsViewModel;
import com.sg.model.viewmodel.sighting.editsighting.SightingEditHeroViewModel;
import com.sg.model.viewmodel.sighting.editsighting.SightingEditLocationViewModel;
import com.sg.model.viewmodel.sighting.editsighting.SightingEditViewModel;
import com.sg.model.viewmodel.sighting.sightingpage.SightingPageHeroViewModel;
import com.sg.model.viewmodel.sighting.sightingpage.SightingPageLocationViewModel;
import com.sg.model.viewmodel.sighting.sightingpage.SightingPageViewModel;
import com.sg.model.viewmodel.sighting.sightingpage.SightingViewModel;
import com.sg.service.HeroService;
import com.sg.service.HeroSightingService;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class SightingWebServiceImplTest {

    @Inject
    SightingWebService sightingWebService;

    @Inject
    TestDataHelperWebService testData;

    @Inject
    HeroService heroService;

    @Inject
    HeroSightingService heroSightingService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void retrieveSightingPageViewModel() {

        // Arrange
        List<Location> locations = testData.createTestLocations(12);
        List<Sighting> sightings = testData.createTestSightings(12, locations.get(0));
        List<Hero> heroes = testData.createTestHeroes(12);


        // Act
        SightingPageViewModel spvm = sightingWebService.retrieveSightingPageViewModel(Integer.MAX_VALUE, 0, 1);

        // Assert
        assertEquals(sightings.size(), spvm.getSightings().size());
        assertEquals(heroes.size(), spvm.getHeroes().size());
        assertEquals(locations.size(), spvm.getLocations().size() );

        assertNotNull(spvm.getSightingPageCreateCommandModel());

        for(SightingViewModel sightingViewModel : spvm.getSightings()) {
            assertNotNull(sightingViewModel.getId());
            assertNotNull(sightingViewModel.getName());
        }

        for(SightingPageHeroViewModel hero : spvm.getHeroes()) {
            assertNotNull(hero.getId());
            assertNotNull(hero.getName());
        }

        for(SightingPageLocationViewModel location : spvm.getLocations()) {
            assertNotNull(location.getId());
            assertNotNull(location.getName());
        }

    }

    @Test
    public void retrieveSightingPageViewModelPaging() {

        // Arrange
        List<Location> locations = testData.createTestLocations(12);
        List<Sighting> sightings = testData.createTestSightings(12, locations.get(0));
        List<Hero> heroes = testData.createTestHeroes(12);
        int numberOfSightings = 3;


        // Act
        SightingPageViewModel spvm = sightingWebService.retrieveSightingPageViewModel(3, 0, 1);

        // Assert
        assertEquals(numberOfSightings, spvm.getSightings().size());
        assertEquals(heroes.size(), spvm.getHeroes().size());
        assertEquals(locations.size(), spvm.getLocations().size() );

        assertNotNull(spvm.getSightingPageCreateCommandModel());

        for(SightingViewModel sightingViewModel : spvm.getSightings()) {
            assertNotNull(sightingViewModel.getId());
            assertNotNull(sightingViewModel.getName());
        }

        for(SightingPageHeroViewModel hero : spvm.getHeroes()) {
            assertNotNull(hero.getId());
            assertNotNull(hero.getName());
        }

        for(SightingPageLocationViewModel location : spvm.getLocations()) {
            assertNotNull(location.getId());
            assertNotNull(location.getName());
        }

    }


    @Test
    public void saveSightingPageCreateCommandModel() {

        // Arrange
        Location location = testData.createTestLocation();
        List<Hero> heroes = testData.createTestHeroes(3);

        SightingPageCreateCommandModel commandModel = new SightingPageCreateCommandModel();
        commandModel.setDate(testData.date);
        commandModel.setDescription("saw Batman at Chick-Fil-A");
        commandModel.setLocationId(location.getLocationId());

        Long [] heroIds = new Long[3];
        heroIds[0] = heroes.get(0).getHeroId();
        heroIds[1] = heroes.get(1).getHeroId();
        heroIds[2] = heroes.get(2).getHeroId();

        commandModel.setHeroId(heroIds);

        // Act
        Sighting sighting = sightingWebService.saveSightingPageCreateCommandModel(commandModel);

        // Assert
        assertEquals(LocalDate.parse("2018-01-01"), sighting.getDate());
        assertEquals("saw Batman at Chick-Fil-A", sighting.getDescription());
        assertEquals(location.getLocationId(), sighting.getLocation().getLocationId());
        assertNotNull(sighting.getSightingId());

        boolean containsHeroOne = false;
        boolean containsHeroTwo = false;
        boolean containsHeroThree = false;
        List<Hero> heroList = heroService.retrieveHeroBySighting(sighting, Integer.MAX_VALUE, 0);
        for (Hero hero : heroList) {
            if (hero.getHeroId().equals(heroes.get(0).getHeroId())){
                containsHeroOne = true;
            }

            if (hero.getHeroId().equals(heroes.get(1).getHeroId())){
                containsHeroTwo = true;
            }
            if (hero.getHeroId().equals(heroes.get(2).getHeroId())){
                containsHeroThree = true;
            }
        }

        assertTrue(containsHeroOne);
        assertTrue(containsHeroTwo);
        assertTrue(containsHeroThree);

    }

    @Test
    public void retrieveSightingEditViewModel() {

        // Arrange
        List<Location> locations = testData.createTestLocations(12);
        Sighting sighting = testData.createTestSighting(locations.get(0));
        List<Hero> heroes = testData.createTestHeroes(12);

        List<Hero> selectedHeroes = new ArrayList<>();
        selectedHeroes.add(heroes.get(0));
        selectedHeroes.add(heroes.get(1));
        selectedHeroes.add(heroes.get(2));

        for (Hero hero : selectedHeroes) {
            HeroSighting heroSighting = new HeroSighting();
            heroSighting.setHero(hero);
            heroSighting.setSighting(sighting);

            heroSightingService.addHeroSighting(heroSighting);
        }

        // Act
        SightingEditViewModel editViewModel = sightingWebService.retrieveSightingEditViewModel(sighting.getSightingId());

        // Assert
        assertEquals(locations.size(), editViewModel.getLocations().size());
        assertEquals(heroes.size(), editViewModel.getHeroes().size());

        for (SightingEditHeroViewModel hero : editViewModel.getHeroes()) {
            assertNotNull(hero.getId());
            assertNotNull(hero.getName());
        }

        for(SightingEditLocationViewModel location : editViewModel.getLocations()) {
            assertNotNull(location.getId());
            assertNotNull(location.getName());
        }

        assertEquals(sighting.getSightingId(), editViewModel.getSightingEditCommandModel().getSightingId());
        assertEquals(LocalDate.parse("2018-01-01"), editViewModel.getSightingEditCommandModel().getDate());
        assertEquals("saw Superman at chick-fil-a", editViewModel.getSightingEditCommandModel().getDescription());

        boolean containsHeroOne = false;
        boolean containsHeroTwo = false;
        boolean containsHeroThree = false;

        for (Long heroId : editViewModel.getSightingEditCommandModel().getHeroIds()) {
            if (heroId.equals(heroes.get(0).getHeroId())){
                containsHeroOne = true;
            }

            if (heroId.equals(heroes.get(1).getHeroId())){
                containsHeroTwo = true;
            }
            if (heroId.equals(heroes.get(2).getHeroId())){
                containsHeroThree = true;
            }

        }
        assertTrue(containsHeroOne);
        assertTrue(containsHeroTwo);
        assertTrue(containsHeroThree);


    }

    @Test
    public void saveSightingEditCommandModel() {

        // Arrange
        Location location = testData.createTestLocation();
        List<Hero> heroes = testData.createTestHeroes(2);
        Sighting existingSighting = testData.createTestSighting(location);

        for (Hero hero : heroes) {
            HeroSighting heroSighting = new HeroSighting();
            heroSighting.setHero(hero);
            heroSighting.setSighting(existingSighting);

            heroSightingService.addHeroSighting(heroSighting);
        }

        SightingEditCommandModel commandModel = new SightingEditCommandModel();
        commandModel.setDate(testData.date2);
        commandModel.setDescription("saw wonder woman at the hair salon");
        commandModel.setSightingId(existingSighting.getSightingId());
        commandModel.setLocationId(location.getLocationId());

        List<Hero> updatedHeroes = testData.createSecondTestHeroes(3);
        Long [] heroIds = new Long[3];
        heroIds[0] = updatedHeroes.get(0).getHeroId();
        heroIds[1] = updatedHeroes.get(1).getHeroId();
        heroIds[2] = updatedHeroes.get(2).getHeroId();
        // setting updated hero ids
        commandModel.setHeroIds(heroIds);


        // Act
        Sighting sighting = sightingWebService.saveSightingEditCommandModel(commandModel);

        // Assert
        assertEquals(LocalDate.parse("2018-03-22"), sighting.getDate());
        assertEquals("saw wonder woman at the hair salon", sighting.getDescription());
        assertEquals(location.getLocationId(), sighting.getLocation().getLocationId());
        assertEquals(existingSighting.getSightingId(), sighting.getSightingId());

        boolean containsHeroOne = false;
        boolean containsHeroTwo = false;
        boolean containsHeroThree = false;
        boolean deletedHeroOne = true;
        boolean deletedHeroTwo = true;
        // get a list of heroes sighting has
        List<Hero> heroList = heroService.retrieveHeroBySighting(sighting, Integer.MAX_VALUE, 0);
        for (Hero hero : heroList) {
            if (hero.getHeroId().equals(updatedHeroes.get(0).getHeroId())){
                containsHeroOne = true;
            }

            if (hero.getHeroId().equals(updatedHeroes.get(1).getHeroId())){
                containsHeroTwo = true;
            }
            if (hero.getHeroId().equals(updatedHeroes.get(2).getHeroId())){
                containsHeroThree = true;
            }

            // confirm the old ones are gone
            if (hero.getHeroId().equals(heroes.get(0).getHeroId())) {
                deletedHeroOne = false;
            }

            if (hero.getHeroId().equals(heroes.get(1).getHeroId())) {
                deletedHeroTwo = false;
            }
        }

        assertTrue(containsHeroOne);
        assertTrue(containsHeroTwo);
        assertTrue(containsHeroThree);
        assertTrue(deletedHeroOne);
        assertTrue(deletedHeroTwo);

    }

    @Test
    public void retrieveSightingDetailsViewModel() {

        // Arrange
        Location location = testData.createTestLocation();
        Sighting sighting = testData.createTestSighting(location);
        List<Hero> heroes = testData.createTestHeroes(3);

        for (Hero hero : heroes) {
            HeroSighting heroSighting = new HeroSighting();
            heroSighting.setHero(hero);
            heroSighting.setSighting(sighting);

            heroSightingService.addHeroSighting(heroSighting);
        }

        // Act
        SightingDetailsViewModel viewModel = sightingWebService.retrieveSightingDetailsViewModel(sighting.getSightingId());

        // Assert
        assertEquals(sighting.getDate(),viewModel.getDate());
        assertEquals(sighting.getDescription(), viewModel.getDescription());
        assertEquals(location.getName(), viewModel.getLocationName());

        boolean containsHeroOne = false;
        boolean containsHeroTwo = false;
        boolean containsHeroThree = false;

        for (SightingDetailsHeroViewModel hero : viewModel.getHeroes()) {
            if (hero.getId().equals(heroes.get(0).getHeroId())){
                containsHeroOne = true;
            }

            if (hero.getId().equals(heroes.get(1).getHeroId())){
                containsHeroTwo = true;
            }
            if (hero.getId().equals(heroes.get(2).getHeroId())){
                containsHeroThree = true;
            }

        }
        assertTrue(containsHeroOne);
        assertTrue(containsHeroTwo);
        assertTrue(containsHeroThree);

    }

    @Test
    public void removeSightingViewModel() {

        // Arrange
        List<Location> locations = testData.createTestLocations(12);
        Sighting sighting = testData.createTestSighting(locations.get(0));
        List<Hero> heroes = testData.createTestHeroes(12);


        // Act
        SightingPageViewModel spvm = sightingWebService.retrieveSightingPageViewModel(Integer.MAX_VALUE, 0, 1);
        sightingWebService.removeSightingViewModel(sighting.getSightingId());

        SightingPageViewModel readSPVM = sightingWebService.retrieveSightingPageViewModel(Integer.MAX_VALUE, 0, 1);

        // Assert
        assertNotEquals(spvm.getSightings().size(), readSPVM.getSightings().size());
        assertEquals(0, readSPVM.getSightings().size());

    }
}