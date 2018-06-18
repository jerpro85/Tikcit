package com.example.jeremiah8100.test.Adapters.ListAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jeremiah8100.test.Items.Activity;
import com.example.jeremiah8100.test.Items.Event;
import com.example.jeremiah8100.test.R;

import java.util.List;

/**
 * Created by jeremiah8100 on 19-3-2018.
 */

public class ActivityAdapter extends BaseAdapter {

    List<Activity> Activities;
    Context Context;

    public ActivityAdapter(Context Context, List<Activity> Events){
        this.Context = Context;
        this.Activities = Events;
    }

    @Override
    public int getCount() {
        return Activities.size();
    }

    @Override
    public Object getItem(int i) {
        return Activities.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View control = (View)((LayoutInflater)Context.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE)).inflate( R.layout.activityitem, null);
        TextView tv = control.findViewById(R.id.TvName);
        ImageView iv = control.findViewById(R.id.IvImage);
//        Bitmap b = BitmapFactory.decodeResource(Context.getResources(), R.drawable.img1);
      //  RoundedBitmapDrawable image = RoundedBitmapDrawableFactory.create(Context.getResources(), b);
      //  image.setCornerRadius(image.getIntrinsicHeight());
        TextView TvDescription = control.findViewById(R.id.TvDescription);
        TvDescription.setText(Activities.get(i).Description);

       // iv.setImageBitmap(b);
        tv.setText(Activities.get(i).Name);


        return control;
    }
}
