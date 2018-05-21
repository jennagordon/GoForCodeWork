package com.sg.dvd.library.ui;

import com.sg.dvd.library.controller.DVDLibraryController;
import com.sg.dvd.library.dto.DVD;

import java.util.List;

public class DVDLibraryView {
    UserIO io;

    public int printMenuAndGetSelection() {

        io.print("Main Menu");
        io.print("1. Add DVD");
        io.print("2. Delete DVD");
        io.print("3. Edit DVD");
        io.print("4. List all DVDs");
        io.print("5. List one DVD");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public DVD createDVD(){
        String title = io.readString("Please enter the DVD's title");
        String releaseDate = io.readString("Please enter the DVD's release date");
        String mpaa = io.readString("Please enter the DVD's MPAA rating");
        String directorName = io.readString("Please enter the DVD's Director's Name");
        String studio = io.readString("Please enter the DVD's studio");
        String userRating = io.readString("Please enter your rating or note");

        DVD currentDVD = new DVD(title);

        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setMpaa(mpaa);
        currentDVD.setDirectorName(directorName);
        currentDVD.setStudio(studio);
        currentDVD.setUserRating(userRating);

        return currentDVD;
    }

    public void displayCreateDVDBanner() {
        io.print("=== Create DVD ===");
    }

    public void displayCreateSuccessfulBanner() {
        io.readString("DVD successfully created. Please hit enter to continue");
    }

    public void displayDVDList(List<DVD> dvdList) {
        for(DVD currentDVD : dvdList) {
            io.print(currentDVD.getTitle() + ": "
            + currentDVD.getReleaseDate() + " "
            + currentDVD.getMpaa() + " "
            + currentDVD.getDirectorName() + " "
            + currentDVD.getStudio() + " "
            + currentDVD.getUserRating());
        }
        io.readString("Please hit enter to continue");
    }

    public void displayDisplayDVDListBanner() {
        io.print("=== All DVDs ===");
    }

    public void displayDisplayDVDBanner() {
        io.print("=== One DVD ===");
    }

    public String getDVDTitle() {
        return io.readString("Please enter the DVD title");
    }

    public void displayDVD(DVD dvd) {
        // if dvd title matches what user entered
        // then display the dvd
        if (dvd != null) {
            io.print(dvd.getTitle());
            io.print(dvd.getReleaseDate());
            io.print(dvd.getMpaa());
            io.print(dvd.getDirectorName());
            io.print(dvd.getStudio());
            io.print(dvd.getUserRating());

        // if dvd title is null
        } else {
            io.print("No such DVD");
        }
        io.readString("Please hit enter to continue");
    }

    //Banners for delete feature
    public void displayDeleteDVDBanner() {
        io.print("=== Delete DVD ===");
    }

    public void displayDeleteSuccessBanner() {
        io.readString("DVD successfully deleted. Please hit enter to continue");
    }

    //Banner for exiting
    public void displayExitBanner() {
        io.print("GOOD BYE!!!");
    }

    //Banner for the unknown command
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!");
    }

    //Banner for error messaging
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public DVDLibraryView(UserIO io) {
        this.io = io;
    }

    public DVD editDVD() {

        String Title = getDVDTitle();
        String releaseDate = io.readString("Please enter the DVD's release date");
        String mpaa = io.readString("Please enter the DVD's MPAA rating");
        String directorName = io.readString("Please enter the DVD's Director's Name");
        String studio = io.readString("Please enter the DVD's studio");
        String userRating = io.readString("Please enter your rating or note");

        DVD currentDVD = new DVD(Title);

        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setMpaa(mpaa);
        currentDVD.setDirectorName(directorName);
        currentDVD.setStudio(studio);
        currentDVD.setUserRating(userRating);

        return currentDVD;
    }

    //Banners for Edit feature
    public void displayEditBanner() {
        io.print("=== Edit DVD ===");
    }

    public void displayEditSuccessBanner() {
        io.readString("DVD successfully edited. Please hit enter to continue.");
    }

}
