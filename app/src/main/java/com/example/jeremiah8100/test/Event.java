package com.example.jeremiah8100.test;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.jeremiah8100.test.Adapters.ListAdapters.ActivityAdapter;
import com.example.jeremiah8100.test.Items.Sessions;


/**
 * A simple {@link Fragment} subclass.
 */
public class Event extends Fragment {
    public com.example.jeremiah8100.test.Items.Event event;

    public Event() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        event = Connection.GetEvents(Sessions.currentSession.account).get(0);
        ((Inapp)getActivity()).SetTitle(event.Name);
        View main = inflater.inflate(R.layout.fragment_event, container, false);
        ListView LvActivities = main.findViewById(R.id.LvActivities);
        LvActivities.setAdapter(new ActivityAdapter(getActivity(), event.GetActivities()));
        return main;
    }

}
