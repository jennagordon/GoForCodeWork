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
import com.sg.model.viewmodel.hero.heropage.HeroViewModel;
import com.sg.service.*;
import com.sg.webservice.util.PagingUtils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class HeroWebServiceImpl implements HeroWebService {

    HeroService heroService;
    OrganizationService organizationService;
    PowerService powerService;
    HeroPowerService heroPowerService;
    HeroOrganizationService heroOrganizationService;
    HeroSightingService heroSightingService;

    @Inject
    public HeroWebServiceImpl(HeroService heroService, OrganizationService organizationService, PowerService powerService,
                              HeroPowerService heroPowerService, HeroOrganizationService heroOrganizationService,
                              HeroSightingService heroSightingService) {
        this.heroService = heroService;
        this.organizationService = organizationService;
        this.powerService = powerService;
        this.heroPowerService = heroPowerService;
        this.heroOrganizationService = heroOrganizationService;
        this.heroSightingService = heroSightingService;
    }

    @Override
    public HeroPageViewModel retrieveHeroPageViewModel(Integer limit, Integer offset, Integer pageNumbers) {

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
        HeroPageViewModel viewModel = new HeroPageViewModel();
        HeroPageCreateCommandModel commandModel = new HeroPageCreateCommandModel();

        // LOOK STUFF UP
        List<Hero> heroes = heroService.retrieveAllHero(limit, offset);
        List<Power> powers = powerService.retrieveAllPowers(Integer.MAX_VALUE, 0);
        List<Organization> organizations = organizationService.retrieveAllOrganizations(Integer.MAX_VALUE, 0);

        Integer currentPage = PagingUtils.calculatePageNumber(limit, offset);
        List<Integer> pages = PagingUtils.getPageNumbers(currentPage, pageNumbers);

        // PUT STUFF ON
        viewModel.setPageNumber(currentPage);
        viewModel.setPageNumbers(pages);

        viewModel.setHeroes(translateHeroList(heroes));
        viewModel.setPowers(translateHeroPagePowerList(powers));
        viewModel.setOrganizations(translateHeroOrganizationList(organizations));
        viewModel.setHeroPageCreateCommandModel(commandModel);

        return viewModel;
    }


    // ---- TRANSLATE METHODS FOR HERO ----
    private List<HeroViewModel> translateHeroList(List<Hero> heroes) {

        List<HeroViewModel> heroViewModels = new ArrayList<>();
        for (Hero hero : heroes) {
            heroViewModels.add(translateHero(hero));
        }
        return heroViewModels;
    }

    private HeroViewModel translateHero(Hero hero) {
        HeroViewModel viewModel = new HeroViewModel();
        viewModel.setId(hero.getHeroId());
        viewModel.setName(hero.getName());

        return viewModel;
    }

    // ---- TRANSLATE METHODS FOR POWER ----
    private List<HeroPagePowerViewModel> translateHeroPagePowerList(List<Power> powers) {

        List<HeroPagePowerViewModel> powerViewModels = new ArrayList<>();
        for (Power power : powers) {
            powerViewModels.add(translateHeroPagePower(power));
        }

        return powerViewModels;
    }

    private HeroPagePowerViewModel translateHeroPagePower (Power power) {

        HeroPagePowerViewModel powerViewModel = new HeroPagePowerViewModel();
        powerViewModel.setId(power.getPowerId());
        powerViewModel.setName(power.getName());

        return powerViewModel;
    }

    // ---- TRANSLATE METHODS FOR ORGANIZATION ----
    private List<HeroPageOrganizationViewModel> translateHeroOrganizationList(List<Organization> organizations) {

        List<HeroPageOrganizationViewModel> orgViewModel = new ArrayList<>();

        for (Organization organization : organizations) {
            orgViewModel.add(translateHeroPageOrganization(organization));
        }

        return orgViewModel;
    }

    private HeroPageOrganizationViewModel translateHeroPageOrganization(Organization organization) {
        HeroPageOrganizationViewModel org = new HeroPageOrganizationViewModel();
        org.setId(organization.getOrganizationId());
        org.setName(organization.getName());

        return org;
    }

    @Override
    public Hero saveHeroPageCreateCommandModel(HeroPageCreateCommandModel heroPageCreateCommandModel) {

        // INSTANTIATE
        Hero hero = new Hero();

        // PUT STUFF ON
        hero.setName(heroPageCreateCommandModel.getName());
        hero.setDescription(heroPageCreateCommandModel.getDescription());

        Hero createdHero = heroService.addHero(hero);


        List<Power> powers = new ArrayList<>();
        for (Long powerId : heroPageCreateCommandModel.getPowerIds()) {

            Power power = powerService.retrievePower(powerId);
            powers.add(power);
        }

        List<Organization> organizations = new ArrayList<>();
        for (Long organizationId : heroPageCreateCommandModel.getOrganizationIds()) {

            Organization organization = organizationService.retrieveOrganization(organizationId);
            organizations.add(organization);
        }

        // create relationships for power and org
        for (Power power : powers) {
            HeroPower heroPower = new HeroPower();
            heroPower.setHero(createdHero);
            heroPower.setPower(power);

            heroPowerService.addHeroPower(heroPower);
        }

        for (Organization organization : organizations) {
            HeroOrganization heroOrganization = new HeroOrganization();
            heroOrganization.setHero(createdHero);
            heroOrganization.setOrganization(organization);

            heroOrganizationService.addHeroOrganization(heroOrganization);
        }

        return hero;
    }

    @Override
    public HeroEditViewModel retrieveHeroEditViewModel(Long id) {

        // INSTANTIATE
        HeroEditViewModel editViewModel = new HeroEditViewModel();

        // LOOK STUFF UP
        List<Power> powers = powerService.retrieveAllPowers(Integer.MAX_VALUE, 0);
        List<Organization> organizations = organizationService.retrieveAllOrganizations(Integer.MAX_VALUE, 0);
        Hero existingHero = heroService.retrieveHero(id);

        // list of selected powers
        List<Power> selectedPowers = powerService.retrievePowerByHero(existingHero, Integer.MAX_VALUE, 0);

        // list of selected orgs
        List<Organization> selectedOrganizations = organizationService.retrieveOrganizationByHero(existingHero, Integer.MAX_VALUE, 0);

        // command model
        HeroEditCommandModel commandModel = new HeroEditCommandModel();
        commandModel.setId(existingHero.getHeroId());
        commandModel.setName(existingHero.getName());
        commandModel.setDescription(existingHero.getDescription());

        // use bridge tables to get power Ids
        if(selectedPowers.size() > 0) {
            Long[] powerIds = new Long[selectedPowers.size()];

            int counter = 0;
            for (Power power : selectedPowers) {
                powerIds[counter] = power.getPowerId();
                counter++;
            }
            commandModel.setPowerIds(powerIds);
        }

        // use bride tables to get organization ids
        if(selectedOrganizations.size() > 0) {
            Long[] organizationIds = new Long[selectedOrganizations.size()];

            int counter = 0;
            for (Organization organization : selectedOrganizations) {
                organizationIds[counter] = organization.getOrganizationId();
                counter++;
            }
            commandModel.setOrganizationIds(organizationIds);
        }

        // set values on the view
        editViewModel.setPowers(translateHeroEditPowerList(powers));
        editViewModel.setOrganizations(translateHeroEditOrganizationList(organizations));
        editViewModel.setHeroEditCommandModel(commandModel);

        return editViewModel;
    }

    // ---- TRANSLATE METHODS FOR POWERS ON EDIT VIEW ----
    private List<HeroEditPowerViewModel> translateHeroEditPowerList(List<Power> powers) {

        List<HeroEditPowerViewModel> editViewModels = new ArrayList<>();
        for (Power power : powers) {
            editViewModels.add(translateHeroEditPower(power));
        }

        return editViewModels;
    }

    private HeroEditPowerViewModel translateHeroEditPower (Power power) {

        HeroEditPowerViewModel powerEditModel = new HeroEditPowerViewModel();
        powerEditModel.setId(power.getPowerId());
        powerEditModel.setName(power.getName());

        return powerEditModel;
    }


    // ---- TRANSLATE METHODS FOR ORGANIZATION ON EDIT VIEW ----
    private List<HeroEditOrganizationViewModel> translateHeroEditOrganizationList(List<Organization> organizations) {

        List<HeroEditOrganizationViewModel> orgViewModel = new ArrayList<>();

        for (Organization organization : organizations) {
            orgViewModel.add(translateHeroEditOrganization(organization));
        }

        return orgViewModel;
    }

    private HeroEditOrganizationViewModel translateHeroEditOrganization(Organization organization) {
        HeroEditOrganizationViewModel org = new HeroEditOrganizationViewModel();
        org.setId(organization.getOrganizationId());
        org.setName(organization.getName());

        return org;
    }

    @Override
    public Hero saveHeroEditCommandModel(HeroEditCommandModel heroEditCommandModel) {

        Hero hero = heroService.retrieveHero(heroEditCommandModel.getId());
        hero.setName(heroEditCommandModel.getName());
        hero.setDescription(heroEditCommandModel.getDescription());

        // getting the selection on powers
        List<Power> powers = new ArrayList<>();
        for (Long powerId : heroEditCommandModel.getPowerIds()) {

            Power power = powerService.retrievePower(powerId);
            powers.add(power);
        }

        // getting the selection on organization
        List<Organization> organizations = new ArrayList<>();
        for (Long organizationId : heroEditCommandModel.getOrganizationIds()) {

            Organization organization = organizationService.retrieveOrganization(organizationId);
            organizations.add(organization);
        }

        heroService.updateHero(hero);

        // remove existing relationships
        List<HeroPower> existingPowerRelationships = heroPowerService.retrieveHeroPowerByHero(hero.getHeroId());
        for (HeroPower heroPower : existingPowerRelationships) {
            heroPowerService.removeHeroPower(heroPower);
        }

        List<HeroOrganization> existingOrgRelationships = heroOrganizationService.retrieveHeroOrganizationByHero(hero.getHeroId());
        for (HeroOrganization heroOrganization : existingOrgRelationships) {
            heroOrganizationService.removeHeroOrganization(heroOrganization);
        }

        // create new relationships for power
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

        return hero;
    }

    @Override
    public HeroDetailsViewModel retrieveHeroDetailsViewModel(Long id) {

        HeroDetailsViewModel heroDetailsViewModel = new HeroDetailsViewModel();

        // LOOK STUFF UP
        Hero hero = heroService.retrieveHero(id);
        List<Power> selectedPowers = powerService.retrievePowerByHero(hero, Integer.MAX_VALUE, 0);
        List<Organization> selectedOrganizations = organizationService.retrieveOrganizationByHero(hero, Integer.MAX_VALUE, 0);

        // PUT STUFF ON IT
        heroDetailsViewModel.setId(hero.getHeroId());
        heroDetailsViewModel.setName(hero.getName());
        heroDetailsViewModel.setDescription(hero.getDescription());
        heroDetailsViewModel.setPowers(translateHeroDetailsPowerList(selectedPowers));
        heroDetailsViewModel.setOrganizations(translateHeroDetailsOrganizationList(selectedOrganizations));

        return heroDetailsViewModel;
    }

    @Override
    public void removeHeroViewModel(Long id) {

        Hero hero = heroService.retrieveHero(id);

        List<HeroSighting> heroSightingList = heroSightingService.retrieveHeroSightingByHero(hero.getHeroId());

        for (HeroSighting heroSighting : heroSightingList) {
            heroSightingService.removeHeroSighting(heroSighting);
        }

        List<HeroPower> heroPowerList = heroPowerService.retrieveHeroPowerByHero(hero.getHeroId());

        for (HeroPower heroPower : heroPowerList) {
            heroPowerService.removeHeroPower(heroPower);
        }

        List<HeroOrganization> heroOrganizationList = heroOrganizationService.retrieveHeroOrganizationByHero(hero.getHeroId());

        for (HeroOrganization heroOrganization : heroOrganizationList) {
            heroOrganizationService.removeHeroOrganization(heroOrganization);
        }

        heroService.removeHero(hero);


    }

    // ---- TRANSLATE METHODS FOR POWERS ON DETAILS VIEW ----
    private List<HeroDetailsPowerViewModel> translateHeroDetailsPowerList(List<Power> powers) {

        List<HeroDetailsPowerViewModel> detailsViewModels = new ArrayList<>();
        for (Power power : powers) {
            detailsViewModels.add(translateHeroDetailsPower(power));
        }

        return detailsViewModels;
    }

    private HeroDetailsPowerViewModel translateHeroDetailsPower (Power power) {

        HeroDetailsPowerViewModel powerDetailsModel = new HeroDetailsPowerViewModel();
        powerDetailsModel.setId(power.getPowerId());
        powerDetailsModel.setName(power.getName());

        return powerDetailsModel;
    }


    // ---- TRANSLATE METHODS FOR ORGANIZATION ON DETAILS VIEW ----
    private List<HeroDetailsOrganizationViewModel> translateHeroDetailsOrganizationList(List<Organization> organizations) {

        List<HeroDetailsOrganizationViewModel> detailViewModel = new ArrayList<>();

        for (Organization organization : organizations) {
            detailViewModel.add(translateHeroDetailsOrganization(organization));
        }

        return detailViewModel;
    }

    private HeroDetailsOrganizationViewModel translateHeroDetailsOrganization(Organization organization) {
        HeroDetailsOrganizationViewModel org = new HeroDetailsOrganizationViewModel();
        org.setId(organization.getOrganizationId());
        org.setName(organization.getName());

        return org;
    }
}
