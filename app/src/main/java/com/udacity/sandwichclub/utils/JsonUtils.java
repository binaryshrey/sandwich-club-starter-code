package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        // check whether input(json) is null or not
        if (json == null || json.isEmpty()) {
            return null;
        }
        try{
            //creating an object
            Sandwich sandwichClub = new Sandwich();
            JSONObject sandwichClubJsonObject = new JSONObject(json);

            JSONObject sandwichNameJsonObject = sandwichClubJsonObject.getJSONObject("name");
            sandwichClub.setMainName(sandwichNameJsonObject.getString("mainName"));
            JSONArray alsoKnownAsJsonArray = sandwichNameJsonObject.getJSONArray("alsoKnownAs");
            //check if array is null or not
            if (alsoKnownAsJsonArray != null) {
                //creating ArrayList to store the contents of alsoKnownAs key
                ArrayList<String> aKAList = new ArrayList<>();
                for (int i = 0; i < alsoKnownAsJsonArray.length(); i++) {
                    aKAList.add(alsoKnownAsJsonArray.getString(i));
                }
                sandwichClub.setAlsoKnownAs(aKAList);
            }

            sandwichClub.setPlaceOfOrigin(sandwichClubJsonObject.getString("placeOfOrigin"));
            sandwichClub.setDescription(sandwichClubJsonObject.getString("description"));
            sandwichClub.setImage(sandwichClubJsonObject.getString("image"));

            JSONArray ingredientsJsonArray = sandwichClubJsonObject.getJSONArray("ingredients");
            //check if array is null or not
            if (ingredientsJsonArray != null) {
                //creating ArrayList to store the contents of ingredients key
                ArrayList<String> iList = new ArrayList<>();
                for (int i = 0; i < ingredientsJsonArray.length(); i++) {
                    iList.add(ingredientsJsonArray.getString(i));
                }
                sandwichClub.setIngredients(iList);
            }

            return sandwichClub;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
