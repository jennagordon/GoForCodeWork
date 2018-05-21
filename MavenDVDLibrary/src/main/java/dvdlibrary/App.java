package dvdlibrary;

import dvdlibrary.controller.DVDLibraryController;
import dvdlibrary.dao.DVDLibraryDao;
import dvdlibrary.dao.DVDLibraryDaoFileImpl;
import dvdlibrary.service.DVDLibraryServiceLayer;
import dvdlibrary.service.DVDLibraryServiceLayerImpl;
import dvdlibrary.ui.DVDLibraryView;
import dvdlibrary.ui.UserIO;
import dvdlibrary.ui.UserIOConsoleImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        /*
        UserIO myIO = new UserIOConsoleImpl();
        DVDLibraryView myView = new DVDLibraryView(myIO);
        DVDLibraryDao myDao = new DVDLibraryDaoFileImpl();
        DVDLibraryServiceLayer myService = new DVDLibraryServiceLayerImpl(myDao);
        DVDLibraryController controller = new DVDLibraryController(myService, myView);

        controller.run();*/

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        DVDLibraryController controller = ctx.getBean("controller", DVDLibraryController.class);
        controller.run();
    }
}
