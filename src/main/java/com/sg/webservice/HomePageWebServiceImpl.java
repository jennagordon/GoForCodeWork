package com.sg.webservice;

import com.sg.dto.Hero;
import com.sg.dto.Location;
import com.sg.dto.Sighting;
import com.sg.model.viewmodel.homepage.HomePageHeroViewModel;
import com.sg.model.viewmodel.homepage.HomePageLocationViewModel;
import com.sg.model.viewmodel.homepage.HomePageSightingViewModel;
import com.sg.model.viewmodel.homepage.HomePageViewModel;
import com.sg.service.HeroService;
import com.sg.service.LocationService;
import com.sg.service.SightingService;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HomePageWebServiceImpl implements HomePageWebService {


    private SightingService sightingService;
    private HeroService heroService;
    private LocationService locationService;

    @Inject
    public HomePageWebServiceImpl(SightingService sightingService, HeroService heroService, LocationService locationService) {
        this.sightingService = sightingService;
        this.heroService = heroService;
        this.locationService = locationService;
    }

    public HomePageViewModel retrieveHomePageViewModel(Integer limit) {

        HomePageViewModel viewModel = new HomePageViewModel();


        // look stuff up
        // list of most recent sightings
        List<Sighting> sightings = sightingService.retrieveAllSightings(limit,0);


        //set stuff on the view
        viewModel.setSightings(translateHomePageSightingList(sightings));

        return viewModel;
    }

    // ---- TRANSLATE METHODS FOR SIGHTING ON HOME PAGE ----
    private List<HomePageSightingViewModel> translateHomePageSightingList (List<Sighting> sightings) {

        List<HomePageSightingViewModel> sightingViewModels = new ArrayList<>();

        for (Sighting sighting : sightings) {
            sightingViewModels.add(translateHomePageSighting(sighting));
        }

        return sightingViewModels;
    }

    private HomePageSightingViewModel translateHomePageSighting (Sighting sighting) {

        HomePageSightingViewModel sightingViewModel = new HomePageSightingViewModel();

        List<Hero> heroes = heroService.retrieveHeroBySighting(sighting, Integer.MAX_VALUE, 0);

        Location location = locationService.retrieveLocation(sighting.getLocation().getLocationId());

        // set values on viewModel
        sightingViewModel.setId(sighting.getSightingId());
        sightingViewModel.setDescription(sighting.getDescription());
        sightingViewModel.setDate(sighting.getDate());
        sightingViewModel.setLocation(translateHomePageLocation(location));
        sightingViewModel.setHeroes(translateHomePageHeroList(heroes));

        return sightingViewModel;
    }

    // ---- TRANSLATE METHODS FOR HOME PAGE LOCATION ----
    private HomePageLocationViewModel translateHomePageLocation (Location location) {
        HomePageLocationViewModel homePageLocationViewModel = new HomePageLocationViewModel();
        homePageLocationViewModel.setId(location.getLocationId());
        homePageLocationViewModel.setName(location.getName());

        return homePageLocationViewModel;
    }

    // ---- TRANSLATE METHODS FOR HOME PAGE HEROES ----
    private List<HomePageHeroViewModel> translateHomePageHeroList (List<Hero> heroes) {
        List<HomePageHeroViewModel> homePageHeroViewModels = new ArrayList<>();

        for (Hero hero : heroes) {
            homePageHeroViewModels.add(translateHomePageHero(hero));
        }

        return homePageHeroViewModels;
    }

    private HomePageHeroViewModel translateHomePageHero (Hero hero) {
        HomePageHeroViewModel homePageHeroViewModel = new HomePageHeroViewModel();
        homePageHeroViewModel.setId(hero.getHeroId());
        homePageHeroViewModel.setName(hero.getName());

        return homePageHeroViewModel;
    }
}


