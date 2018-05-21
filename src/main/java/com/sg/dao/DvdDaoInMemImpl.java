package com.sg.dao;

import com.sg.model.DVD;

import java.util.*;

public class DvdDaoInMemImpl implements DvdDao {
    Map<Integer, DVD> dvdMap = new HashMap<>();
    private int dvdIdCounter = 0;

    @Override
    public DVD addDvd(DVD dvd) {
        dvd.setDvdId(dvdIdCounter);

        dvdIdCounter++;

        dvdMap.put(dvd.getDvdId(), dvd);

        return dvd;
    }

    @Override
    public void removeDvd(int dvdId) {

        dvdMap.remove(dvdId);
    }

    @Override
    public void updateDvd(DVD dvd) {
        dvdMap.put(dvd.getDvdId(), dvd);

    }

    @Override
    public List<DVD> retrieveAllDvds() {

        Collection<DVD> dvdCollection = dvdMap.values();

        return new ArrayList<>(dvdCollection);
    }

    @Override
    public DVD retrieveDvdById(int dvdId) {
        return dvdMap.get(dvdId);
    }

    @Override
    public List<DVD> searchContacts(String criteria, String userSearch) {

        // get all the dvds
        List<DVD> allDvdList = retrieveAllDvds();
        List<DVD> dvdSearchList = new ArrayList<>();

        for (DVD currentDvD : allDvdList) {

            if(currentDvD.getTitle().equals(criteria)) {
               dvdSearchList.add(currentDvD);
            }

            if(currentDvD.getDirector().equals(criteria)) {
                dvdSearchList.add(currentDvD);
            }

            if(currentDvD.getReleaseYear().toString().equals(criteria)) {
                dvdSearchList.add(currentDvD);
            }

            if(currentDvD.getRating().equals(criteria)) {
                dvdSearchList.add(currentDvD);
            }
        }
        return dvdSearchList;
    }
}
