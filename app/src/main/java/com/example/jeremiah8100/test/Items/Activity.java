package com.example.jeremiah8100.test.Items;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Activity {
    public String Id;
    public String Name;
    public String Description;
    public Date Startdate;
    public Date Enddate;

    public Activity(String Id, String Name, String Description){
        this.Id = Id;
        this.Name = Name;
        this.Description = Description;
    }
}
