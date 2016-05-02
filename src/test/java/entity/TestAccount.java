package entity;


import org.home.entity.Account;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestAccount {
    private static final Logger logger =
            (Logger) LoggerFactory.getLogger(TestAccount.class);

    @Test
    public void testDefaultAccount(){
        Account defaultAccount = Account.DEFAULT_ACCOUNT;
        logger.debug("Default account print " + defaultAccount);

        Account account = new Account();
        logger.debug("Created account to string " + account);

    }
}
