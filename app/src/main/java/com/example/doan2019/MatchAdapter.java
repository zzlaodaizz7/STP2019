package com.example.doan2019;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class MatchAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Match> matchList;

    public MatchAdapter(Context context, int layout, List<Match> matchList) {
        this.context = context;
        this.layout = layout;
        this.matchList = matchList;
    }

    @Override
    public int getCount() {
        return matchList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView txtTeamHost;
        TextView txtState;
        TextView txtTime;
        TextView txtPitch;
        TextView txtLevel;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout, null);
            viewHolder = new ViewHolder();
            viewHolder.txtTeamHost = (TextView) convertView.findViewById(R.id.txtTeamHost);
            viewHolder.txtState = (TextView) convertView.findViewById(R.id.txtState);
            viewHolder.txtTime = (TextView) convertView.findViewById(R.id.txtTime);
            viewHolder.txtPitch = (TextView) convertView.findViewById(R.id.txtPitch);
            viewHolder.txtLevel = (TextView) convertView.findViewById(R.id.txtLevel);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Match match = matchList.get(position);
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy hh:mm");
        String time = dateFormat.format(match.getTime());
        viewHolder.txtTeamHost.setText(match.getTeamHost());
        viewHolder.txtState.setText(match.getState());
        viewHolder.txtTime.setText(time);
        viewHolder.txtPitch.setText(match.getPitch());
        viewHolder.txtLevel.setText(match.getLevel());
        return convertView;
    }
}
