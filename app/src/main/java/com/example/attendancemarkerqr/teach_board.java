package com.example.attendancemarkerqr;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;


public class teach_board extends AppCompatActivity {

    EditText qrValue;
    Button but_generate , but_save;
    ImageView qrImage;
    Bitmap qrBits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teach_board);

        qrValue = findViewById(R.id.edit_qr_input);
        but_generate = findViewById(R.id.but_generate);
        qrImage = findViewById(R.id.qr_image);
        but_save = findViewById(R.id.but_saveQR);

        but_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = qrValue.getText().toString();
                if (input.isEmpty()) {
                    qrValue.setError("Enter Subject and date");
                } else {
                    QRGEncoder qrgEncoder = new QRGEncoder(input, null, QRGContents.Type.TEXT, 500);
                    //it takes multiple argumnets 1.value we need to give to generate qr 2.bundle--not passing any bundle--so null
                    //3. type of data passing into encoder--here text 4.dimensions of qr to generate
                    try {
                        qrBits = qrgEncoder.encodeAsBitmap();
                        //qrBits = qrgEncoder.getBitmap();
                        qrImage.setImageBitmap(qrBits);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        /*but_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean save;
                String result;
                try {
                    save = QRGSaver.save(savePath, edtValue.getText().toString().trim(), bitmap, QRGContents.ImageType.IMAGE_JPEG);
                    result = save ? "Image Saved" : "Image Not Saved";
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });*/


    }
}
