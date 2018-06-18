package com.example.jeremiah8100.test;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.jeremiah8100.test.Adapters.ListAdapters.Eventadapter;
import com.example.jeremiah8100.test.Items.Account;
import com.example.jeremiah8100.test.Items.Event;
import com.example.jeremiah8100.test.Items.Sessions;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Events extends Fragment {


    public Events() {
        // Required empty public constructor
    }
    List<Event> events;
    Eventadapter eventadapter;
    boolean custom = false;
    String Title = "Events";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        try {
            ((Inapp) getActivity()).SetTitle(Title);
        } catch (Exception e){

        }

        if(!custom)
            events = Connection.GetEvents(Sessions.currentSession.account);
        View main = inflater.inflate(R.layout.fragment_events, container, false);


        ListView lv = main.findViewById(R.id.LvEventItems);
        eventadapter = new Eventadapter(getActivity(),events);
        lv.setAdapter(eventadapter);
        return main;
    }

    public static Events Normal(){
        Events ev = new Events();
        ev.custom = true;
        ev.events = Connection.GetEvents(Sessions.currentSession.account);
        return ev;
    }

    public static Events Bookmarked(){
        Events ev = new Events();

       ev.events = new ArrayList<>();
        ev.custom = true;
        for(Event e : Connection.GetEvents(Sessions.currentSession.account) ){
            if(Database.db.IsBookMarked(e)){
                ev.events.add(e);
            }
            ev.Title = "Bookmarked Events";
        }

        return ev;
    }

}
