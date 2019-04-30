package com.example.healthhappy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.List;

/**
 * Created by tanni on 3/18/2018.
 */

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoHolder> {


    private Context context;
    List<Todo> todos;


    public TodoAdapter(Context context, List<Todo> todos) {
        this.context = context;
        this.todos = todos;
    }

    class TodoHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvDesc;
        private ImageView imgTodo;
        private CardView cvTodo;
        private Button btnDel;

        TodoHolder(View view) {
            super(view);

            tvTitle = view.findViewById(R.id.namaTodo);
            tvDesc = view.findViewById(R.id.descTodo);
            imgTodo = view.findViewById(R.id.imageView3);
            cvTodo = view.findViewById(R.id.cardTodo);
            btnDel = view.findViewById(R.id.delTodo);

        }
    }

    @Override
    public TodoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TodoHolder(LayoutInflater.from(context).
                inflate(R.layout.item_list_todo, parent, false));
    }

    @Override
    public void onBindViewHolder(final TodoHolder holder, final int position) {
        final Todo data = todos.get(position);

        holder.tvTitle.setText(data.namaTodo);
        holder.tvDesc.setText(data.deskripsiTodo);
        Glide.with(context)
                .load(data.getImage_url())
                .into(holder.imgTodo);

        holder.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference dbTodo = FirebaseDatabase.getInstance().getReference("TODO").child(data.getId());
                dbTodo.removeValue();
                Toast.makeText(context, "Todo telah di Hapus", Toast.LENGTH_LONG).show();
            }
        });

        holder.cvTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailTodo.class);
                intent.putExtra("judul", data.namaTodo);
                intent.putExtra("deskripsi", data.deskripsiTodo);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    public Todo getDataTodo(int position){
        return todos.get(position);
    }


}


