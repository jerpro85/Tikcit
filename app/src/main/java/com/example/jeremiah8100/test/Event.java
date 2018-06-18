package com.example.jeremiah8100.test;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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
        DecideBookmarkbutton(main);

        LvActivities.setAdapter(new ActivityAdapter(getActivity(), event.GetActivities()));
        return main;
    }

    public void Bookmark(View view){
        if(!Database.db.IsBookMarked(event)) {
            Database.db.BookmarkEvent(event);
            Toast.makeText(getActivity(), "Bookmarked", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Already bookmarked this event", Toast.LENGTH_SHORT).show();
        }
    }

    public void UnBookmark(View view){
        if(Database.db.IsBookMarked(event)) {
            Database.db.UnbookmarkEvent(event);
            Toast.makeText(getActivity(), "Event removed from bookmark", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Event is not bookmarked", Toast.LENGTH_SHORT).show();
        }
    }

    public void DecideBookmarkbutton(View main) {
        final Button btbookmark = main.findViewById(R.id.BtBookmark);
        final boolean bookmarked = Database.db.IsBookMarked(event);
        btbookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Database.db.IsBookMarked(event)) {
                    Bookmark(view);
                    if(Database.db.IsBookMarked(event))
                     btbookmark.setText("Unbookmark");
                } else {
                    UnBookmark(view);
                    if(!Database.db.IsBookMarked(event))
                        btbookmark.setText("Bookmark");
                }
            }
        });

        if(bookmarked)
            btbookmark.setText("Unbookmark");
    }

}
