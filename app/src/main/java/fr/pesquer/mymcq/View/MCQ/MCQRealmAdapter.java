package fr.pesquer.mymcq.View.MCQ;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import fr.pesquer.mymcq.Entity.MCQ;
import fr.pesquer.mymcq.R;
import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

public class MCQRealmAdapter extends RealmBaseAdapter<MCQ> implements ListAdapter {


    public MCQRealmAdapter(Context context, OrderedRealmCollection<MCQ> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder viewHolder;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.row_mcq, parent, false);
            viewHolder = new MyViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.tvMCQName);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (MyViewHolder) convertView.getTag();
        }

        MCQ item = adapterData.get(position);
        viewHolder.name.setText(item.name);
        return convertView;
    }


    private static class MyViewHolder{
        TextView name;
    }
}
