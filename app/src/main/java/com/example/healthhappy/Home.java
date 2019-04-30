package com.example.healthhappy;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthhappy.Chat.ChatActivity;
import com.example.healthhappy.NearestGym.NearestGymActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class Home extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListner;
    FirebaseUser user;
    GoogleSignInClient mGoogleSignInClient;
    GoogleApiClient mGoogleApiClient;
    GoogleSignInAccount gAccount;
    private static final String TAG = "";
    FloatingActionButton fabCall,fabChat;


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();
        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser()==null)
                {
                    startActivity(new Intent(Home.this, Login.class));
                }
            }
        };

        fabCall = (FloatingActionButton) findViewById(R.id.btnCall);
        fabCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeACall("082218340199");
            }
        });

        fabChat = (FloatingActionButton) findViewById(R.id.btnChat);
        fabChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(Home.this, ChatActivity.class);
                startActivity(intent5);
            }
        });
    }

    public void listenerMenu(View view) {
        switch (view.getId()){
            case R.id.imgWorkout:
                Intent intent1 = new Intent(Home.this,HalamanWorkout.class);
                startActivity(intent1);
                break;
            case R.id.imgDiet:
                Intent intent2 = new Intent(Home.this,ListDiet.class);
                startActivity(intent2);
                break;
            case R.id.imgTodo:
                Intent intent3 = new Intent(Home.this,MenuTodo.class);
                startActivity(intent3);
                break;
            case R.id.imgMaps:
                Intent intent4 = new Intent(Home.this,NearestGymActivity.class);
                startActivity(intent4);
                break;
        }
    }
    //method untuk implement menu pada activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu); // inflate atau memasukkan menu
        return super.onCreateOptionsMenu(menu);
    }

    //method untuk handling menu yang di klik dari daftar di menu yang di implement
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnLogout:
                mAuth.getInstance().signOut();
                startActivity(new Intent(Home.this, Login.class)); //panggil login activity
                finish();
                break;
            case R.id.mnAbout:
                startActivity(new Intent(Home.this, AboutUs.class)); //panggil login activity
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void makeACall(final String phoneNumber){
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }
}


