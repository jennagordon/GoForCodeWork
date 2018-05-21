package com.sg.dao;

import com.sg.dto.Hero;
import com.sg.dto.Location;
import com.sg.dto.Location;

import java.util.List;

public interface LocationDao {

    Location addLocation(Location location);

    void removeLocation(Location location);

    void updateLocation(Location location);

    List<Location> retrieveAllLocations(int limit, int offset);

    Location retrieveLocation(Long locationId);

}
