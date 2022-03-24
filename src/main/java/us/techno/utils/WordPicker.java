package us.techno.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URISyntaxException;


public class WordPicker {

    public static String pickNewWord() throws URISyntaxException, IOException {
        Gson gson = new Gson();
        //String str = gson.fromJson(, String.class);
        return "great";
    }
}
