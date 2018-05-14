package com.example.jeremiah8100.test;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.jeremiah8100.test.Adapters.ListAdapters.Eventadapter;
import com.example.jeremiah8100.test.Items.Event;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Events extends Fragment {


    public Events() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View main = inflater.inflate(R.layout.fragment_events, container, false);
        List<Event> events = new ArrayList<>();
        events.add(new Event("Databinding", "Binding van data met controls"));
        for(int a = 0;a < 20;a++)
        events.add(new Event("Hackaton", "Een wedstrijd waarbij de groep met de beste applicatie wint"));

        ListView lv = main.findViewById(R.id.LvEventItems);
        lv.setAdapter(new Eventadapter(getContext(),events));
        return main;
    }

}
