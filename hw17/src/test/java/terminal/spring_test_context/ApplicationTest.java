package terminal.spring_test_context;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import terminal.MainTerminal;
import terminal.model.*;
import terminal.writer.ConsoleWriter;
import terminal.writer.Writer;

@Configuration
@ComponentScan
public class ApplicationTest {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    Account getAccount() {
        return new AccountImpl();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    PinValidator getPinValidator() {
        return  new PinValidatorImpl();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    Terminal getTerminal() {
        return new TerminalImpl(getTerminalServer(), getPinValidator());
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    TerminalServer getTerminalServer() {
        return new TerminalServerImpl();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    Writer getWriter() {
        return new ConsoleWriter();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    MainTerminal getMainTerminal() {
        return new MainTerminal(getTerminal(), getWriter());
    }
}
