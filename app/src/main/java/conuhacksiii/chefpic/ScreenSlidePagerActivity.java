package conuhacksiii.chefpic;

/**
 * Created by laura on 2018-01-27.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

public class ScreenSlidePagerActivity extends FragmentActivity {

    private static final int NUM_PAGES = 10; //number of recipes to display
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    protected void onCreate(Bundle savedInstaceState){

        super.onCreate(savedInstaceState);
        setContentView(R.layout.recipe_display_layout);

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
            return new ScreenSlidePageFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

}