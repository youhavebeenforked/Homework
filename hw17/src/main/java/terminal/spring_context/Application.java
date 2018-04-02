package terminal.spring_context;

import org.springframework.context.annotation.*;
import terminal.MainTerminal;
import terminal.model.*;
import terminal.writer.*;

@Configuration
@ComponentScan
public class Application {

    @Bean
    Account getAccount() {
        return new AccountImpl();
    }

    @Bean
    PinValidator getPinValidator() {
        return  new PinValidatorImpl();
    }

    @Bean
    Terminal getTerminal() {
        return new TerminalImpl(getTerminalServer(), getPinValidator());
    }

    @Bean
    TerminalServer getTerminalServer() {
        return new TerminalServerImpl();
    }

    @Bean
    Writer getWriter() {
        return new ConsoleWriter();
    }

    @Bean
    MainTerminal getMainTerminal() {
        return new MainTerminal(getTerminal(), getWriter());
    }
}