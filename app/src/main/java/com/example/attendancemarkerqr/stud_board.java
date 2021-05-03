package com.example.attendancemarkerqr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class stud_board extends AppCompatActivity {

    Button scan_btn;
    public static TextView qrtext , subtext , nxttext;
    public static ImageView tick;
    public static ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_board);

        scan_btn = findViewById(R.id.scan_btn);
        qrtext = findViewById(R.id.qr_text);
        subtext = findViewById(R.id.sub_text);
        nxttext = findViewById(R.id.nxt_txt);
        tick = findViewById(R.id.img_tick);
        logo = findViewById(R.id.img_logo);

        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Att_Scanner.class));
            }
        });
    }
}
