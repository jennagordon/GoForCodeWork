package com.sg.dao;

import com.sg.model.DVD;

import java.util.List;

public interface DvdDao {

    public DVD addDvd(DVD dvd);

    public void removeDvd(int dvdId);

    public void updateDvd(DVD dvd);

    public List<DVD> retrieveAllDvds();

    public DVD retrieveDvdById(int dvdId);

    public List<DVD> searchContacts(String userSearch, String searchTerm);
}
