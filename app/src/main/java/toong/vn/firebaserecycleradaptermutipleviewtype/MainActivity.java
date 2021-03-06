package toong.vn.firebaserecycleradaptermutipleviewtype;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import toong.vn.firebaserecycleradaptermutipleviewtype.fragment.ContainerFragment;
import toong.vn.firebaserecycleradaptermutipleviewtype.screen.translucent.TranslucentActivity;

public class MainActivity extends AppCompatActivity {
    ViewPager mPager;

    PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = findViewById(R.id.pager);
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());

        mPagerAdapter.addFragment(ContainerFragment.newInstance(1));
        mPagerAdapter.addFragment(ContainerFragment.newInstance(2));
        mPagerAdapter.addFragment(ContainerFragment.newInstance(3));
        mPagerAdapter.addFragment(ContainerFragment.newInstance(4));
        mPagerAdapter.addFragment(ContainerFragment.newInstance(5));

        mPager.setOffscreenPageLimit(mPagerAdapter.getCount());
        mPager.setAdapter(mPagerAdapter);

        findViewById(R.id.text_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TranslucentActivity.class));
            }
        });
    }

    private class PagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }

        public Fragment getCurrentFragment(int currentPos) {
            return mFragmentList.get(currentPos);
        }
    }

    @Override
    public void onBackPressed() {
        if (mPagerAdapter == null) {
            return;
        }
        if (mPagerAdapter instanceof PagerAdapter) {
            Fragment currentFragment =
                    ((PagerAdapter) mPagerAdapter).getCurrentFragment(mPager.getCurrentItem());
            if (currentFragment instanceof ContainerFragment) {
                // Main Fragment is hide, handle back press at child fragment on ViewPager
                ContainerFragment container = (ContainerFragment) currentFragment;
                if (container.onBackPressed()) {
                    return;
                }
            }
        }
        super.onBackPressed();
    }
}
