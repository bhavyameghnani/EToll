package com.example.lenovo.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QRCode extends AppCompatActivity implements View.OnClickListener {

    EditText qrText;
    Button qrConvert , qrBack;
    ImageView qrImage;
    String qrData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        qrConvert = (Button)findViewById(R.id.qrConvert);
        qrBack  = (Button)findViewById(R.id.qrBack);
        qrText = (EditText)findViewById(R.id.qrEditTextData);
        qrImage =(ImageView)findViewById(R.id.qrImage);




        qrConvert.setOnClickListener(this);
        qrBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==qrConvert) {
            qrData = qrText.getText().toString().trim();
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(qrData, BarcodeFormat.QR_CODE, 200, 200);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                qrImage.setImageBitmap(bitmap);

            } catch (WriterException e) {
                e.printStackTrace();

            }
        }

        if(v==qrBack){
            finish();
            Intent intent = new Intent(QRCode.this, DashboardActivity.class);
            startActivity(intent);
        }
    }
}
