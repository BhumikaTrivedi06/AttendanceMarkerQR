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

public class teach_reg extends AppCompatActivity {

    public EditText tEmail, tPassword, tfullname, tmobile, tdept, tprn, tyear, tclg , tusername;
    public Button tSubmitbtn;

    DatabaseReference dref;

    //public FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teach_reg);

        tEmail = findViewById(R.id.edit_email2);
        tPassword = findViewById(R.id.edit_pswd2);
        tSubmitbtn = findViewById(R.id.but_submt2);
        tfullname = findViewById(R.id.edit_t_fullname);
        tmobile = findViewById(R.id.edit_t_mobile);
        tdept = findViewById(R.id.edit_t_dept);
        tprn = findViewById(R.id.edit_t_prn);
        tyear = findViewById(R.id.edit_t_year);
        tclg = findViewById(R.id.edit_t_clg);
        tusername = findViewById(R.id.edit_t_username);

        dref = FirebaseDatabase.getInstance().getReference("Details");

        tSubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = tfullname.getText().toString();
                String username = tusername.getText().toString().toLowerCase();
                String mobile = tmobile.getText().toString();
                String dept = tdept.getText().toString();
                String prn = tprn.getText().toString();
                String year = tyear.getText().toString();
                String clg = tclg.getText().toString();
                String email = tEmail.getText().toString().trim();
                String password = tPassword.getText().toString().trim();

                if(TextUtils.isEmpty(name))
                {
                    tfullname.setError("Fullname is Required.");
                    return;
                }
                if(TextUtils.isEmpty(username))
                {
                    tusername.setError("Username is Required.");
                    return;
                }
                if(TextUtils.isEmpty(email))
                {
                    tEmail.setError("Email is Required.");
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
                else if(TextUtils.isEmpty(prn))
                {
                    tprn.setError("Unique ID is Required.");
                    return;
                }
                else if(TextUtils.isEmpty(clg))
                {
                    tclg.setError("College is Required.");
                    return;
                }
                else if(TextUtils.isEmpty(year))
                {
                    tyear.setError("Year is Required.");
                    return;
                }
                else if(TextUtils.isEmpty(password))
                {
                    tPassword.setError("College is Required.");
                    return;
                }
                if(mobile.length() < 10)
                {
                    tmobile.setError("Mobile No Must be 10 Characters");
                    return;
                }
                if(year.length() > 1)
                {
                    tyear.setError("Enter Valid year");
                    return;
                }
                if(password.length() < 6)
                {
                    tPassword.setError("Password Must be >= 6 Characters");
                    return;
                }



                Student st = new Student(name, email, username, password, dept, year, prn, mobile, clg);
                dref.child("Staff").child(username).setValue(st).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(teach_reg.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(teach_reg.this, "Data has been Saved", Toast.LENGTH_SHORT).show();
                        go_to_login();
                    }
                });
            }
        });
    }


        //fAuth = FirebaseAuth.getInstance(); //getting current instance of databse i.e from firebase
        /*if(fAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(),stud_login_page.class));
            finish();
        }*/

        /*tSubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = tEmail.getText().toString().trim();  //initially it is object then convert to string and trim to format data
                String password = tPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    tEmail.setError("Email is Required.");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    tPassword.setError("Password is Required.");
                    return;
                }
                if(password.length() < 6)
                {
                    tPassword.setError("Password Must be >= 6 Characters");
                    return;
                }

                tprogressBar.setVisibility(View.VISIBLE);

                //Register user in firebase
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(teach_reg.this,"Registration successfull",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),teach_info.class));
                        }
                        else
                        {
                            Toast.makeText(teach_reg.this,"Error! "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });*/


    void go_to_login()
    {
        Intent intent10 = new Intent(this,teach_login_page.class);
        startActivity(intent10);
    }

}

