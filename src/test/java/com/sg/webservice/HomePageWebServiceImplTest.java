package com.sg.webservice;

import com.sg.dto.Hero;
import com.sg.dto.Location;
import com.sg.dto.Sighting;
import com.sg.model.viewmodel.homepage.HomePageHeroViewModel;
import com.sg.model.viewmodel.homepage.HomePageLocationViewModel;
import com.sg.model.viewmodel.homepage.HomePageSightingViewModel;
import com.sg.model.viewmodel.homepage.HomePageViewModel;
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
public class HomePageWebServiceImplTest {

    @Inject
    TestDataHelperWebService testData;

    @Inject
    HomePageWebService homePageWebService;


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void retrieveHomePageViewModel() {

        // Assert
        Location locationOne = testData.createTestLocation();
        List<Sighting> sightings = testData.createTestSightings(12, locationOne);
        List<Hero> heroes = testData.createTestHeroes(12);

        // Act
        HomePageViewModel homePageViewModel = homePageWebService.retrieveHomePageViewModel(Integer.MAX_VALUE);

        // Assert
        assertEquals(sightings.size(), homePageViewModel.getSightings().size());

        for (HomePageSightingViewModel sightingViewModel : homePageViewModel.getSightings()) {
            assertNotNull(sightingViewModel.getDate());
            assertNotNull(sightingViewModel.getDescription());
            assertNotNull(sightingViewModel.getHeroes());
            assertNotNull(sightingViewModel.getLocation());

            // checking hero values on the sighting view model
            for (HomePageHeroViewModel homePageHeroViewModel : sightingViewModel.getHeroes()) {
                assertNotNull(homePageHeroViewModel.getId());
                assertNotNull(homePageHeroViewModel.getName());
                assertEquals(heroes.size(), sightingViewModel.getHeroes().size());
            }

        }

    }

    @Test
    public void retrieveHomePageViewModelPaging() {

        // Assert
        Location locationOne = testData.createTestLocation();
        List<Sighting> sightings = testData.createTestSightings(12, locationOne);
        List<Hero> heroes = testData.createTestHeroes(12);

        // Act
        HomePageViewModel homePageViewModel = homePageWebService.retrieveHomePageViewModel(10);

        // Assert
        assertEquals(10, homePageViewModel.getSightings().size());

        for (HomePageSightingViewModel sightingViewModel : homePageViewModel.getSightings()) {
            assertNotNull(sightingViewModel.getDate());
            assertNotNull(sightingViewModel.getDescription());
            assertNotNull(sightingViewModel.getHeroes());
            assertNotNull(sightingViewModel.getLocation());


            // checking hero values on the sighting view model
            for (HomePageHeroViewModel homePageHeroViewModel : sightingViewModel.getHeroes()) {
                assertNotNull(homePageHeroViewModel.getId());
                assertNotNull(homePageHeroViewModel.getName());
                assertEquals(heroes.size(), sightingViewModel.getHeroes().size());
            }

        }

    }
}