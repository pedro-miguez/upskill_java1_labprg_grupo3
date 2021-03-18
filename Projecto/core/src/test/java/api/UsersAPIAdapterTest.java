package api;

import application.UsersAPI;
import domain.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class responsible for testing the users API adapter.
 * 
 * @author Grupo 3
 */
public class UsersAPIAdapterTest{

    //Tests getting context.
    @Test
    public void testGetContext() {
        UsersAPI uapi = new UsersAPI();
        String result = uapi.getContext();
        String expected = uapi.getContext();

        assertEquals(result, expected);
    }

    //Tests the login.
    @Test
    public void testLogin() {
        UsersAPI uapi = new UsersAPI();
        assertTrue(uapi.login("testpedroman", "qwerty"));
    }

    //Tests the logout.
    @Test
    public void testLogout() {
        UsersAPI uapi = new UsersAPI();
        uapi.login("testpedroman", "qwerty");
        assertTrue(uapi.logout());
    }


}