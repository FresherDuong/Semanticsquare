package com.semanticsquare.thrillio.util;

import java.io.IOException;
import java.net.*;

public class HttpConnect {

    public static String download(String sourUrl) throws URISyntaxException, MalformedURLException {
        System.out.println("Downloading: " + sourUrl);
        //URL url = new URI(sourUrl).toURL();
        URI uri = new URI(sourUrl);
        URL url = uri.toURL();

        try {
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            int responseCode = con.getResponseCode();

            if(responseCode >= 200 && responseCode < 300) { //connect Ok if responseCode >=200 <300
                return IOUtil.read(con.getInputStream());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
