package com.sg.service;

import com.sg.dao.LocationDao;
import com.sg.dto.Location;

import javax.inject.Inject;
import java.util.List;

public class LocationServiceImpl implements LocationService {


    LocationDao locationDao;

    @Inject
    public LocationServiceImpl(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Override
    public Location addLocation(Location location) {
        return locationDao.addLocation(location);
    }

    @Override
    public void removeLocation(Location location) {

        locationDao.removeLocation(location);

    }

    @Override
    public void updateLocation(Location location) {

        locationDao.updateLocation(location);

    }

    @Override
    public List<Location> retrieveAllLocations(int limit, int offset) {
        return locationDao.retrieveAllLocations(limit, offset);
    }

    @Override
    public Location retrieveLocation(Long locationId) {
        return locationDao.retrieveLocation(locationId);
    }
}
