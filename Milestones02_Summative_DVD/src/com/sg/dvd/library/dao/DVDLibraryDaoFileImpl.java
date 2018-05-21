package com.sg.dvd.library.dao;

import com.sg.dvd.library.dto.DVD;

import java.io.*;
import java.util.*;

public class DVDLibraryDaoFileImpl implements DVDLibraryDao{
    private Map<String, DVD> dvdMap = new HashMap<>();

    public static final String ROSTER_FILE = "roster.txt";
    public static final String DELIMITER = "::";

    @Override
    public DVD createDVD(String title, DVD dvdInfo) throws DVDLibraryDaoException {
        //adds new DVD to the dvd Map
        DVD newDVD = dvdMap.put(title, dvdInfo);
        writeRoster();
        return newDVD;
    }

    @Override
    public DVD deleteDVD(String title) throws DVDLibraryDaoException {
        //calling the map to find DVD by title
        DVD deleteDVD = dvdMap.remove(title);
        writeRoster();
        return deleteDVD;
    }

    @Override
    public DVD editDVD(String title, DVD dvdInfo) throws DVDLibraryDaoException {

        DVD editDVD = dvdMap.put(title, dvdInfo);
        writeRoster();
        return editDVD;
    }

    @Override
    public List<DVD> getAllDVD() throws DVDLibraryDaoException {
        // returns all dvds from the dvd map
        loadRoster();
        return new ArrayList<DVD>(dvdMap.values());
    }

    @Override
    public DVD getDVD(String title) throws DVDLibraryDaoException {
        //get one DVD by title
        loadRoster();
        return dvdMap.get(title);
    }

    public void loadRoster() throws DVDLibraryDaoException {
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(ROSTER_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryDaoException("-_- Could not load roster data into memory.");
        }

        String currentLine;

        String[] currentTokens;

        while(scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            DVD currentDVD = new DVD(currentTokens[0]);
            currentDVD.setReleaseDate(currentTokens[1]);
            currentDVD.setMpaa(currentTokens[2]);
            currentDVD.setDirectorName(currentTokens[3]);
            currentDVD.setStudio(currentTokens[4]);
            currentDVD.setUserRating(currentTokens[5]);

            dvdMap.put(currentDVD.getTitle(), currentDVD);
        }
        scanner.close();
    }

    private void writeRoster() throws DVDLibraryDaoException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ROSTER_FILE));
        } catch (IOException e) {
            throw new DVDLibraryDaoException("Could not save student data.", e);
        }

        List<DVD> dvdList = this.getAllDVD();
        for (DVD currentDVD : dvdList) {
            out.println(currentDVD.getTitle() + DELIMITER
            + currentDVD.getReleaseDate() + DELIMITER
            + currentDVD.getMpaa() + DELIMITER
            + currentDVD.getDirectorName() + DELIMITER
            + currentDVD.getStudio() + DELIMITER
            + currentDVD.getUserRating());

            out.flush();
        }
        out.close();
    }
}
