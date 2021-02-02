package api;

import application.UsersAPI;
import domain.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UsersAPIAdapterTest{

    @Test
    public void testGetContext() {
        UsersAPI uapi = new UsersAPI();
        String result = uapi.getContext();
        String expected = uapi.getContext();

        assertEquals(result, expected);
    }

    @Test
    public void testRegisterAndLogin() {
        UsersAPI uapi = new UsersAPI();
        assertTrue(uapi.login("testpedroman", "qwerty"));
    }

    @Test
    public void testLogout() {
        UsersAPI uapi = new UsersAPI();

        assertTrue(uapi.logout());
    }

    public void testRegisterUser() {
    }

    public void testRegisterUserWithRoles() {
    }

    public void testGetSession() {
        UsersAPI uapi = new UsersAPI();
        uapi.getEmail();
    }
}