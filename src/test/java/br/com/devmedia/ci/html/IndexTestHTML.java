package br.com.devmedia.ci.html;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class IndexTestHTML {

    @Test
    public void testGoogle() throws IOException {
        WebClient webClient = new WebClient();
        HtmlPage currentPage = webClient.getPage("http://www.google.com/");
        assertEquals("Google", currentPage.getTitleText());
    }

}
