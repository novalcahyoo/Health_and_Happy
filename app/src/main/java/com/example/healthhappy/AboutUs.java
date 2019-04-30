package com.example.healthhappy;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        AboutUs.this.setTitle("About Us");
    }

    public void intentProfile(View view){
        switch (view.getId()){
            case R.id.profTNN:
                Uri uri1 = Uri.parse("https://www.linkedin.com/in/naufalcahyo/"); // missing 'http://' will cause crashed
                Intent intent1 = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(intent1);
                break;
            case R.id.profANS:
                Uri uri2 = Uri.parse("https://www.linkedin.com/in/naufalcahyo/"); // missing 'http://' will cause crashed
                Intent intent2 = new Intent(Intent.ACTION_VIEW, uri2);
                startActivity(intent2);
                break;
            case R.id.profERN:
                Uri uri3 = Uri.parse("https://www.linkedin.com/in/naufalcahyo/"); // missing 'http://' will cause crashed
                Intent intent3 = new Intent(Intent.ACTION_VIEW, uri3);
                startActivity(intent3);
                break;
            case R.id.profANI:
                Uri uri4 = Uri.parse("https://www.linkedin.com/in/naufalcahyo/"); // missing 'http://' will cause crashed
                Intent intent4 = new Intent(Intent.ACTION_VIEW, uri4);
                startActivity(intent4);
                break;
        }
    }
}
