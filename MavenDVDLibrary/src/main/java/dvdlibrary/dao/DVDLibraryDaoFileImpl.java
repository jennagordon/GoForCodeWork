package dvdlibrary.dao;


import dvdlibrary.dto.DVD;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DVDLibraryDaoFileImpl implements DVDLibraryDao{
    private Map<String, DVD> dvdMap = new HashMap<>();

    public static final String LIBRARY_FILE = "Library.txt";
    public static final String DELIMITER = "::";

    @Override
    public DVD createDVD(String title, DVD dvdInfo) throws DVDLibraryPersistenceException {
        //adds new DVD to the dvd Map
        DVD newDVD = dvdMap.put(title, dvdInfo);
        writeLibrary();
        return newDVD;
    }

    @Override
    public DVD deleteDVD(String title) throws DVDLibraryPersistenceException {
        //calling the map to find DVD by title
        DVD deleteDVD = dvdMap.remove(title);
        writeLibrary();
        return deleteDVD;
    }

    @Override
    public DVD editDVD(String title, DVD dvdInfo) throws DVDLibraryPersistenceException {

        DVD editDVD = dvdMap.put(title, dvdInfo);
        writeLibrary();
        return editDVD;
    }

    @Override
    public List<DVD> getAllDVD() throws DVDLibraryPersistenceException {
        // returns all dvds from the dvd map
        loadLibrary();
        return new ArrayList<DVD>(dvdMap.values());
    }

    @Override
    public DVD getDVD(String title) throws DVDLibraryPersistenceException {
        //get one DVD by title
        loadLibrary();
        return dvdMap.get(title);
    }

    private void loadLibrary() throws DVDLibraryPersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryPersistenceException("-_- Could not load roster data into memory.");
        }

        String currentLine;

        String[] currentTokens;

        while(scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            DVD currentDVD = new DVD(currentTokens[0]);
            currentDVD.setReleaseDateLocal(LocalDate.parse(currentTokens[1]));
            currentDVD.setMpaa(currentTokens[2]);
            currentDVD.setDirectorName(currentTokens[3]);
            currentDVD.setStudio(currentTokens[4]);
            currentDVD.setUserRating(currentTokens[5]);

            dvdMap.put(currentDVD.getTitle(), currentDVD);
        }
        scanner.close();
    }

    private void writeLibrary() throws DVDLibraryPersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e) {
            throw new DVDLibraryPersistenceException("Could not save student data.", e);
        }

        List<DVD> dvdList = this.getAllDVD();
        for (DVD currentDVD : dvdList) {
            out.println(currentDVD.getTitle() + DELIMITER
            + currentDVD.getReleaseDateLocal() + DELIMITER
            + currentDVD.getMpaa() + DELIMITER
            + currentDVD.getDirectorName() + DELIMITER
            + currentDVD.getStudio() + DELIMITER
            + currentDVD.getUserRating());

            out.flush();
        }
        out.close();
    }
}
