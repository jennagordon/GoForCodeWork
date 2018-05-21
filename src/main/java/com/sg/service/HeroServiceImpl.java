package com.sg.service;

import com.sg.dao.HeroDao;
import com.sg.dto.Hero;
import com.sg.dto.Organization;
import com.sg.dto.Power;
import com.sg.dto.Sighting;

import javax.inject.Inject;
import java.util.List;

public class HeroServiceImpl implements HeroService {

    HeroDao heroDao;

    @Inject
    public HeroServiceImpl(HeroDao heroDao) {
        this.heroDao = heroDao;
    }

    @Override
    public Hero addHero(Hero hero) {
        return heroDao.addHero(hero);
    }

    @Override
    public void removeHero(Hero hero) {

        heroDao.removeHero(hero);

    }

    @Override
    public void updateHero(Hero hero) {

        heroDao.updateHero(hero);

    }

    @Override
    public List<Hero> retrieveAllHero(int limit, int offset) {
        return heroDao.retrieveAllHero(limit, offset);
    }

    @Override
    public Hero retrieveHero(Long heroId) {
        return heroDao.retrieveHero(heroId);
    }

    @Override
    public List<Hero> retrieveHeroByPower(Power power, int limit, int offset) {
        return heroDao.retrieveHeroByPower(power, limit, offset);
    }

    @Override
    public List<Hero> retrieveHeroBySighting(Sighting sighting, int limit, int offset) {
        return heroDao.retrieveHeroBySighting(sighting, limit, offset);
    }

    @Override
    public List<Hero> retrieveHeroByOrganization(Organization organization, int limit, int offset) {
        return heroDao.retrieveHeroByOrganization(organization, limit, offset);
    }
}
