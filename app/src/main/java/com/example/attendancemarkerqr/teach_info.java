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

public class teach_info extends AppCompatActivity {

    EditText tfullname,tmobile,tdept,tunid,tclg;
    Button but_save2;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference root = db.getReference().child("Staff");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teach_info);

        tfullname = findViewById(R.id.edit_t_fullname);
        tmobile = findViewById(R.id.edit_t_mobile);
        tdept = findViewById(R.id.edit_t_dept);
        tunid = findViewById(R.id.edit_t_unid);
        tclg = findViewById(R.id.edit_t_clg);
        but_save2 = findViewById(R.id.but_save2);

        but_save2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = tfullname.getText().toString();
                String mobile = tmobile.getText().toString();
                String dept = tdept.getText().toString();
                String unid = tunid.getText().toString();
                String clg = tclg.getText().toString();

                if(TextUtils.isEmpty(name))
                {
                    tfullname.setError("Fullname is Required.");
                    return;
                }
                else if(TextUtils.isEmpty(mobile))
                {
                    tmobile.setError("Mobile No is Required.");
                    return;
                }
                else if(TextUtils.isEmpty(dept))
                {
                    tdept.setError("Department is Required.");
                    return;
                }
                else if(TextUtils.isEmpty(unid))
                {
                    tunid.setError("PRN No is Required.");
                    return;
                }
                else if(TextUtils.isEmpty(clg))
                {
                    tclg.setError("College is Required.");
                    return;
                }
                if(mobile.length() < 10)
                {
                    tmobile.setError("Mobile No Must be 10 Characters");
                    return;
                }

                HashMap<String , String> userMap = new HashMap<>();
                userMap.put("name" , name);
                userMap.put("mobile" , mobile);
                userMap.put("dept" , dept);
                userMap.put("prn" , unid);
                userMap.put("college" , clg);

                root.push().setValue(userMap);
                Toast.makeText(teach_info.this,"Data Saved successfully",Toast.LENGTH_SHORT).show();
                go_to_login();

            }
        });
    }

    void go_to_login()
    {
        Intent intent8 = new Intent(this,teach_login_page.class);
        startActivity(intent8);
    }
}
