package commandoengineer.whathehack;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        private int NUM_ITEMS = 5;

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v;
            v = getActivity().getLayoutInflater().inflate(R.layout.list_item_plan_final,parent, false);
            MyViewHolder vh = new MyViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.bindViewHolder(position);
        }

        @Override
        public int getItemCount() {
            return NUM_ITEMS;
        }

        class MyViewHolder extends RecyclerView.ViewHolder{
            TextView mPlanNameView, mButton, mSavings, mDescription;
            MyViewHolder(View v){
                super(v);
                mPlanNameView = (TextView) v.findViewById(R.id.plan_name_tv);
                mDescription = (TextView) v.findViewById(R.id.plan_desc_tv);
                mButton = (TextView) v.findViewById(R.id.button);
                mSavings = (TextView) v.findViewById(R.id.savings_tv);
            }

            public void bindViewHolder(int i){
                mPlanNameView.setText(DData.planName[i]);
                mDescription.setText(DData.planString[i]);
                mSavings.setText(DData.savings[i]);
                mButton.setText(getString(R.string.rupee_symbol) + " " + DData.costs[i] + "/-");
            }
        }

    }
}