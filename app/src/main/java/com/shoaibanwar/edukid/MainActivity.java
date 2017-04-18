package com.shoaibanwar.edukid;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shoaibanwar.edukid.model.Lesson;
import com.shoaibanwar.edukid.model.LessonDataStore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter adapter_ViewPager;
    private List<Lesson> lessonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.main_viewPager);
        tabLayout = (TabLayout) findViewById(R.id.main_TabLayout);

        tabLayout.setupWithViewPager(viewPager);

        adapter_ViewPager = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter_ViewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        lessonList = new LessonDataStore(MainActivity.this).getLessonList();
    }


    private class PagerAdapter extends FragmentPagerAdapter{

        private final int COUNT_TOTAL_FRAGMENTS = 2;
        private String[] tabTitles = new String[]{"Subject", "Scores"};

        public PagerAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }

        // overriding getPageTitle()
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    //Return Subject Fragment
                    return SubjectFragment.newInstance((ArrayList<Lesson>) MainActivity.this.lessonList);
                case 1:
                    //Return Scores Fragment
                    return ScoresFragment.newInstance("Will see what to pass","Will see what to pass");
            }
            return null;
        }

        @Override
        public int getCount() {
            return COUNT_TOTAL_FRAGMENTS;
        }
    }
}
