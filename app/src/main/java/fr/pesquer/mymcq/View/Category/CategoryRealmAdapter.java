package fr.pesquer.mymcq.View.Category;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;
import fr.pesquer.mymcq.Entity.Category;
import fr.pesquer.mymcq.R;
import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

public class CategoryRealmAdapter extends RealmBaseAdapter<Category> implements ListAdapter {

    public CategoryRealmAdapter(Context context, OrderedRealmCollection<Category> data) {
        super(context,data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder viewHolder;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.row_category, parent, false);
            viewHolder = new MyViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.tvCategoryName);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (MyViewHolder) convertView.getTag();
        }

        Category item = adapterData.get(position);
        viewHolder.name.setText(item.name);
        return convertView;
    }


    private static class MyViewHolder{
        TextView name;
    }

}
