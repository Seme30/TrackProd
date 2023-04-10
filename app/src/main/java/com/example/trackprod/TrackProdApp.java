package com.example.trackprod;

import android.app.Application;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.realm.Realm;


public class TrackProdApp extends Application{

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;


    @Override
    public void onCreate() {
        super.onCreate();
        Prefs.INSTANCE.initPrefs(getApplicationContext());
        Realm.init(getApplicationContext());



    }

}
