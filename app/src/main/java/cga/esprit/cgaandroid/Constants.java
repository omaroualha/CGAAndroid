package cga.esprit.cgaandroid;

import com.fatboyindustrial.gsonjodatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;

/**
 * Created by gofurs on 26/11/17.
 */

public class Constants {
    public class API{
        public static final String BASE_URL="http://172.16.52.36:8080";
        public static final String API_CLAIM=BASE_URL+"/rest/claim";
    }

    private static final GsonBuilder GSON_BASE = Converters
            .registerAll(new GsonBuilder().disableHtmlEscaping())
            .setDateFormat(DateFormat.FULL, DateFormat.FULL);

    public static Gson GSON = GSON_BASE
            .create();

}
