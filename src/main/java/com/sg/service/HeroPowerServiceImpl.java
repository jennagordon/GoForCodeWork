package com.sg.service;

import com.sg.dao.HeroPowerDao;
import com.sg.dto.HeroPower;

import javax.inject.Inject;
import java.util.List;

public class HeroPowerServiceImpl implements HeroPowerService {

    HeroPowerDao heroPowerDao;

    @Inject
    public HeroPowerServiceImpl(HeroPowerDao heroPowerDao) {
        this.heroPowerDao = heroPowerDao;
    }

    @Override
    public HeroPower addHeroPower(HeroPower heroPower) {
        return heroPowerDao.addHeroPower(heroPower);
    }

    @Override
    public void removeHeroPower(HeroPower heroPower) {

        heroPowerDao.removeHeroPower(heroPower);

    }

    @Override
    public void updateHeroPower(HeroPower heroPower) {

        heroPowerDao.updateHeroPower(heroPower);

    }

    @Override
    public HeroPower retrieveHeroPowerById(Long heroPowerId) {
        return heroPowerDao.retrieveHeroPowerById(heroPowerId);
    }

    @Override
    public List<HeroPower> retrieveHeroPowerByPower(Long powerId) {
        return heroPowerDao.retrieveHeroPowerByPower(powerId);
    }

    @Override
    public List<HeroPower> retrieveHeroPowerByHero(Long heroId) {
        return heroPowerDao.retrieveHeroPowerByHero(heroId);
    }
}
