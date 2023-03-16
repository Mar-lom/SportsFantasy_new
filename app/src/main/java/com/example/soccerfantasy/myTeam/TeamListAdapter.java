//package com.example.soccerfantasy.myTeam;
//
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//import com.example.soccerfantasy.R;
//import com.google.api.Context;
//
//import org.checkerframework.checker.units.qual.A;
//
//import java.util.ArrayList;
//
//public class TeamListAdapter extends BaseAdapter {
//
//    Context context;
//
//    LayoutInflater layoutInflater;
//
//    ArrayList<Team> teams = new ArrayList<Team>();
//
//    public TeamListAdapter (Context appContext, ArrayList<Team> teamList){
//
//        this.context = appContext;
//        this.teams = teamList;
//
//    }
//
//    @Override
//    public int getCount() {
//        return teams.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return getItemId(i);
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return i;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//
//        Team team = getItem(i);
//
//
//
//        return null;
//    }
//}