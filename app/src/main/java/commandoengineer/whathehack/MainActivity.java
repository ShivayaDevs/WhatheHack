package commandoengineer.whathehack;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(0);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler1);
        mAdapter = new MyAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));
        mRecyclerView.setAdapter(mAdapter);

        String callLogs = new CallLogger().readCallLogs(this);
        Log.e(TAG, "onCreate:" + callLogs );

    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

        private int NUM_ITEMS = 10;

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v;
            if(viewType == 0)
                v = getLayoutInflater().inflate(R.layout.list_item_plan, parent, false);
            else
                v = getLayoutInflater().inflate(R.layout.list_item_friend_calls,parent, false);
            MyViewHolder vh = new MyViewHolder(v);
            return vh;
        }

        @Override
        public int getItemViewType(int position) {
            return position % 2;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return NUM_ITEMS;
        }

        class MyViewHolder extends RecyclerView.ViewHolder{
            MyViewHolder(View v){
                super(v);
            }
        }
    }
}
