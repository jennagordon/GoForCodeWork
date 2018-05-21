package com.sg.dvd.library.controller;

import com.sg.dvd.library.dao.DVDLibraryDao;
import com.sg.dvd.library.dao.DVDLibraryDaoException;
import com.sg.dvd.library.dao.DVDLibraryDaoFileImpl;
import com.sg.dvd.library.dto.DVD;
import com.sg.dvd.library.service.DVDLibraryServiceLayer;
import com.sg.dvd.library.ui.UserIO;
import com.sg.dvd.library.ui.UserIOConsoleImpl;
import com.sg.dvd.library.ui.DVDLibraryView;

import java.util.List;

public class DVDLibraryController {
    DVDLibraryView view;
    //DVDLibraryView view = new DVDLibraryView();      BEFORE THE REFACTOR
    DVDLibraryServiceLayer service;

    public DVDLibraryController(DVDLibraryServiceLayer myService, DVDLibraryView myView) {
        this.service = myService;
        this.view = myView;
    }

    //DVDLibraryDao dao;
    //DVDLibraryDao dao = new DVDLibraryDaoFileImpl();   BEFORE THE REFACTOR


    public void run () {
        boolean keepGoing = true;
        int menuSelection = 0;

        try {
            while (keepGoing)  {

                menuSelection = getMenuSelection();

                switch (menuSelection)  {
                    case 1:
                        addDVD();
                        break;
                    case 2:
                        deleteDVD();
                        break;
                    case 3:
                        editDVD();
                        break;
                    case 4:
                        getAllDVD();
                        break;
                    case 5:
                        getDVD();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (DVDLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void addDVD() throws DVDLibraryDaoException {
       view.displayCreateDVDBanner();
       DVD newDVD = view.createDVD();
       service.createDVD(newDVD.getTitle(), newDVD); //calling createDVD method in DVDLibraryDAO
       view.displayCreateSuccessfulBanner();
    }

    private void getAllDVD() throws DVDLibraryDaoException {
        view.displayDisplayDVDListBanner();
        List<DVD> dvdList = service.getAllDVD();
        view.displayDVDList(dvdList);
    }

    private void getDVD() throws DVDLibraryDaoException {
        view.displayDisplayDVDBanner();
        //gets dvd title from user input using the view
        String title = view.getDVDTitle();
        //calling DAO to display the dvd info just by title
        DVD dvd = service.getDVD(title);
        view.displayDVD(dvd);
    }

    private void deleteDVD() throws DVDLibraryDaoException {
        view.displayDeleteDVDBanner();
        //gets dvd title from user input using the view
        String title = view.getDVDTitle();
        //calling DAO to delete DVD by title
        service.deleteDVD(title);
        view.displayDeleteSuccessBanner();

    }

    private void editDVD() throws DVDLibraryDaoException {
        //print banner
        view.displayEditBanner();


        //get dvd by title
        //edit info
        //DVD objects know their own title
        DVD newDVD = view.editDVD();
        String title = newDVD.getTitle();
        service.editDVD(title, newDVD);

        view.displayEditSuccessBanner();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

//    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view) {
//       this.dao = dao;
//        this.view = view;
//    }
}
