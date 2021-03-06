package com.example.jeremiah8100.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jeremiah8100.test.Items.Account;
import com.example.jeremiah8100.test.Items.Sessions;

public class Main extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    EditText EtUsername;
    EditText EtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Database.db.Init("data.db", this);
        EtUsername = findViewById(R.id.EtUsername);
        EtPassword = findViewById(R.id.EtPassword);
        // Example of a call to a native method

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */

    public void Login(View v){
        System.out.println("okay");
        Account.Result result = Connection.Login(EtUsername.getText().toString(), EtPassword.getText().toString());
        System.out.println(result.message);
        if(result.authenticated) {
            Sessions.currentSession.account = result.account;
            startActivity(new Intent(Main.this, Inapp.class));
        }


    }


}
