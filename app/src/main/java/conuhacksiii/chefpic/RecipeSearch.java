package conuhacksiii.chefpic;

import android.content.res.Resources;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;



/**
 * Created by Anthony on 2018-01-27.
 */

public class RecipeSearch {

    public static final String APP_ID = "6327a91c";
    public static final String APP_KEY = "64c645227eb440aef738ec34994d4079";

    //https://api.edamam.com/search?q=taco&app_id=6327a91c&app_key=64c645227eb440aef738ec34994d4079

    //private String url = "https://api.edamam.com/search?q=" + meal + "&app_id=" + APP_ID + "&app_key=" + APP_KEY;


    public ArrayList<Recipe> findRecipes(String meal)
    {
        String url = "https://api.edamam.com/search?q=" + meal + "&app_id=" + APP_ID + "&app_key=" + APP_KEY;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        try {
            Response response = client.newCall(request).execute();
            return processResult(response);
        }
        catch (IOException e){
        }

        return null;

    }


    public ArrayList<Recipe> processResult(Response response){
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();


        try {
            String jsonData = response.body().string();
            if(response.isSuccessful()){
                JSONObject returnJSON = new JSONObject(jsonData);
                JSONArray recipesListJSON = returnJSON.getJSONArray("hits");

                for(int i=0; i<10; ++i){

                    JSONObject recipeArrayJSON = recipesListJSON.getJSONObject(i);
                    JSONObject recipeJSON = recipeArrayJSON.getJSONObject("recipe");

                    String title = recipeJSON.getString("label");

                    String image = recipeJSON.getString("image");

                    String url = recipeJSON.getString("url");

                    String serving = recipeJSON.getString("yield");

                    JSONArray topIngredients = recipeJSON.getJSONArray("ingredientLines");

                    recipes.add(new Recipe(image, title, url, serving, JSONParser(topIngredients)));


                }
            }
        }
        catch(IOException e){
        }
        catch(JSONException e){
        }
        return recipes;
    }
    private String[] JSONParser(JSONArray array){
        String[] stringArray = new String[5];

        //cycling through first 5
        for(int i = 0; i < 5; ++i)
        {
            //take json string at value i
            try
            {
                String jsonString = array.getString(i);
                stringArray[i] = jsonString;
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        return stringArray;
    }

}
