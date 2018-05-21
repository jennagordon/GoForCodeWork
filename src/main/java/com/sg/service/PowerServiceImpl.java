package com.sg.service;

import com.sg.dao.PowerDao;
import com.sg.dto.Hero;
import com.sg.dto.Power;

import javax.inject.Inject;
import java.util.List;

public class PowerServiceImpl implements PowerService {

    PowerDao powerDao;

    @Inject
    public PowerServiceImpl(PowerDao powerDao) {
        this.powerDao = powerDao;
    }

    @Override
    public Power addPower(Power power) {
        return powerDao.addPower(power);
    }

    @Override
    public void removePower(Power power) {

        powerDao.removePower(power);

    }

    @Override
    public void updatePower(Power power) {

        powerDao.updatePower(power);

    }

    @Override
    public List<Power> retrieveAllPowers(int limit, int offset) {
        return powerDao.retrieveAllPowers(limit, offset);
    }

    @Override
    public Power retrievePower(Long powerId) {
        return powerDao.retrievePower(powerId);
    }

    @Override
    public List<Power> retrievePowerByHero(Hero hero, int limit, int offset) {
        return powerDao.retrievePowerByHero(hero, limit, offset);
    }
}
