package fr.pesquer.mymcq.View.MCQ;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import fr.pesquer.mymcq.Data.MCQRealm;
import fr.pesquer.mymcq.Entity.Category;
import fr.pesquer.mymcq.Entity.MCQ;
import fr.pesquer.mymcq.R;
import fr.pesquer.mymcq.View.MainActivity;

public class MCQListFragment extends ListFragment {

    public Category category;
    private SwipeRefreshLayout swipeContainer;
    private MCQRealmAdapter adapter;

    public MCQListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mcqlist, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        swipeContainer = (SwipeRefreshLayout) getView().findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                /*SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.prefs_file_key), Context.MODE_PRIVATE);
                String token = sharedPref.getString(getString(R.string.user_token), null);
                MCQWSAdapter.getAllMCQ(token, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        swipeContainer.setRefreshing(false);
                    }

                    @Override
                    public void onResponse(Call call, Response response) {
                        if (response.isSuccessful() && response.code() == 200) {
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().toString());

                                JSONArray jsonMCQ = jsonObject.getJSONArray("MCQ");

                                MCQRealm mcqRealm = new MCQRealm();
                                mcqRealm.deleteAll();
                                mcqRealm.addFromJson(jsonMCQ);
                                swipeContainer.setRefreshing(false);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else{
                            swipeContainer.setRefreshing(false);
                        }
                    }
                });*/
                ((MainActivity)getActivity()).loadDateFromWS();
                swipeContainer.setRefreshing(false);
                MCQRealm request = new MCQRealm();
                if (category == null){
                    adapter = new MCQRealmAdapter(getActivity(), request.getAll());
                }else{
                    adapter = new MCQRealmAdapter(getActivity(), request.getAllWithCategory(category));
                }
                setListAdapter(adapter);
            }
        });


        MCQRealm request = new MCQRealm();
        if (category == null){
            adapter = new MCQRealmAdapter(getActivity(), request.getAll());
        }else{
            adapter = new MCQRealmAdapter(getActivity(), request.getAllWithCategory(category));
        }
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        MCQ mcq = (MCQ) l.getItemAtPosition(position);
        MCQFragment fragment = new MCQFragment();
        fragment.currentMCQ = mcq;

        ((MainActivity)getActivity()).pushFragment(fragment);
    }
}