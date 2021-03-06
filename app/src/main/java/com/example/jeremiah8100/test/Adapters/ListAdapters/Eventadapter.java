package com.example.jeremiah8100.test.Adapters.ListAdapters;

import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jeremiah8100.test.Connection;
import com.example.jeremiah8100.test.Inapp;
import com.example.jeremiah8100.test.Items.Activity;
import com.example.jeremiah8100.test.Items.Event;
import com.example.jeremiah8100.test.Items.Sessions;
import com.example.jeremiah8100.test.R;

import java.util.List;

/**
 * Created by jeremiah8100 on 19-3-2018.
 */

public class Eventadapter extends BaseAdapter {

    List<Event> Events;
    Context Context;

    public Eventadapter(Context Context, List<Event> Events){
        this.Context = Context;
        this.Events = Events;
    }

    @Override
    public int getCount() {
        return Events.size();
    }

    @Override
    public Object getItem(int i) {
        return Events.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View control = (View)((LayoutInflater)Context.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE)).inflate( R.layout.eventitem, null);
        TextView tv = control.findViewById(R.id.TvName);
        ImageView iv = control.findViewById(R.id.IvImage);
//        Bitmap b = BitmapFactory.decodeResource(Context.getResources(), R.drawable.img1);
      //  RoundedBitmapDrawable image = RoundedBitmapDrawableFactory.create(Context.getResources(), b);
      //  image.setCornerRadius(image.getIntrinsicHeight());
        TextView TvDescription = control.findViewById(R.id.TvDescription);
        TvDescription.setText(Events.get(i).Description);
        control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Inapp)Context).nav.Show(new com.example.jeremiah8100.test.Event());
            }
        });
       // iv.setImageBitmap(b);
        tv.setText(Events.get(i).Name);


        return control;
    }
}
