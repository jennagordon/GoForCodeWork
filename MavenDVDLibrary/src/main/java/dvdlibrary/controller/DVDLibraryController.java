package dvdlibrary.controller;


import dvdlibrary.dao.DVDLibraryPersistenceException;
import dvdlibrary.dto.DVD;
import dvdlibrary.service.DVDLibraryServiceLayer;
import dvdlibrary.ui.DVDLibraryView;

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
        } catch (DVDLibraryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void addDVD() throws DVDLibraryPersistenceException {
       view.displayCreateDVDBanner();
       DVD newDVD = view.createDVD();
       service.createDVD(newDVD.getTitle(), newDVD); //calling createDVD method in DVDLibraryDAO
       view.displayCreateSuccessfulBanner();
    }

    private void getAllDVD() throws DVDLibraryPersistenceException {
        view.displayDisplayDVDListBanner();
        List<DVD> dvdList = service.getAllDVD();
        view.displayDVDList(dvdList);
    }

    private void getDVD() throws DVDLibraryPersistenceException {
        view.displayDisplayDVDBanner();
        //gets dvd title from user input using the view
        String title = view.getDVDTitle();
        // calling service(DAO) to display the dvd info just by title
        // when calling service we could hit DVD not found
        // because dao would throw dvd not found exception to service
        // service would throw it to us (controller) and we need to handle
        // add try catch for not found exception
        // if this happens give message to user via view
        DVD dvd = service.getDVD(title);
        view.displayDVD(dvd);
    }

    private void deleteDVD() throws DVDLibraryPersistenceException {
        view.displayDeleteDVDBanner();
        //gets dvd title from user input using the view
        String title = view.getDVDTitle();
        //calling DAO to delete DVD by title
        service.deleteDVD(title);
        view.displayDeleteSuccessBanner();

    }

    private void editDVD() throws DVDLibraryPersistenceException {

        //print banner
        view.displayEditBanner();
        // getting title to update from user
        String title = view.promptUserForDvdTitle();


        // pass dvd title to get DVD key (title)
        DVD dvdToUpdate = service.getDVD(title);
        // pass key (title) to view to ask user for updates
        DVD editedDVD = view.promptUserForDvdUpdates(dvdToUpdate);

        //pass dvd object to the service
        service.editDVD(title, editedDVD);

        // Display success banner
        view.displayEditSuccessBanner();




        //edit info
        //DVD objects know their own title
//        DVD newDVD = view.editDVD();
//        String title = newDVD.getTitle();
//        service.editDVD(title, newDVD);



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
