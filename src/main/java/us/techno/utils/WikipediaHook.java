package us.techno.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WikipediaHook {
    public static String getWikipediaPage(String word) throws IOException {
        URL url = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&uselang=user&prop=extracts&titles=" + word + "&formatversion=2&exsentences=10&exlimit=1&explaintext=1");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        System.out.println(content);
        return content.toString();
    }
}
