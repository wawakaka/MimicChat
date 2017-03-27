package com.example.wawakaka.mimicchat;

import android.app.Activity;
import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import static android.text.method.LinkMovementMethod.getInstance;


/**
 * Created by Wawakaka on 3/25/2017.
 */

public class MessagesListAdapter extends BaseAdapter {
    private Context context;
    private List<Message> messagesItems;
    LinearLayout linkPreviews;

    public MessagesListAdapter(Context context, List<Message> messagesItems) {
        this.context = context;
        this.messagesItems = messagesItems;
    }

    @Override
    public int getCount() {
        return messagesItems.size();
    }

    @Override
    public Object getItem(int position) {
        return messagesItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Message m = messagesItems.get(position);
        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (messagesItems.get(position).is_from_me()) {
            convertView = mInflater.inflate(R.layout.message_out, null);

        } else {
            convertView = mInflater.inflate(R.layout.message_in, null);
        }
        TextView senderName = (TextView)convertView.findViewById(R.id.sender);
        TextView msg = (TextView) convertView.findViewById(R.id.message);
        TextView time = (TextView) convertView.findViewById(R.id.time);
        TextView webTitle = (TextView) convertView.findViewById(R.id.webTitle);
        TextView webDescription = (TextView) convertView.findViewById(R.id.webDesc);

        linkPreviews = (LinearLayout)convertView.findViewById(R.id.linkPreviews);

        senderName.setText(m.getSender());
        msg.setText(m.getMessage());
        time.setText(m.getTime());

        if(m.getWebTitle()==null){
            linkPreviews.setVisibility(View.GONE);
        }else{
            linkPreviews.setVisibility(View.VISIBLE);
        }

        webTitle.setText(m.getWebTitle());
        webDescription.setText(m.getWebDesc());
        return convertView;
    }


}
