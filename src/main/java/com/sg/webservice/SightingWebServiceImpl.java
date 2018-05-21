package com.sg.webservice;

import com.sg.dto.*;
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
import com.sg.service.LocationService;
import com.sg.service.SightingService;
import com.sg.webservice.util.PagingUtils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class SightingWebServiceImpl implements SightingWebService {

    SightingService sightingService;
    LocationService locationService;
    HeroService heroService;
    HeroSightingService heroSightingService;

    @Inject
    public SightingWebServiceImpl(SightingService sightingService, LocationService locationService, HeroService heroService, HeroSightingService heroSightingService) {
        this.sightingService = sightingService;
        this.locationService = locationService;
        this.heroService = heroService;
        this.heroSightingService = heroSightingService;
    }

    @Override
    public SightingPageViewModel retrieveSightingPageViewModel(Integer limit, Integer offset, Integer pageNumbers) {

        if(limit == null) {
            limit = 5;
        }

        if(offset == null) {
            offset = 0;
        }

        if(pageNumbers == null) {
            pageNumbers = 5;
        }

        // INSTANTIATE
        SightingPageViewModel spvm = new SightingPageViewModel();

        // LOOK STUFF UP
        List<Sighting> sightings = sightingService.retrieveAllSightings(limit, offset);
        List<Location> locations = locationService.retrieveAllLocations(Integer.MAX_VALUE, 0);
        List<Hero> heroes = heroService.retrieveAllHero(Integer.MAX_VALUE, 0);

        SightingPageCreateCommandModel commandModel = new SightingPageCreateCommandModel();

        Integer currentPage = PagingUtils.calculatePageNumber(limit, offset);
        List<Integer> pages = PagingUtils.getPageNumbers(currentPage, pageNumbers);

        // PUT THINGS ON THINGS
        spvm.setPageNumber(currentPage);
        spvm.setPageNumbers(pages);


        spvm.setLocations(translateSightingPageLocationList(locations));
        spvm.setSightings(translateSightingList(sightings));
        spvm.setHeroes(translateSightingPageHeroList(heroes));
        spvm.setSightingPageCreateCommandModel(commandModel);

        return spvm;
    }

    // ---- TRANSLATE METHODS FOR SIGHTING ----
    private List<SightingViewModel> translateSightingList (List<Sighting> sightings) {
        List<SightingViewModel> sightingViewModels = new ArrayList<>();

        for (Sighting sighting : sightings) {
            sightingViewModels.add(translateSighting(sighting));
        }
        return sightingViewModels;
    }

    private SightingViewModel translateSighting (Sighting sighting) {
        SightingViewModel viewModel = new SightingViewModel();
        Location location = locationService.retrieveLocation(sighting.getLocation().getLocationId());

        viewModel.setId(sighting.getSightingId());
        viewModel.setName(location.getName());

        return viewModel;
    }
    // ---- TRANSLATE METHODS FOR SIGHTING LOCATIONS ----
    private List<SightingPageLocationViewModel> translateSightingPageLocationList (List<Location> locations) {
        List<SightingPageLocationViewModel> sightingLocations = new ArrayList<>();

        for (Location location : locations) {
            sightingLocations.add(translateSightingLocation(location));
        }
        return sightingLocations;

    }

    private SightingPageLocationViewModel translateSightingLocation (Location location) {
        SightingPageLocationViewModel locationViewModel = new SightingPageLocationViewModel();

        locationViewModel.setId(location.getLocationId());
        locationViewModel.setName(location.getName());

        return locationViewModel;
    }
    // ---- TRANSLATE METHODS FOR SIGHTING HEROES ----
    private List<SightingPageHeroViewModel> translateSightingPageHeroList (List<Hero> heroes) {
        List<SightingPageHeroViewModel> sightingHeroes = new ArrayList<>();

        for (Hero hero : heroes) {
            sightingHeroes.add(translateSightingHero(hero));
        }
        return sightingHeroes;

    }

    private SightingPageHeroViewModel translateSightingHero (Hero hero) {
        SightingPageHeroViewModel heroViewModel = new SightingPageHeroViewModel();
        heroViewModel.setId(hero.getHeroId());
        heroViewModel.setName(hero.getName());

        return heroViewModel;
    }

    @Override
    public Sighting saveSightingPageCreateCommandModel(SightingPageCreateCommandModel sightingPageCreateCommandModel) {
        Sighting sighting = new Sighting();
        Location location = locationService.retrieveLocation(sightingPageCreateCommandModel.getLocationId());

        sighting.setDate(sightingPageCreateCommandModel.getDate());
        sighting.setDescription(sightingPageCreateCommandModel.getDescription());
        sighting.setLocation(location);

        // to add heroes we need to use our bridge table
        List<Hero> heroList = new ArrayList<>();
        for (Long heroId : sightingPageCreateCommandModel.getHeroId()) {

            Hero hero = heroService.retrieveHero(heroId);
            heroList.add(hero);
        }

        Sighting createdSighting = sightingService.addSighting(sighting);

        for (Hero hero : heroList) {

            HeroSighting heroSighting = new HeroSighting();
            heroSighting.setSighting(createdSighting);
            heroSighting.setHero(hero);

            heroSightingService.addHeroSighting(heroSighting);
        }


        return createdSighting;
    }

    @Override
    public SightingEditViewModel retrieveSightingEditViewModel(Long id) {
        SightingEditViewModel sevm = new SightingEditViewModel();

        Sighting sighting = sightingService.retrieveSighting(id);
        List<Hero> heroes = heroService.retrieveAllHero(Integer.MAX_VALUE, 0);
        List<Location> locations = locationService.retrieveAllLocations(Integer.MAX_VALUE, 0);

        // list of selected heroes
        List<Hero> selectedHeroes = heroService.retrieveHeroBySighting(sighting, Integer.MAX_VALUE, 0);

        SightingEditCommandModel editCommandModel = new SightingEditCommandModel();
        editCommandModel.setSightingId(sighting.getSightingId());
        editCommandModel.setDate(sighting.getDate());
        editCommandModel.setDescription(sighting.getDescription());

        // use bridge table to retrieve set heroes
        if(selectedHeroes.size() > 0) {
            Long[] heroIds = new Long[selectedHeroes.size()];

            int counter = 0;
            for (Hero hero : selectedHeroes) {
                heroIds[counter] = hero.getHeroId();
                counter++;
            }
            editCommandModel.setHeroIds(heroIds);
        }

        // set values on view
        sevm.setHeroes(translateSightingEditHeroList(heroes));
        sevm.setLocations(translateSightingEditLocationList(locations));
        sevm.setSightingEditCommandModel(editCommandModel);

        return sevm;
    }

    // ---- TRANSLATE METHODS FOR SIGHTING LOCATIONS ----
    private List<SightingEditLocationViewModel> translateSightingEditLocationList (List<Location> locations) {
        List<SightingEditLocationViewModel> sightingLocations = new ArrayList<>();

        for (Location location : locations) {
            sightingLocations.add(translateSightingEditLocation(location));
        }
        return sightingLocations;

    }

    private SightingEditLocationViewModel translateSightingEditLocation (Location location) {
        SightingEditLocationViewModel locationViewModel = new SightingEditLocationViewModel();

        locationViewModel.setId(location.getLocationId());
        locationViewModel.setName(location.getName());

        return locationViewModel;
    }
    // ---- TRANSLATE METHODS FOR SIGHTING HEROES ----
    private List<SightingEditHeroViewModel> translateSightingEditHeroList (List<Hero> heroes) {
        List<SightingEditHeroViewModel> sightingHeroes = new ArrayList<>();

        for (Hero hero : heroes) {
            sightingHeroes.add(translateSightingEditHero(hero));
        }
        return sightingHeroes;

    }

    private SightingEditHeroViewModel translateSightingEditHero (Hero hero) {
        SightingEditHeroViewModel heroViewModel = new SightingEditHeroViewModel();
        heroViewModel.setId(hero.getHeroId());
        heroViewModel.setName(hero.getName());

        return heroViewModel;
    }


    @Override
    public Sighting saveSightingEditCommandModel(SightingEditCommandModel sightingEditCommandModel) {
        Sighting sighting = sightingService.retrieveSighting(sightingEditCommandModel.getSightingId());
        Location location = locationService.retrieveLocation(sighting.getLocation().getLocationId());

        sighting.setDate(sightingEditCommandModel.getDate());
        sighting.setDescription(sightingEditCommandModel.getDescription());
        sighting.setLocation(location);

        // getting the selection heroes
        List<Hero> heroList = new ArrayList<>();
        for (Long heroId : sightingEditCommandModel.getHeroIds()) {

            Hero hero = heroService.retrieveHero(heroId);
            heroList.add(hero);
        }

        // update sighting
        sightingService.updateSighting(sighting);

        // removing the bridge table instances
        List<HeroSighting> existingRelationships = heroSightingService.retrieveHeroSightingBySighting(sighting.getSightingId());

        for (HeroSighting heroSighting : existingRelationships) {
            heroSightingService.removeHeroSighting(heroSighting);
        }

        // creating the new instances
        for (Hero hero : heroList) {
            HeroSighting heroSighting = new HeroSighting();
            heroSighting.setHero(hero);
            heroSighting.setSighting(sighting);

            heroSightingService.addHeroSighting(heroSighting);
        }

        return sighting;
    }

    @Override
    public SightingDetailsViewModel retrieveSightingDetailsViewModel(Long id) {

        SightingDetailsViewModel sightingDetailsViewModel = new SightingDetailsViewModel();

        // LOOK STUFF UP
        Sighting sighting = sightingService.retrieveSighting(id);
        Location location = locationService.retrieveLocation(sighting.getLocation().getLocationId());
        List<Hero> selectedHeroes = heroService.retrieveHeroBySighting(sighting, Integer.MAX_VALUE, 0);

        // PUT STUFF ON
        sightingDetailsViewModel.setId(sighting.getSightingId());
        sightingDetailsViewModel.setDate(sighting.getDate());
        sightingDetailsViewModel.setDescription(sighting.getDescription());
        sightingDetailsViewModel.setLocationName(location.getName());
        sightingDetailsViewModel.setHeroes(translateSightingDetailsHero(selectedHeroes));

        return sightingDetailsViewModel;
    }

    @Override
    public void removeSightingViewModel(Long id) {

        Sighting sighting = sightingService.retrieveSighting(id);

        // if sighting has heroes related to it we must delete it from the HeroSighting
        List<HeroSighting> heroSightingList = heroSightingService.retrieveHeroSightingBySighting(sighting.getSightingId());

        for (HeroSighting heroSighting : heroSightingList) {
            heroSightingService.removeHeroSighting(heroSighting);
        }

        sightingService.removeSighting(sighting);

    }

    // ---- TRANSLATE METHODS FOR HERO ON SIGHTING DETAILS ----
    private List<SightingDetailsHeroViewModel> translateSightingDetailsHero(List<Hero> heroes) {

        List<SightingDetailsHeroViewModel> heroList = new ArrayList<>();

        for (Hero hero :heroes) {
            heroList.add(translateSightingDetailHero(hero));
        }

        return heroList;

    }

    private SightingDetailsHeroViewModel translateSightingDetailHero(Hero hero) {
        SightingDetailsHeroViewModel viewModel = new SightingDetailsHeroViewModel();

        viewModel.setId(hero.getHeroId());
        viewModel.setName(hero.getName());

        return viewModel;

    }
}
