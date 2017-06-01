package qbecker.cartracker;

import java.util.UUID;
/**
 * Created by qbecker on 6/1/17.
 */

public class Utilities {

    //// TODO: 6/1/17 check db to make sure the key is not in use, if it is create another rince repeate
    public static String CreateUID(){
        String raw = UUID.randomUUID().toString();
        String[] postSplit = raw.split("-");
        return postSplit[0];
    }
}
