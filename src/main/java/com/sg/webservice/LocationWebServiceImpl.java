package com.sg.webservice;

import com.sg.dto.Location;
import com.sg.dto.Organization;
import com.sg.dto.Sighting;
import com.sg.model.commandmodel.location.editlocation.LocationEditCommandModel;
import com.sg.model.commandmodel.location.locationpage.LocationPageCreateCommandModel;
import com.sg.model.viewmodel.location.detailslocation.LocationDetailsViewModel;
import com.sg.model.viewmodel.location.editlocation.LocationEditViewModel;
import com.sg.model.viewmodel.location.locationpage.LocationPageViewModel;
import com.sg.model.viewmodel.location.locationpage.LocationViewModel;
import com.sg.service.LocationService;
import com.sg.service.OrganizationService;
import com.sg.service.SightingService;
import com.sg.webservice.util.PagingUtils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class LocationWebServiceImpl implements LocationWebService {


    LocationService locationService;

    SightingService sightingService;

    OrganizationService organizationService;

    @Inject
    public LocationWebServiceImpl(LocationService locationService, SightingService sightingService, OrganizationService organizationService) {
        this.locationService = locationService;
        this.sightingService = sightingService;
        this.organizationService = organizationService;
    }

    @Override
    public LocationPageViewModel retrieveLocationPageViewModel(Integer limit, Integer offset, Integer pageNumbers) {
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
        LocationPageViewModel lpvm = new LocationPageViewModel();

        // LOOK STUFF UP
        List<Location> locations = locationService.retrieveAllLocations(limit, offset);
        LocationPageCreateCommandModel lpccm = new LocationPageCreateCommandModel();

        Integer currentPage = PagingUtils.calculatePageNumber(limit, offset);
        List<Integer> pages = PagingUtils.getPageNumbers(currentPage, pageNumbers);


        // PUT STUFF
        lpvm.setPageNumber(currentPage);
        lpvm.setPageNumbers(pages);

        lpvm.setLocationPageCreateCommandModel(lpccm);
        // translating the location list into a list of LocationPageView
        lpvm.setLocations(translateLocationList(locations));


        return lpvm;
    }

    // ---- TRANSLATE METHODS FOR LOCATIONPAGEVIEWMODEL ----
    private List<LocationViewModel> translateLocationList(List<Location> locations) {

        List<LocationViewModel> locationViewModels = new ArrayList<>();

        for (Location location : locations) {

            locationViewModels.add(translateLocation(location));

        }

        return locationViewModels;

    }

    private LocationViewModel translateLocation (Location location) {

        LocationViewModel locationViewModel = new LocationViewModel();
        locationViewModel.setId(location.getLocationId());
        locationViewModel.setName(location.getName());

        return locationViewModel;
    }

    @Override
    public Location saveLocationPageCreateCommandModel(LocationPageCreateCommandModel locationPageCreateCommandModel) {

        // INSTANTIATE
        Location location = new Location();

        // PUT STUFF
        location.setName(locationPageCreateCommandModel.getName());
        location.setDescription(locationPageCreateCommandModel.getDescription());
        location.setStreet(locationPageCreateCommandModel.getStreet());
        location.setCity(locationPageCreateCommandModel.getCity());
        location.setState(locationPageCreateCommandModel.getState());
        location.setZip(locationPageCreateCommandModel.getZip());

        if(locationPageCreateCommandModel.getLatitude() != null) {
            location.setLatitude(locationPageCreateCommandModel.getLatitude());
        }

        if(locationPageCreateCommandModel.getLongitude() != null) {
            location.setLongitude(locationPageCreateCommandModel.getLongitude());
        }


        return locationService.addLocation(location);
    }

    @Override
    public LocationEditViewModel retrieveLocationEditViewModel(Long id) {

        LocationEditViewModel locationEditViewModel = new LocationEditViewModel();
        Location location = locationService.retrieveLocation(id);

        LocationEditCommandModel commandModel = new LocationEditCommandModel();
        commandModel.setLocationId(location.getLocationId());
        commandModel.setName(location.getName());
        commandModel.setDescription(location.getDescription());
        commandModel.setStreet(location.getStreet());
        commandModel.setCity(location.getCity());
        commandModel.setState(location.getState());
        commandModel.setZip(location.getZip());

        if(location.getLatitude() != null) {
            commandModel.setLatitude(location.getLatitude());
        }

        if(location.getLongitude() != null) {
            commandModel.setLongitude(location.getLongitude());
        }

        locationEditViewModel.setLocationEditCommandModel(commandModel);

        return locationEditViewModel;
    }

    @Override
    public Location saveLocationEditCommandModel(LocationEditCommandModel locationEditCommandModel) {

        // LOOK STUFF UP
        Location location = locationService.retrieveLocation(locationEditCommandModel.getLocationId());

        // SET STUFF
        location.setName(locationEditCommandModel.getName());
        location.setDescription(locationEditCommandModel.getDescription());
        location.setStreet(locationEditCommandModel.getStreet());
        location.setCity(locationEditCommandModel.getCity());
        location.setState(locationEditCommandModel.getState());
        location.setZip(locationEditCommandModel.getZip());

        if(locationEditCommandModel.getLatitude() != null) {
            location.setLatitude(locationEditCommandModel.getLatitude());
        }

        if(locationEditCommandModel.getLongitude() != null) {
            location.setLongitude(locationEditCommandModel.getLongitude());
        }

        // UPDATED STUFF
        locationService.updateLocation(location);

        return location;
    }

    @Override
    public LocationDetailsViewModel retrieveLocationDetailsViewModel(Long id) {
        LocationDetailsViewModel locationDetailsViewModel = new LocationDetailsViewModel();

        Location location = locationService.retrieveLocation(id);

        // PUT STUFF
        locationDetailsViewModel.setId(location.getLocationId());
        locationDetailsViewModel.setName(location.getName());
        locationDetailsViewModel.setDescription(location.getDescription());
        locationDetailsViewModel.setStreet(location.getStreet());
        locationDetailsViewModel.setCity(location.getCity());
        locationDetailsViewModel.setState(location.getState());
        locationDetailsViewModel.setZip(location.getZip());

        if(location.getLatitude() != null) {
            locationDetailsViewModel.setLatitude(location.getLatitude());
        }

        if(location.getLongitude() != null) {
            locationDetailsViewModel.setLongitude(location.getLongitude());
        }

        return locationDetailsViewModel;
    }

    @Override
    public void removeLocationViewModel(Long id) throws DependencyException {

        Location location = locationService.retrieveLocation(id);

        List<Organization> organizationList = organizationService.retrieveAllOrganizations(Integer.MAX_VALUE, 0);
        for (Organization organization : organizationList) {
            if(organization.getLocation().getLocationId().equals(location.getLocationId())) {
                throw new DependencyException("Sorry! Can't delete because of an existing dependency");
            }
        }

        List<Sighting> sightingList = sightingService.retrieveSightingByLocation(location, Integer.MAX_VALUE, 0);

        if (sightingList.size() > 0) {

           throw new DependencyException("Sorry! Can't delete because of an existing dependency");

        }

        locationService.removeLocation(location);

    }
}
