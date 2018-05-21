package com.sg.service;

import com.sg.dao.HeroSightingDao;
import com.sg.dto.HeroSighting;

import javax.inject.Inject;
import java.util.List;

public class HeroSightingServiceImpl implements HeroSightingService {

    HeroSightingDao heroSightingDao;

    @Inject
    public HeroSightingServiceImpl(HeroSightingDao heroSightingDao) {
        this.heroSightingDao = heroSightingDao;
    }

    @Override
    public HeroSighting addHeroSighting(HeroSighting heroSighting) {
        return heroSightingDao.addHeroSighting(heroSighting);
    }

    @Override
    public void removeHeroSighting(HeroSighting heroSighting) {

        heroSightingDao.removeHeroSighting(heroSighting);

    }

    @Override
    public void updateHeroSighting(HeroSighting heroSighting) {

        heroSightingDao.updateHeroSighting(heroSighting);

    }

    @Override
    public HeroSighting retrieveHeroSightingById(Long heroSightingId) {
        return heroSightingDao.retrieveHeroSightingById(heroSightingId);
    }

    @Override
    public List<HeroSighting> retrieveHeroSightingBySighting(Long sightingId) {
        return heroSightingDao.retrieveHeroSightingBySighting(sightingId);
    }

    @Override
    public List<HeroSighting> retrieveHeroSightingByHero(Long heroId) {
        return heroSightingDao.retrieveHeroSightingByHero(heroId);
    }
}
