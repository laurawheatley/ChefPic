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

    protected void onCreate(Bundle savedInstaceState){

        super.onCreate(savedInstaceState);
        setContentView(R.layout.recipe_display_layout);

        Intent intent = getIntent();
        rs = new RecipeSearch();
        new RecipeAsyncTask().execute(intent.getStringExtra("EXTRA_RESULTSS"));

        //creating a ViewPager and a PagerAdapter
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    public void onBackPressed(){

        //if mPager is at the first item
        if(mPager.getCurrentItem() == 0){
            super.onBackPressed(); //handling backwards -- i believe this will figure out the looping?
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
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
            ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
            Bundle args = new Bundle();
            args.putString("image", newRecipe.getImage());
            args.putString("title", newRecipe.getTitle());
            args.putString("url", newRecipe.getRecipeURL());
            args.putString("serving", newRecipe.getServing());
            args.putStringArray("topingredients", newRecipe.getTopIngredients());
            fragment.setArguments(args);
            fragment.initialize();
            //return new ScreenSlidePageFragment(newRecipe.getImage(), newRecipe.getTitle(), newRecipe.getRecipeURL(), newRecipe.getServing(), newRecipe.getTopIngredients());
            return fragment;
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
    }

}