package com.sg.webservice;

import com.sg.dto.HeroOrganization;
import com.sg.dto.Location;
import com.sg.dto.Organization;
import com.sg.model.commandmodel.organization.editorganization.OrganizationEditCommandModel;
import com.sg.model.commandmodel.organization.organizationpage.OrganizationPageCreateCommandModel;
import com.sg.model.viewmodel.organization.detailsorganization.OrganizationDetailsViewModel;
import com.sg.model.viewmodel.organization.editorganization.OrganizationEditLocationViewModel;
import com.sg.model.viewmodel.organization.editorganization.OrganizationEditViewModel;
import com.sg.model.viewmodel.organization.organizationpage.OrganizationPageLocationViewModel;
import com.sg.model.viewmodel.organization.organizationpage.OrganizationPageViewModel;
import com.sg.model.viewmodel.organization.organizationpage.OrganizationViewModel;
import com.sg.service.HeroOrganizationService;
import com.sg.service.HeroService;
import com.sg.service.LocationService;
import com.sg.service.OrganizationService;
import com.sg.webservice.util.PagingUtils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class OrganizationWebServiceImpl implements OrganizationWebService {

    OrganizationService organizationService;
    LocationService locationService;
    HeroOrganizationService heroOrganizationService;

    @Inject
    public OrganizationWebServiceImpl(OrganizationService organizationService, LocationService locationService, HeroOrganizationService heroOrganizationService) {
        this.organizationService = organizationService;
        this.locationService = locationService;
        this.heroOrganizationService = heroOrganizationService;
    }

    @Override
    public OrganizationPageViewModel retrieveOrganizationPageViewModel(Integer limit, Integer offset, Integer pageNumbers) {

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
        OrganizationPageViewModel opvm = new OrganizationPageViewModel();

        // LOOK STUFF UP
        List<Location> locations = locationService.retrieveAllLocations(Integer.MAX_VALUE, 0);
        List<Organization> organizations = organizationService.retrieveAllOrganizations(limit, offset);
        OrganizationPageCreateCommandModel commandModel = new OrganizationPageCreateCommandModel();


        Integer currentPage = PagingUtils.calculatePageNumber(limit, offset);
        List<Integer> pages = PagingUtils.getPageNumbers(currentPage, pageNumbers);

        // PUT STUFF
        opvm.setPageNumber(currentPage);
        opvm.setPageNumbers(pages);

        opvm.setLocations(translateOrganizationLocationList(locations));
        opvm.setOrganizations(translateOrganizationList(organizations));
        opvm.setOrganizationPageCreateCommandModel(commandModel);

        return opvm;
    }

    // ---- ORGANIZATION TRANSLATE METHODS FOR LOCATION ----
    private List<OrganizationPageLocationViewModel> translateOrganizationLocationList(List<Location> locations) {

        List<OrganizationPageLocationViewModel> orgLocationList = new ArrayList<>();

        for (Location location : locations) {
            orgLocationList.add(translateOrganizationLocation(location));
        }
        return orgLocationList;
    }

    private OrganizationPageLocationViewModel translateOrganizationLocation(Location location) {

        OrganizationPageLocationViewModel orgLocationViewModel = new OrganizationPageLocationViewModel();
        orgLocationViewModel.setId(location.getLocationId());
        orgLocationViewModel.setName(location.getName());

        return orgLocationViewModel;

    }

    // ---- ORGANIZATION TRANSLATE METHODS FOR ORGANIZATION ----
    private List<OrganizationViewModel> translateOrganizationList(List<Organization> organizations) {

        List<OrganizationViewModel> orgList = new ArrayList<>();

        for (Organization organization : organizations) {
            orgList.add(translateOrganization(organization));
        }
        return orgList;
    }

    private OrganizationViewModel translateOrganization(Organization organization) {

        OrganizationViewModel orgViewModel = new OrganizationViewModel();

        orgViewModel.setId(organization.getOrganizationId());
        orgViewModel.setName(organization.getName());

        return orgViewModel;

    }

    @Override
    public Organization saveOrganizationPageCreateCommandModel(OrganizationPageCreateCommandModel organizationPageCreateCommandModel) {
        Organization organization = new Organization();

        organization.setName(organizationPageCreateCommandModel.getName());
        organization.setDescription(organizationPageCreateCommandModel.getDescription());

        // get location to set if NOT null
        if (organizationPageCreateCommandModel.getLocationId() != null) {
            Location location = locationService.retrieveLocation(organizationPageCreateCommandModel.getLocationId());
            organization.setLocation(location);
        }

        return organizationService.addOrganization(organization);
    }

    @Override
    public OrganizationEditViewModel retrieveOrganizationEditViewModel(Long id) {

        // list of locations, existing org, command model
        List<Location> locations = locationService.retrieveAllLocations(Integer.MAX_VALUE, 0);
        Organization organization = organizationService.retrieveOrganization(id);

        OrganizationEditCommandModel commandModel = new OrganizationEditCommandModel();
        commandModel.setOrganizationId(organization.getOrganizationId());
        commandModel.setName(organization.getName());
        commandModel.setDescription(organization.getDescription());

        if(organization.getLocation() != null) {
            commandModel.setLocationId(organization.getLocation().getLocationId());
        }

        OrganizationEditViewModel editViewModel = new OrganizationEditViewModel();

        editViewModel.setOrganizationEditCommandModel(commandModel);
        editViewModel.setLocations(translateOrganizationEditLocationList(locations));

        return editViewModel;
    }

    // ---- TRANSLATE METHODS FOR EDIT LOCATION LIST ----
    private List<OrganizationEditLocationViewModel> translateOrganizationEditLocationList(List<Location> locations) {

        List<OrganizationEditLocationViewModel> orgEditLocationList = new ArrayList<>();

        for (Location location : locations) {
            orgEditLocationList.add(translateOrganizationEditLocation(location));
        }
        return orgEditLocationList;
    }

    private OrganizationEditLocationViewModel translateOrganizationEditLocation(Location location) {

        OrganizationEditLocationViewModel orgEditLocationViewModel = new OrganizationEditLocationViewModel();
        orgEditLocationViewModel.setId(location.getLocationId());
        orgEditLocationViewModel.setName(location.getName());

        return orgEditLocationViewModel;

    }

    @Override
    public Organization saveOrganizationEditCommandModel(OrganizationEditCommandModel organizationEditCommandModel) {

        Organization organization = organizationService.retrieveOrganization(organizationEditCommandModel.getOrganizationId());

        organization.setName(organizationEditCommandModel.getName());
        organization.setDescription(organizationEditCommandModel.getDescription());

        if(organizationEditCommandModel.getLocationId() != null) {
            Location location = locationService.retrieveLocation(organizationEditCommandModel.getLocationId());
            organization.setLocation(location);
        }

        organizationService.updateOrganization(organization);

        return organization;
    }

    @Override
    public OrganizationDetailsViewModel retrieveOrganizationDetailsViewModel(Long id) {
        Organization organization = organizationService.retrieveOrganization(id);
        Location location = locationService.retrieveLocation(organization.getLocation().getLocationId());

        OrganizationDetailsViewModel viewModel = new OrganizationDetailsViewModel();
        viewModel.setId(organization.getOrganizationId());
        viewModel.setName(organization.getName());
        viewModel.setDescription(organization.getDescription());

        if (organization.getLocation() != null) {
            viewModel.setLocationName(location.getName());
        }

        return viewModel;
    }

    @Override
    public void removeOrganizationViewModel(Long id) {

        Organization organization = organizationService.retrieveOrganization(id);

        // need to handle exception cases
        List<HeroOrganization> heroOrganizationList = heroOrganizationService.retrieveHeroOrganizationByOrganization(organization.getOrganizationId());

        for (HeroOrganization heroOrganization : heroOrganizationList) {
            heroOrganizationService.removeHeroOrganization(heroOrganization);
        }

        organizationService.removeOrganization(organization);

    }
}
