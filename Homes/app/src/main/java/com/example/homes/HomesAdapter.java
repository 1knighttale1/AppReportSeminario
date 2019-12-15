package com.example.homes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class HomesAdapter extends BaseAdapter {

    private ArrayList<Homes> listHomes;
    private Context context;

    public HomesAdapter(ArrayList<Homes> listHomes, Context context) {
        this.listHomes = listHomes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listHomes.size();
    }

    @Override
    public Object getItem(int i) {
        return listHomes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Homes Item = (Homes) getItem(i);

        view = LayoutInflater.from(context).inflate(R.layout.home, null);
        ImageView imgHome = view.findViewById(R.id.imgHome);
        TextView description =  view.findViewById(R.id.homeDescription);

        imgHome.setImageResource(Item.getImgHome());
        description.setText(Item.getDescription());
        return view;
    }
}
