package com.example.attendancemarkerqr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Stud_info extends AppCompatActivity {

    EditText sfullname,smobile,sdept,sprn,syear,sclg;
    Button but_save;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference root = db.getReference().child("Student");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_info);

        sfullname = findViewById(R.id.edit_s_fullname);
        smobile = findViewById(R.id.edit_s_mobile);
        sdept = findViewById(R.id.edit_s_dept);
        sprn = findViewById(R.id.edit_s_prn);
        syear = findViewById(R.id.edit_s_year);
        sclg = findViewById(R.id.edit_s_clg);
        but_save = findViewById(R.id.but_saveQR);

        but_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = sfullname.getText().toString();
                String mobile = smobile.getText().toString();
                String dept = sdept.getText().toString();
                String prn = sprn.getText().toString();
                String year = syear.getText().toString();
                String clg = sclg.getText().toString();

                if(TextUtils.isEmpty(name))
                {
                    sfullname.setError("Fullname is Required.");
                    return;
                }
                else if(TextUtils.isEmpty(mobile))
                {
                    smobile.setError("Mobile No is Required.");
                    return;
                }
                else if(TextUtils.isEmpty(dept))
                {
                    sdept.setError("Department is Required.");
                    return;
                }
                else if(TextUtils.isEmpty(prn))
                {
                    sprn.setError("PRN No is Required.");
                    return;
                }
                else if(TextUtils.isEmpty(year))
                {
                    syear.setError("Year is Required.");
                    return;
                }
                else if(TextUtils.isEmpty(clg))
                {
                    sclg.setError("College is Required.");
                    return;
                }
                if(mobile.length() < 10)
                {
                    smobile.setError("Mobile No Must be 10 Characters");
                    return;
                }

                HashMap<String , String> userMap = new HashMap<>();
                userMap.put("name" , name);
                userMap.put("mobile" , mobile);
                userMap.put("dept" , dept);
                userMap.put("prn" , prn);
                userMap.put("year" , year);
                userMap.put("college" , clg);

                root.push().setValue(userMap);
                Toast.makeText(Stud_info.this,"Data Saved successfully",Toast.LENGTH_SHORT).show();
                go_to_login();

            }
        });
    }

    void go_to_login()
    {
        Intent intent6 = new Intent(this,stud_login_page.class);
        startActivity(intent6);
    }
}
