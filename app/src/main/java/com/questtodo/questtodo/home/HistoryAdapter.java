package com.questtodo.questtodo.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.questtodo.questtodo.R;
import com.questtodo.questtodo.model.Quest;
import com.questtodo.questtodo.utils.ResourceId;
import com.questtodo.questtodo.utils.TimeUtils;

import java.util.ArrayList;


/**
 * Created by eunjooim on 15. 8. 29..
 */
public class HistoryAdapter extends ArrayAdapter<Quest> {

    private ArrayList<Quest> items;
    private Context context;

    public HistoryAdapter(Context context, int resource, ArrayList<Quest> items) {
        super(context, resource, items);
        this.items = items;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.layout_history_list_item, null);
        }

        Quest history = items.get(position);

        if (history != null) {
            ImageView ivHistoryType = (ImageView) view.findViewById(R.id.iv_history_type);
            TextView tvHistoryTime = (TextView) view.findViewById(R.id.tv_history_time);
            TextView tvHistoryTitle = (TextView) view.findViewById(R.id.tv_history_title);

            ResourceId resourceId = new ResourceId();
            int typeResource = resourceId.getTypeResource(history.getType());
            ivHistoryType.setImageResource(typeResource);

            TimeUtils timeUtils = new TimeUtils();
            String time = timeUtils.getStringTime(history.getTime());
            tvHistoryTime.setText(time);
            tvHistoryTitle.setText(history.getTitle());
        }

        return view;
    }

}
