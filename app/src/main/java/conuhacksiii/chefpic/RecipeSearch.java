package conuhacksiii.chefpic;

import android.content.res.Resources;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;



/**
 * Created by Anthony on 2018-01-27.
 */

public class RecipeSearch {

    public static final String APP_ID = Resources.getSystem().getString(R.string.edamam_app_id);
    public static final String APP_KEY = Resources.getSystem().getString(R.string.edamam_app_key);

    //https://api.edamam.com/search?q=taco&app_id=6327a91c&app_key=64c645227eb440aef738ec34994d4079

    //private String url = "https://api.edamam.com/search?q=" + meal + "&app_id=" + APP_ID + "&app_key=" + APP_KEY;

    public void findRecipes(String meal)
    {
        String url = "https://api.edamam.com/search?q=" + meal + "&app_id=" + APP_ID + "&app_key=" + APP_KEY;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        try {
            Response response = client.newCall(request).execute();
            processResult(response);
        }
        catch (IOException e){

        }

    }

    public ArrayList<Recipe> processResult(Response response){
        ArrayList<Recipe> recipes = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if(response.isSuccessful()){
                JSONObject returnJSON = new JSONObject(jsonData);
                JSONArray recipesListJSON = returnJSON.getJSONArray("hits");

                for(int i=0; i<=10; ++i){
                    JSONObject recipeArrayJSON = recipesListJSON.getJSONObject(i);
                    JSONObject recipeJSON = recipeArrayJSON.getJSONObject("recipe");

                    String title = recipeJSON.getString("label");
                    recipes.setTitle(title);

                    String image = recipeJSON.getString("image");
                    recipes.setImage(image);

                    String url = recipeJSON.getString("url");
                    recipes.setUrl(url);

                    String serving = recipeJSON.getString("yield");
                    recipes.setYield(serving);

                    String healthLabels = recipeJSON.getJSONArray("healthLabels").toString();
                    recipes.setHealthLabels(healthLabels);

                    String topIngredients = recipeJSON.getJSONArray("ingredientLines").toString();
                    recipes.setTopIngredients(topIngredients);

                }
            }

        }
        catch(IOException e){
        }
        catch(JSONException e){
        }
        return recipes;
    }

}
