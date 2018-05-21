import controller.VendingMachineController;
import dao.VendingMachineDao;
import dao.VendingMachineDaoFileImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.VendingMachineServiceLayer;
import service.VendingMachineServiceLayerImpl;
import ui.UserIO;
import ui.UserIOConsoleImpl;
import ui.VendingMachineView;

public class App {
    public static void main(String[] args) {
        // BEFORE SPRING DI
//        UserIO myI0 = new UserIOConsoleImpl();
//        VendingMachineView myView = new VendingMachineView(myI0);
//        VendingMachineDao myDao = new VendingMachineDaoFileImpl();
//        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao);
//        VendingMachineController controller = new VendingMachineController(myService, myView);
//
//        controller.run();

        // Spring DI
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineController controller = ctx.getBean("controller", VendingMachineController.class);
        controller.run();



    }
}
