package conuhacksiii.chefpic;

/**
 * Created by laura on 2018-01-27.
 */

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ScreenSlidePagerActivity extends FragmentActivity {

    private static final int NUM_PAGES = 10; //number of recipes to display
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    private RecipeSearch rs;
    private ArrayList<Recipe> recipes;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_display_layout);

        rs = new RecipeSearch();
        Intent intent = getIntent();
        new RecipeAsyncTask().execute(intent.getStringExtra("EXTRA_RESULTS"));
    }

    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Recipe newRecipe = recipes.get(position);
            return ScreenSlidePageFragment.newInstance(newRecipe.getImage(),
                    newRecipe.getTitle(),
                    newRecipe.getServing(),
                    newRecipe.getRecipeURL(),
                    newRecipe.getTopIngredients());
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    class RecipeAsyncTask extends AsyncTask<String, Void, Void> {

        private String result = "";

        protected Void doInBackground(String... meal) {
            recipes = rs.findRecipes(meal[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mPager = (ViewPager) findViewById(R.id.pager);
            mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
            mPager.setAdapter(mPagerAdapter);
        }
    }

}