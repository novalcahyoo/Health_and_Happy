package com.example.healthhappy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class HalamanWorkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_workout);

        HalamanWorkout.this.setTitle("Work Out Recommendation");

    }

    public void listenerWorkout(View view) {
        switch (view.getId()){
            case R.id.imageView6:
                Uri uri1 = Uri.parse("https://www.youtube.com/watch?v=DHvSGdCIZyQ"); // missing 'http://' will cause crashed
                Intent intent1 = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(intent1);
                break;
            case R.id.imageView7:
                Uri uri2 = Uri.parse("https://www.youtube.com/watch?v=UJezMYvf8Ss"); // missing 'http://' will cause crashed
                Intent intent2 = new Intent(Intent.ACTION_VIEW, uri2);
                startActivity(intent2);
                break;
        }
    }
}
