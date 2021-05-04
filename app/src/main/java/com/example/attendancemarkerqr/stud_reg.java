 package com.example.attendancemarkerqr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class stud_reg extends AppCompatActivity {

    public EditText sEmail, sPassword, sfullname, smobile, sdept, sprn, syear, sclg , susername;
    public Button sSubmitbtn;

    DatabaseReference dref;

    //public FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_reg);

        sEmail = findViewById(R.id.edit_email1);
        sPassword = findViewById(R.id.edit_pswd1);
        sSubmitbtn = findViewById(R.id.but_submt1);
        sfullname = findViewById(R.id.edit_s_fullname);
        smobile = findViewById(R.id.edit_s_mobile);
        sdept = findViewById(R.id.edit_s_dept);
        sprn = findViewById(R.id.edit_s_prn);
        syear = findViewById(R.id.edit_s_year);
        sclg = findViewById(R.id.edit_s_clg);
        susername = findViewById(R.id.edit_s_username);

        dref = FirebaseDatabase.getInstance().getReference("Details");

        sSubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = sfullname.getText().toString();
                String username = susername.getText().toString().toLowerCase();
                String mobile = smobile.getText().toString();
                String dept = sdept.getText().toString();
                String prn = sprn.getText().toString();
                String year = syear.getText().toString();
                String clg = sclg.getText().toString();
                String email = sEmail.getText().toString().trim();
                String password = sPassword.getText().toString().trim();

                if(TextUtils.isEmpty(name))   //TextUtils.isEmpty(String_name)
                {
                    sfullname.setError("Fullname is Required.");
                    return;
                }
                if(TextUtils.isEmpty(username))
                {
                    susername.setError("Username is Required.");
                    return;
                }
                if(TextUtils.isEmpty(email))
                {
                    sEmail.setError("Email is Required.");
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
                    sprn.setError("PRN is Required.");
                    return;
                }
                else if(TextUtils.isEmpty(clg))
                {
                    sclg.setError("College is Required.");
                    return;
                }
                else if(TextUtils.isEmpty(year))
                {
                    syear.setError("Year is Required.");
                    return;
                }
                else if(TextUtils.isEmpty(password))
                {
                    sPassword.setError("College is Required.");
                    return;
                }
                if(mobile.length() < 10)
                {
                    smobile.setError("Mobile No Must be 10 Characters");
                    return;
                }
                if(year.length() > 1)
                {
                    syear.setError("Enter Valid year");
                    return;
                }
                if(password.length() < 6)
                {
                    sPassword.setError("Password Must be >= 6 Characters");
                    return;
                }

                Student st = new Student(name,email,username,password,dept,year,prn,mobile,clg);
                dref.child("Student").child(username).setValue(st).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(stud_reg.this,e.toString(),Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(stud_reg.this,"Data has been Saved",Toast.LENGTH_SHORT).show();
                        go_to_login();
                    }
                });
            }
        });



        //fAuth = FirebaseAuth.getInstance(); //getting current instance of databse i.e from firebase
        /*if(fAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(),stud_login_page.class));
            finish();
        }*/

        /*sSubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = sEmail.getText().toString().trim();  //initially it is object then convert to string and trim to format data
                String password = sPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    sEmail.setError("Email is Required.");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    sPassword.setError("Password is Required.");
                    return;
                }
                if(password.length() < 6)
                {
                    sPassword.setError("Password Must be >= 6 Characters");
                    return;
                }

                sprogressBar.setVisibility(View.VISIBLE);

                //Register user in firebase
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(stud_reg.this,"Registration successfull",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Stud_info.class));
                        }
                        else
                        {
                            Toast.makeText(stud_reg.this,"Error! "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });*/
    }




       /* String name = sfullname.getText().toString();
        String username = susername.getText().toString().toLowerCase();
        String mobile = smobile.getText().toString();
        String dept = sdept.getText().toString();
        String prn = sprn.getText().toString();
        String year = syear.getText().toString();
        String clg = sclg.getText().toString();
        String email = sEmail.getText().toString().trim();
        String password = sPassword.getText().toString().trim();

        Student st = new Student(name,email,username,password,dept,year,prn,mobile,clg);
        dref.child("Student").child(username).setValue(st).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(stud_reg.this,e.toString(),Toast.LENGTH_SHORT);
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(stud_reg.this,"Data has been Saved",Toast.LENGTH_SHORT);
            }
        });
    }*/

    void go_to_login()
    {
        Intent intent6 = new Intent(this,stud_login_page.class);
        startActivity(intent6);
    }
}

