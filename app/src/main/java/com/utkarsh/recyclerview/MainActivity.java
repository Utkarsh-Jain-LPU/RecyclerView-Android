package com.utkarsh.recyclerview;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Bitmap bitmap;
    private String data,time;
    private EditText name;
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerAdapter;
    ArrayList<Example> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        name = findViewById(R.id.name);
        Button open = findViewById(R.id.open);
        Button add = findViewById(R.id.add);

        arrayList = new ArrayList<>();

        recyclerAdapter = new RecyclerAdapter(MainActivity.this,arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(recyclerAdapter);

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,131,null);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdata();
                if(data.isEmpty()) {
                    Toast.makeText(MainActivity.this,"Name field must be filled...",Toast.LENGTH_SHORT).show();
                }
                else if(bitmap == null) {
                    Toast.makeText(MainActivity.this,"User didn't Captured the Image...",Toast.LENGTH_SHORT).show();
                }
                else {
                    arrayList.add(new Example(data,time,bitmap));
                    recyclerAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this,"Item added Successfully...",Toast.LENGTH_SHORT).show();
                    name.setText("");
                    bitmap = null;
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==131 && resultCode==RESULT_OK) {
            assert data != null;
            bitmap = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
            time = Calendar.getInstance().getTime().toString();
            Toast.makeText(MainActivity.this,"Image Captured...",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(MainActivity.this,"User cancelled the request...",Toast.LENGTH_SHORT).show();
        }
    }

    private void getdata() {
        data = name.getText().toString().trim();
    }
}
