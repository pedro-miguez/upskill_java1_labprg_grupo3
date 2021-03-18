package domain;

import org.junit.Test;

import java.nio.file.WatchEvent;

import static org.junit.Assert.*;

/**
 * Class responsible for testing the website.
 * 
 * @author Grupo 3
 */
public class WebsiteTest {

    //Tests a valid website.
    @Test
    public void testCreateWebsiteValido() {

        Website site = new Website("www.org.pt");

        String expected = "www.org.pt";

        String result = site.getWebsite();

        assertEquals(expected, result);
    }

    //Tests a valid website.
    @Test
    public void testCreateWebsiteValido2() {

        Website site = new Website("www.org.uk.pt");

        String expected = "www.org.uk.pt";

        String result = site.getWebsite();

        assertEquals(expected, result);
    }


    //Tests a invalid website.
    @Test(expected = IllegalArgumentException.class)
    public void testCreateWebsiteInvalido() {

        Website site = new Website("www.orgpt");

    }


    //Tests a invalid website.
    @Test(expected = IllegalArgumentException.class)
    public void testCreateWebsiteInvalido2() {

        Website site = new Website("www.org.pt (asd)");
    }

    //Tests a empty website.
    @Test(expected = IllegalArgumentException.class)
    public void testCreateWebsiteVazio() {

        Website site = new Website(" ");

    }


}