package com.example.jeremiah8100.test.Items;

/**
 * Created by jeremiah8100 on 14-5-2018.
 */

public class Account {
    String token;
    String email;
    public Account(String token, String email ){
        this.token = token;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public String getEmail(){
        return email;
    }

}
