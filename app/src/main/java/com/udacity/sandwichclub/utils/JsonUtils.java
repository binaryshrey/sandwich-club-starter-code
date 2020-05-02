package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        // check whether json data is null or not
        if (json == null || json.isEmpty()) {
            return null;
        }
        try{
            //creating an object of Sandwich class
            Sandwich sandwichClub = new Sandwich();
            //creating JSONObject
            JSONObject sandwichClubJsonObject = new JSONObject(json);

            JSONObject sandwichNameJsonObject = sandwichClubJsonObject.getJSONObject("name");
            sandwichClub.setMainName(sandwichNameJsonObject.optString("mainName"));
            JSONArray alsoKnownAsJsonArray = sandwichNameJsonObject.getJSONArray("alsoKnownAs");
            //check if array is null or not
            if (alsoKnownAsJsonArray != null) {
                //creating ArrayList to store the contents of alsoKnownAs key
                ArrayList<String> alsoKnownAsList = new ArrayList<>();
                for (int i = 0; i < alsoKnownAsJsonArray.length(); i++) {
                    alsoKnownAsList.add(alsoKnownAsJsonArray.optString(i));
                }
                sandwichClub.setAlsoKnownAs(alsoKnownAsList);
            }

            sandwichClub.setPlaceOfOrigin(sandwichClubJsonObject.optString("placeOfOrigin"));
            sandwichClub.setDescription(sandwichClubJsonObject.optString("description"));
            sandwichClub.setImage(sandwichClubJsonObject.optString("image"));

            JSONArray ingredientsJsonArray = sandwichClubJsonObject.getJSONArray("ingredients");
            //check if array is null or not
            if (ingredientsJsonArray != null) {
                //creating ArrayList to store the contents of ingredients key
                ArrayList<String> iList = new ArrayList<>();
                for (int i = 0; i < ingredientsJsonArray.length(); i++) {
                    iList.add(ingredientsJsonArray.optString(i));
                }
                sandwichClub.setIngredients(iList);
            }
            //returning object
            return sandwichClub;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
