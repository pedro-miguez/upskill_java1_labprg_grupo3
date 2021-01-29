package domain;

import org.junit.Test;

import java.nio.file.WatchEvent;

import static org.junit.Assert.*;

public class WebsiteTest {

    @Test
    public void createWebsiteValido() {

        Website site = new Website("www.org.pt");

        String expected = "www.org.pt";

        String result = site.getWebsite();

        assertEquals(expected, result);
    }

    @Test
    public void createWebsiteValido2() {

        Website site = new Website("www.org.pt/asd");

        String expected = "www.org.pt/asd";

        String result = site.getWebsite();

        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateWebsiteInvalido() {

        Website site = new Website("wwworg.pt");

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateWebsiteInvalido2() {

        Website site = new Website("www.orgpt");

    }


    @Test(expected = IllegalArgumentException.class)
    public void testCreateWebsiteInvalido3() {

        Website site = new Website("www.org.pt (asd)");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateWebsiteVazio() {

        Website site = new Website(" ");

    }


}