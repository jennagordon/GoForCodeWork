import controller.ClassRosterController;
import dao.ClassRosterAuditDao;
import dao.ClassRosterAuditDaoFileImpl;
import dao.ClassRosterDao;
import dao.ClassRosterDaoFileImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.ClassRosterServiceLayer;
import service.ClassRosterServiceLayerImpl;
import ui.ClassRosterView;
import ui.UserIO;
import ui.UserIOConsoleImpl;

public class App {
    public static void main(String[] args) {
        //BEFORE THE SPRING REFACTOR
//        // Instantiate the UserIO implementation
//        UserIO myIo = new UserIOConsoleImpl();
//        //Instantiate the View and wire the UserIO implementation into it
//        ClassRosterView myView = new ClassRosterView(myIo);
//        //Instantiate the DAO
//        ClassRosterDao myDao = new ClassRosterDaoFileImpl();
//        //Instantiate the Audit DAO
//        ClassRosterAuditDao myAuditDao = new ClassRosterAuditDaoFileImpl();
//        //Instantiate the Service Layer and wire the DAO and Audit DAO into it
//        ClassRosterServiceLayer myService = new ClassRosterServiceLayerImpl(myDao, myAuditDao);
//        //Instantiate the Controller and wire the service layer into it
//        ClassRosterController controller = new ClassRosterController(myService, myView);
//        //Kick off the Controller
//        controller.run();

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ClassRosterController controller = ctx.getBean("controller", ClassRosterController.class);
        controller.run();
    }
}
