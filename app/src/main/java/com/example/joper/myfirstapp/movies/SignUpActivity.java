package com.example.joper.myfirstapp.movies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.joper.myfirstapp.movies.models.User;
import com.example.joper.myfirstapp.movies.utils.SessionStateManager;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText userName;
    private EditText userLastName;
    private EditText userCity;
    private EditText userAge;
    private Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userName = (EditText) findViewById(R.id.editText_name);
        userLastName = (EditText) findViewById(R.id.editText_lastName);
        userCity= (EditText) findViewById(R.id.editText_city);
        userAge = (EditText) findViewById(R.id.editText_edad);
        buttonSignUp = (Button) findViewById(R.id.button_signUp);

        buttonSignUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button_signUp){
            String name = userName.getText().toString();
            String lastName = userLastName.getText().toString();
            String city = userCity.getText().toString();
            int age = Integer.parseInt(userAge.getText().toString());

            User user = new User();
            user.setUserName(name);
            user.setUserLastName(lastName);
            user.setUserCity(city);
            user.setUserAge(age);

            SessionStateManager ssm = new SessionStateManager(this);
            ssm.saveSession(user);

            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
            startActivity(intent);



        }





    }
}
