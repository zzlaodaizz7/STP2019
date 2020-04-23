package com.example.doan2019;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.doan2019.DTO.DangTinDTO;

import java.util.List;

public class DangTinAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<DangTinDTO> dangTinDTOS;

    public DangTinAdapter(Context context, int layout, List<DangTinDTO> dangTinDTOS) {
        this.context = context;
        this.layout = layout;
        this.dangTinDTOS = dangTinDTOS;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public List<DangTinDTO> getDangTinDTOS() {
        return dangTinDTOS;
    }

    public void setDangTinDTOS(List<DangTinDTO> dangTinDTOS) {
        this.dangTinDTOS = dangTinDTOS;
    }

    @Override
    public int getCount() {
        return dangTinDTOS.size();
    }

    @Override
    public Object getItem(int position) {
        return dangTinDTOS.get(position);
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
        DangTinAdapter.ViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout, null);
            viewHolder = new DangTinAdapter.ViewHolder();
            viewHolder.txtTeamHost = (TextView) convertView.findViewById(R.id.txtTeamHost);
            viewHolder.txtState = (TextView) convertView.findViewById(R.id.txtState);
            viewHolder.txtTime = (TextView) convertView.findViewById(R.id.txtTime);
            viewHolder.txtPitch = (TextView) convertView.findViewById(R.id.txtPitch);
            viewHolder.txtLevel = (TextView) convertView.findViewById(R.id.txtLevel);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (DangTinAdapter.ViewHolder) convertView.getTag();
        }

        DangTinDTO dangtin = dangTinDTOS.get(position);
        viewHolder.txtTeamHost.setText(dangtin.getDoidangtin_ten());
        viewHolder.txtState.setText(dangtin.getTrangthai());

        viewHolder.txtTime.setText(dangtin.getNgay());
        viewHolder.txtPitch.setText(""+dangtin.getSan_ten());
        viewHolder.txtLevel.setText(""+dangtin.getTrinhdo());
        return convertView;
    }
}
