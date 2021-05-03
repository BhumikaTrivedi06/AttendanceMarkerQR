package com.example.attendancemarkerqr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChoicePage extends AppCompatActivity {

    private Button stud_button,teach_but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_page);

        stud_button = (Button)findViewById(R.id.student_but);
        stud_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStudLogin();
            }
        });

        teach_but = (Button)findViewById(R.id.teacher_but);
        teach_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTeachLogin();
            }
        });
    }

    public void openStudLogin()
    {
        Intent intent1 = new Intent(this,stud_login_page.class);
        startActivity(intent1);
    }

    public void openTeachLogin()
    {
        Intent intent2 = new Intent(this,teach_login_page.class);
        startActivity(intent2);
    }
}
