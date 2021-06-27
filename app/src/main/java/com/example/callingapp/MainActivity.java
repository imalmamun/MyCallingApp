package com.example.callingapp;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    EditText phoneNumber;
    ImageView callButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        view binding here.....

        phoneNumber = findViewById(R.id.et_phone_number);
        callButton = findViewById(R.id.btn_call);


        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallingFunction();
            }
        });
    }

//    all calling functionality here..........

    private void CallingFunction() {
        String number = phoneNumber.getText().toString().trim();
        if(number.length()>0){
            if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            }else{
                String call = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(call)));
            }

        } else{
            Toast.makeText(this, "You have to input phone number first", Toast.LENGTH_SHORT).show();
        }
    }

//    permission result function here.........


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                CallingFunction();
            }else{
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}