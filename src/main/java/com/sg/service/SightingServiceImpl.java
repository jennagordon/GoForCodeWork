package com.sg.service;

import com.sg.dao.SightingDao;
import com.sg.dto.Hero;
import com.sg.dto.Location;
import com.sg.dto.Sighting;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

public class SightingServiceImpl implements SightingService {

    SightingDao sightingDao;

    @Inject
    public SightingServiceImpl(SightingDao sightingDao) {
        this.sightingDao = sightingDao;
    }

    @Override
    public Sighting addSighting(Sighting sighting) {
        return sightingDao.addSighting(sighting);
    }

    @Override
    public void removeSighting(Sighting sighting) {

        sightingDao.removeSighting(sighting);

    }

    @Override
    public void updateSighting(Sighting sighting) {

        sightingDao.updateSighting(sighting);
    }

    @Override
    public List<Sighting> retrieveAllSightings(int limit, int offset) {
        return sightingDao.retrieveAllSightings(limit, offset);
    }

    @Override
    public Sighting retrieveSighting(Long sightingId) {
        return sightingDao.retrieveSighting(sightingId);
    }

    @Override
    public List<Sighting> retrieveSightingByHero(Hero hero, int limit, int offset) {
        return sightingDao.retrieveSightingByHero(hero, limit, offset);
    }

    @Override
    public List<Sighting> retrieveSightingByLocation(Location location, int limit, int offset) {
        return sightingDao.retrieveSightingByLocation(location, limit, offset);
    }

    @Override
    public List<Sighting> retrieveSightingByDate(LocalDate localDate, int limit, int offset) {
        return sightingDao.retrieveSightingByDate(localDate, limit, offset);
    }
}
