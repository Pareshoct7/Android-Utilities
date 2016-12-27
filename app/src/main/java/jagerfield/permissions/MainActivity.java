package jagerfield.permissions;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import jagerfield.library.AppUtilities;
import jagerfield.library.PermissionsUtil.PermissionsUtil;
import jagerfield.library.PermissionsUtil.Results.ICheckPermissionResult;
import jagerfield.permissions.Fragments.MemoryInfoFragment;
import jagerfield.permissions.Fragments.PermissionsFragment;
import jagerfield.permissions.UserInterfaceManager.UserInterfaceManager;
import jagerfield.permissions.Utilities.C;
import jagerfield.utilities.R;

public class MainActivity extends AppCompatActivity
{
    private CoordinatorLayout activity_main;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        launchViewPager();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
    {
        PermissionsUtil permissionsUtil = AppUtilities.getPermissionUtil(this);

        if (requestCode == permissionsUtil.getPermissionsReqFlag())
        {
            ICheckPermissionResult result = null;
            result = permissionsUtil.checkPermissionsResults(UserInterfaceManager.PERMISSIONS_ARRAY);
            if (result == null)
            {
                return;
            }

            /**
             * Send th eresult to the fragment
             */
            EventBus.getDefault().post(result);

            String str = "";
        }
    }

    private void launchViewPager()
    {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addTab(C.PERMISSIONS_TAB, new PermissionsFragment());
        viewPagerAdapter.addTab(C.MEMORY_INFO_TAB, new MemoryInfoFragment());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter
    {
        private final ArrayList<FragModel> fragmentList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position).fragment;
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentList.get(position).title;
        }

        public void addTab(String title, Fragment fragment)
        {
            fragmentList.add(new FragModel(title, fragment));
        }

        class FragModel
        {
            Fragment fragment;
            String title;

            public FragModel(String title, Fragment fragment) {
                this.fragment = fragment;
                this.title = title;
            }
        }
    }
}




















