package com.example.jeremiah8100.test.Items;

import com.example.jeremiah8100.test.Connection;

import java.net.IDN;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jeremiah8100 on 19-3-2018.
 */

public class Event {
    public String Name;
    public String Description;
    public String Id;
    public Date Startdate;
    public Date Enddate;

    public Event(String Id, String Name, String Description, Date Startdate, Date Enddate){
        this.Name = Name;
        this.Description = Description;
        this.Id= Id;
        this.Startdate = Startdate;
        this.Enddate = Enddate;
    }

    public List<Activity> GetActivities(){
        return Connection.GetActivities(Id, Sessions.currentSession.account);
    }
}
