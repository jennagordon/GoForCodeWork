package com.sg.service;

import com.sg.dto.Hero;
import com.sg.dto.Location;
import com.sg.dto.Sighting;

import java.time.LocalDate;
import java.util.List;

public interface SightingService {

    Sighting addSighting(Sighting sighting);

    void removeSighting(Sighting sighting);

    void updateSighting(Sighting sighting);

    List<Sighting> retrieveAllSightings(int limit, int offset);

    Sighting retrieveSighting(Long sightingId);

    List<Sighting> retrieveSightingByHero(Hero hero, int limit, int offset);

    List<Sighting> retrieveSightingByLocation(Location location, int limit, int offset);

    List<Sighting> retrieveSightingByDate(LocalDate date, int limit, int offset);
}
