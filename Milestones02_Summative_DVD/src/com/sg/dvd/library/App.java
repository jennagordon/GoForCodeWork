package com.sg.dvd.library;

import com.sg.dvd.library.controller.DVDLibraryController;
import com.sg.dvd.library.dao.DVDLibraryDao;
import com.sg.dvd.library.dao.DVDLibraryDaoFileImpl;
import com.sg.dvd.library.service.DVDLibraryServiceLayer;
import com.sg.dvd.library.service.DVDLibraryServiceLayerImpl;
import com.sg.dvd.library.ui.DVDLibraryView;
import com.sg.dvd.library.ui.UserIO;
import com.sg.dvd.library.ui.UserIOConsoleImpl;

public class App {
    public static void main(String[] args) {
        UserIO myIO = new UserIOConsoleImpl();
        DVDLibraryView myView = new DVDLibraryView(myIO);
        DVDLibraryDao myDao = new DVDLibraryDaoFileImpl();
        DVDLibraryServiceLayer myService = new DVDLibraryServiceLayerImpl(myDao);
        DVDLibraryController controller = new DVDLibraryController(myService, myView);

        controller.run();
    }
}
