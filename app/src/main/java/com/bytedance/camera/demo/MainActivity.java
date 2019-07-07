package com.bytedance.camera.demo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CAMARA = 1;

    private static final int REQUEST_EXTERNAL_STORAGE = 101;

    private static final int REQUEST_RECORD_AUDIO= 121;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_picture).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, TakePictureActivity.class));
        });

        findViewById(R.id.btn_camera).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, RecordVideoActivity.class));
        });

        findViewById(R.id.btn_custom).setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},REQUEST_CAMARA);
            }
            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_EXTERNAL_STORAGE);
            }
            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.RECORD_AUDIO},REQUEST_RECORD_AUDIO);
            }
            startActivity(new Intent(MainActivity.this, CustomCameraActivity.class));

        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE: {
                //判断权限是否已经授予
                int permissionCheck = ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "hava storage permission", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "no storage permission", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case REQUEST_CAMARA: {
                //判断权限是否已经授予
                int permissionCheck = ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CAMERA);
                if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "hava camara permission", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "no camara permission", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case REQUEST_RECORD_AUDIO: {
                //判断权限是否已经授予
                int permissionCheck = ContextCompat.checkSelfPermission(this,
                        Manifest.permission.RECORD_AUDIO);
                if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "hava microphone permission", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "no microphone permission", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

}
