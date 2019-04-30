package com.example.healthhappy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.healthhappy.Diet.DietKeto;
import com.example.healthhappy.Diet.DietMayo;
import com.example.healthhappy.Diet.DietOcd;

public class ListDiet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_diet);

        ListDiet.this.setTitle("List Diet");
    }

    public void intentMenu (View view) {
        switch (view.getId()){
            case R.id.dietketo:
                Intent intent1 = new Intent(ListDiet.this, DietKeto.class);
                startActivity(intent1);
                break;
            case R.id.dietmayo:
                Intent intent2 = new Intent(ListDiet.this, DietMayo.class);
                startActivity(intent2);
                break;
            case R.id.dietocd:
                Intent intent3 = new Intent(ListDiet.this, DietOcd.class);
                startActivity(intent3);
                break;
        }
    }

}
