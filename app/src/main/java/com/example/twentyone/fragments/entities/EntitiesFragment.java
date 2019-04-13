package com.example.twentyone.fragments.entities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.twentyone.R;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class EntitiesFragment extends Fragment {

    public static final String TAG = EntitiesFragment.class.getSimpleName();

    private static final EntitiesFragment ourInstance = new EntitiesFragment();

    public static EntitiesFragment getInstance() {
        return ourInstance;
    }

    public EntitiesFragment() {
    }

    private TabLayout tabLayout;
    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_entities, container, false);

        mPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            private final Fragment[] mFragments = new Fragment[] {
                    EntitiesPointsFragment.getInstance(),
                    new EntitiesBloodPressureFragment(),
                    new EntitiesWeightFragment(),
                    new EntitiesPreferencesFragment()
            };
            private final String[] mFragmentNames = new String[] {
                    getString(R.string.entities_tab_points),
                    getString(R.string.entities_tab_blood),
                    getString(R.string.entities_tab_weight),
                    getString(R.string.entities_tab_preferences)
            };
            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }
            @Override
            public int getCount() {
                return mFragments.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mFragmentNames[position];
            }
        };

        // Set up the ViewPager with the sections adapter.
        mViewPager = view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(4);

        tabLayout = view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        return view;
    }
}
