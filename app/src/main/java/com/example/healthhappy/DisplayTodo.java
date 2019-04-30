package com.example.healthhappy;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DisplayTodo extends AppCompatActivity {

    private static final String TAG = DisplayTodo.class.getSimpleName();
    RecyclerView rvTodo;
    DatabaseReference databaseTodo;
    List<Todo> todos;
    SwipeRefreshLayout swpRfrsh;
    CardView cvTodo;
    Button btnDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_todo);

        DisplayTodo.this.setTitle("Display Todo");

        swpRfrsh = (SwipeRefreshLayout) findViewById(R.id.rfrshLayout);
        rvTodo = (RecyclerView) findViewById(R.id.ivTodo);
        databaseTodo = FirebaseDatabase.getInstance().getReference("TODO");
        todos = new ArrayList<>();
        cvTodo = (CardView) findViewById(R.id.cardTodo);
        btnDel = (Button) findViewById(R.id.delTodo);


        rvTodo.setLayoutManager(new LinearLayoutManager(DisplayTodo.this));
        databaseTodo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                todos.clear();
                for (DataSnapshot produkSnapshot : dataSnapshot.getChildren()) {

                    Todo produk = produkSnapshot.getValue(Todo.class);
                    todos.add(produk);

                }
                TodoAdapter todoAdapter = new TodoAdapter(DisplayTodo.this, todos);
                rvTodo.setAdapter(todoAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "eror : .", databaseError.toException());

            }
        });

        swpRfrsh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }


    private void refresh() {
        swpRfrsh.setRefreshing(true);
        databaseTodo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                todos.clear();

                for (DataSnapshot produkSnapshot : dataSnapshot.getChildren()) {

                    Todo produk = produkSnapshot.getValue(Todo.class);
                    todos.add(produk);
                }

                TodoAdapter todoAdapter = new TodoAdapter(DisplayTodo.this, todos);
                rvTodo.setAdapter(todoAdapter);
                swpRfrsh.setRefreshing(false);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "eror : .", databaseError.toException());

            }
        });
        return;
    }
}
