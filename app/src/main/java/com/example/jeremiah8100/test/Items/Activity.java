package com.example.jeremiah8100.test.Items;

import java.util.Date;

public class Activity {
    public String Id;
    public String Name;
    public String Description;
    public Date Startdate;
    public Date Enddate;

    public Activity(String Id, String Name, String Description, Date Startdate, Date Enddate){
        this.Id = Id;
        this.Name = Name;
        this.Description = Description;
        this.Startdate = Startdate;
        this.Enddate = Enddate;
    }
}
