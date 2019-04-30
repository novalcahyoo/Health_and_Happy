package com.example.healthhappy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailTodo extends AppCompatActivity {

    TextView jdlDtl,descDtl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_todo);

        DetailTodo.this.setTitle(getIntent().getStringExtra("judul"));

        jdlDtl = (TextView) findViewById(R.id.titleDetail);
        descDtl = (TextView) findViewById(R.id.descDetail);

        if (getIntent() != null){
            jdlDtl.setText(getIntent().getStringExtra("judul"));
            descDtl.setText(getIntent().getStringExtra("deskripsi"));
        }
    }
}
