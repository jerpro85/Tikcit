package com.example.jeremiah8100.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Inapp extends AppCompatActivity {

    public Navigator nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inapp);
        nav = new Navigator(this, findViewById(R.id.fragment));
        nav.Show(new Events());


    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if(!SidebarActive()) {
            if (!nav.ShowPrevious())
                super.onBackPressed();
        } else {
            HideSidebar();
        }
    }

    public void Menu(View w){
        if(SidebarActive())
            HideSidebar();
        else
            ShowSidebar();
    }

    public void ShowSidebar() {
        LinearLayout LLSidebar = findViewById(R.id.LLSidebar);
        LLSidebar.setVisibility(View.VISIBLE);
    }

    public boolean SidebarActive(){
        LinearLayout LLSidebar = findViewById(R.id.LLSidebar);
        return LLSidebar.getVisibility() == View.VISIBLE;
    }

    public void SettingsClick(View view){
        System.out.println("ok");
        HideSidebar();
        nav.Show(new Settings());
    }

    public void SetTitle(String title){
        TextView TvMenuTitle = findViewById(R.id.TvMenuTitle);
        TvMenuTitle.setText(title);
    }

    public void CloseMenu(View view){
        HideSidebar();
    }

    public void HideSidebar(){
        LinearLayout LLSidebar = findViewById(R.id.LLSidebar);
        LLSidebar.setVisibility(View.GONE);
    }
}