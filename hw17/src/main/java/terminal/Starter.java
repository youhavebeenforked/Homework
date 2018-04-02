package terminal;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import terminal.spring_context.Application;

@Component
public class Starter {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(Application.class);
        MainTerminal mainTerminal = context.getBean(MainTerminal.class);

        try {
            mainTerminal.enterPin("7812");
            mainTerminal.checkBalance();
            mainTerminal.enterPin("7812");
            mainTerminal.checkBalance();
            mainTerminal.enterPin("7812");
            mainTerminal.checkBalance();
            mainTerminal.enterPin("7812");
            mainTerminal.checkBalance();
            Thread.sleep(1500);
            mainTerminal.checkBalance();
            Thread.sleep(1500);
            mainTerminal.enterPin("0000");
            mainTerminal.checkBalance();
            Thread.sleep(2100);
            mainTerminal.checkBalance();
            mainTerminal.enterPin("0000");
            mainTerminal.checkBalance();
            mainTerminal.withdrawMoney(152);
            mainTerminal.checkBalance();
            mainTerminal.withdrawMoney(200);
            mainTerminal.checkBalance();
            mainTerminal.putMoney(45);
            mainTerminal.checkBalance();
            mainTerminal.putMoney(100);
            mainTerminal.checkBalance();
            mainTerminal.withdrawMoney(100000);
            mainTerminal.checkBalance();
            mainTerminal.withdrawMoney(900);
            mainTerminal.checkBalance();
            mainTerminal.checkBalance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
