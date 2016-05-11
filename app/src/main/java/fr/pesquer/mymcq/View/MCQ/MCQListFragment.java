package fr.pesquer.mymcq.View.MCQ;

import android.app.ListFragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import cz.msebera.android.httpclient.Header;
import fr.pesquer.mymcq.Data.MCQRealm;
import fr.pesquer.mymcq.Entity.MCQ;
import fr.pesquer.mymcq.MainActivity;
import fr.pesquer.mymcq.R;
import fr.pesquer.mymcq.Webservice.MCQWSAdapter;

public class MCQListFragment extends ListFragment {


    public MCQListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_mcqlist, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.prefs_file_key),getActivity().MODE_PRIVATE);
        String userId = sharedPref.getString(getString(R.string.user_id_save),null);
        MCQWSAdapter.getAllMCQ(Integer.parseInt(userId), new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onFinish() {
                MCQRealm request = new MCQRealm();
                MCQRealmAdapter adapter = new MCQRealmAdapter(getActivity(), request.getAll(), false);
                setListAdapter(adapter);
            }
        });
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        MCQ mcq = (MCQ) l.getItemAtPosition(position);
        MCQFragment fragment = new MCQFragment();
        fragment.currentMCQ = mcq;

        ((MainActivity)getActivity()).pushFragment(fragment);
    }
}