package com.sg.service;

import com.sg.dao.HeroOrganizationDao;
import com.sg.dto.HeroOrganization;

import javax.inject.Inject;
import java.util.List;

public class HeroOrganizationServiceImpl implements HeroOrganizationService {

    HeroOrganizationDao heroOrganizationDao;

    @Inject
    public HeroOrganizationServiceImpl(HeroOrganizationDao heroOrganizationDao) {
        this.heroOrganizationDao = heroOrganizationDao;
    }

    @Override
    public HeroOrganization addHeroOrganization(HeroOrganization heroOrganization) {
        return heroOrganizationDao.addHeroOrganization(heroOrganization);
    }

    @Override
    public void removeHeroOrganization(HeroOrganization heroOrganization) {

        heroOrganizationDao.removeHeroOrganization(heroOrganization);

    }

    @Override
    public void updateHeroOrganization(HeroOrganization heroOrganization) {

        heroOrganizationDao.updateHeroOrganization(heroOrganization);

    }

    @Override
    public HeroOrganization retrieveHeroOrganizationById(Long heroOrganizationId) {
        return heroOrganizationDao.retrieveHeroOrganizationById(heroOrganizationId);
    }

    @Override
    public List<HeroOrganization> retrieveHeroOrganizationByOrganization(Long organizationId) {
        return heroOrganizationDao.retrieveHeroOrganizationByOrganization(organizationId);
    }

    @Override
    public List<HeroOrganization> retrieveHeroOrganizationByHero(Long heroId) {
        return heroOrganizationDao.retrieveHeroOrganizationByHero(heroId);
    }
}
