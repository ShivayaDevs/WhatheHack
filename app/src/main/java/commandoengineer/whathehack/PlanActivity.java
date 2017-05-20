package commandoengineer.whathehack;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class PlanActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.pager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

//        new UserNotifier().sendSwitchNotification(this, "Something just for you", "Now, make calls using Airtel at just 30p/sec and save upto 40%!");


//        String callLogs = new CallLogger().readCallLogs(this);
//        Log.e("TAG", "onCreate:" + callLogs );
//        new CallLogger().uploadToServer("http://10.12.63.25:5000/data", callLogs, new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.e("TAG", "Failed" + e);
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if(response.isSuccessful()){
//                    Log.e("TAG", "Submitted" + response.body().toString());
//                }
//            }
//        });
    
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SpecialPlansFragment(), "My Special Plans");
        adapter.addFragment(new SpecialPlansFragment(), "Calling Packs");
        adapter.addFragment(new SpecialPlansFragment(), "4G Data");
        adapter.addFragment(new SpecialPlansFragment(), "3G Data");
        adapter.addFragment(new SpecialPlansFragment(), "Tariff Plans");
        adapter.addFragment(new SpecialPlansFragment(), "Roaming Plans");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}






