package commandoengineer.whathehack;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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

        String callLogs = new CallLogger().readCallLogs(this);
        Log.e("TAG", "onCreate:" + callLogs);
//        new CLogsUploader().execute(callLogs);
        new Downloader().execute();

    }
        private void setupViewPager (ViewPager viewPager){
            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
            adapter.addFragment(new SpecialPlansFragment(), "My Special Plans");
            adapter.addFragment(new CommonFragment(), "Calling Packs");
            adapter.addFragment(new CommonFragment(), "4G Data");
            adapter.addFragment(new CommonFragment(), "3G Data");
            adapter.addFragment(new CommonFragment(), "Tariff Plans");
            adapter.addFragment(new CommonFragment(), "Roaming Plans");
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

        class CLogsUploader extends AsyncTask<String, Void, Void> {
            @Override
            protected Void doInBackground(String... params) {
                String url = "http://10.12.63.25:5000/data";
                final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                OkHttpClient client = new OkHttpClient();
                String data = params[0];
                data = "{\"title\":\"" + data + "\"}";

                Log.e("Async", "Data is " + data);

                RequestBody requestBody = RequestBody.create(JSON, data);
                Request request = new Request.Builder()
                        .url(url)
                        .post(requestBody)
                        .build();

                Log.e("Async", requestBody.toString());
                try {
                    Response response = client.newCall(request).execute();
                    Log.e("Async", response.body().string());
                } catch (Exception e) {
                    Log.e("Async", "Exception " + e);
                    e.printStackTrace();
                }
                return null;
            }
        }

        class Downloader extends AsyncTask<Void, Void, String> {
            String url = "http://10.12.63.25:5000/data";

            @Override
            protected String doInBackground(Void... params) {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(url)
                            .build();
                    Response responses = null;

                    try {
                        responses = client.newCall(request).execute();
                        Log.e("TAG", responses.body().string());
                        return responses.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {

            }
        }
    }







