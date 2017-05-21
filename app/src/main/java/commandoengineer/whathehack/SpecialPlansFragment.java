package commandoengineer.whathehack;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yash on 21/5/17.
 */

public class SpecialPlansFragment extends Fragment {

    private static final String TAG = "MainActivity";

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;

    public SpecialPlansFragment() {
            // Required empty public constructor
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View rootView = inflater.inflate(R.layout.fragment_special_plan, container, false);

            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler1);
            mAdapter = new MyAdapter();

            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), layoutManager.getOrientation()));
            mRecyclerView.setAdapter(mAdapter);

            return rootView;
        }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

        private int NUM_ITEMS = 10;

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v;
            v = getActivity().getLayoutInflater().inflate(R.layout.list_item_plan_final,parent, false);
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