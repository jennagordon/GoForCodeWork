package com.sg.service;

import com.sg.dto.Location;

import java.util.List;

public interface LocationService {

    Location addLocation(Location location);

    void removeLocation(Location location);

    void updateLocation(Location location);

    List<Location> retrieveAllLocations(int limit, int offset);

    Location retrieveLocation(Long locationId);

}
