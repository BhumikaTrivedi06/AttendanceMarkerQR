package com.example.attendancemarkerqr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class teach_login_page extends AppCompatActivity {

    EditText tloginName , tloginPass;
    DatabaseReference dbref ;
    Button button_to_teachReg , but_to_teach_board;
    String name, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teach_login_page);

        tloginName = findViewById(R.id.t_Username);
        tloginPass = findViewById(R.id.t_password);

        dbref = FirebaseDatabase.getInstance().getReference("Details");

        button_to_teachReg = (Button)findViewById(R.id.T_reg_but) ;

        but_to_teach_board = (Button)findViewById(R.id.t_login_but) ;
        but_to_teach_board.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = tloginName.getText().toString().toLowerCase();
                pass = tloginPass.getText().toString();
                dbref.child("Staff").child(name).addListenerForSingleValueEvent(listener);
                //go_to_teach_board();
            }
            ValueEventListener listener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        String passwrd = snapshot.child("pass").getValue(String.class);

                        if(passwrd.equals(pass))
                        {
                            Intent intent11 = new Intent (teach_login_page.this, teach_board.class);
                            startActivity(intent11);
                        }
                        else
                        {
                            Toast.makeText(teach_login_page.this,"Invalid Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(teach_login_page.this,"Invalid Username or Password", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            };

        });





    button_to_teachReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_to_register();
            }
        });
    }

    void go_to_register()
    {
        Intent intent12 = new Intent(this,teach_reg.class);
        startActivity(intent12);
    }

}
