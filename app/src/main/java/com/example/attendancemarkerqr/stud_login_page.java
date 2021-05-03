package com.example.attendancemarkerqr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class stud_login_page extends AppCompatActivity {

    EditText sloginName , sloginPass;
    DatabaseReference dbref ;
    Button button_to_studReg, button_to_dashboard;
    public static  String name, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_login_page);

        sloginName = findViewById(R.id.S_Username);
        sloginPass = findViewById(R.id.S_password);

        dbref = FirebaseDatabase.getInstance().getReference("Details");



        button_to_dashboard = (Button)findViewById(R.id.S_login_but);
        button_to_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = sloginName.getText().toString().toLowerCase();
                pass = sloginPass.getText().toString();
                dbref.child("Student").child(name).addListenerForSingleValueEvent(listener);
                //go_to_stud_dashboard();
            }

            ValueEventListener listener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        String passwrd = snapshot.child("pass").getValue(String.class);

                        if(passwrd.equals(pass))
                        {
                            Intent intent5 = new Intent (stud_login_page.this, stud_board.class);
                            startActivity(intent5);
                        }
                        else
                        {
                            Toast.makeText(stud_login_page.this,"Invalid Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(stud_login_page.this,"Invalid Username or Password", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            };

        });


        button_to_studReg = (Button)findViewById(R.id.S_reg_but);
        button_to_studReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_to_register();
            }
        });

    }

    void go_to_register()
    {
        Intent intent3 = new Intent(this,stud_reg.class);
        startActivity(intent3);
    }
}
