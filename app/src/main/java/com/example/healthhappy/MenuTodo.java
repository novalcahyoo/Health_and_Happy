package com.example.healthhappy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MenuTodo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_todo);

        MenuTodo.this.setTitle("Menu Todo");

    }

    public void intentMenu(View view) {
        switch (view.getId()){
            case R.id.cardCreate:
                Intent intent = new Intent(MenuTodo.this,AddTodo.class);
                startActivity(intent);
                break;
            case R.id.cardList:
                Intent intent2 = new Intent(MenuTodo.this,DisplayTodo.class);
                startActivity(intent2);
                break;
        }
    }
}
