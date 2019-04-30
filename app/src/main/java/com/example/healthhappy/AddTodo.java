package com.example.healthhappy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddTodo extends AppCompatActivity {

    EditText eTNama,eTDeskripsi;
    Button btnTodo,btnPilih;
    private int PLACE_PICKER_REQUEST = 1;
    ImageView imageView;
    DatabaseReference databaseTodo;
    private Uri filePath;
    public static FirebaseStorage storage;
    public static StorageReference storageRef;
    private StorageReference refProdukImage;
    private final int PICK_IMAGE_REQUEST = 71;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        AddTodo.this.setTitle("Add Todo");

        eTNama = (EditText) findViewById(R.id.editTextNamaTodo);
        eTDeskripsi = (EditText) findViewById(R.id.editTextDeskripsi);
        btnPilih = (Button) findViewById(R.id.pilihGambar);
        btnTodo = (Button) findViewById(R.id.tambah);
        imageView = (ImageView) findViewById(R.id.imageView);
        //database makanan dan deklarasi variabel penyimpanan
        databaseTodo = FirebaseDatabase.getInstance().getReference("TODO");
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        //listener button choose image mengeksekusi method chooseImage()
        btnPilih.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });

        //listener button choose image mengeksekusi method addLaporan()
        btnTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTodo();
            }
        });

    }

    private void addTodo() {

        final String nama = eTNama.getText().toString().trim();
        final String deskripsi = eTDeskripsi.getText().toString().trim();

        if (!TextUtils.isEmpty(nama)) {

            //melakukan proses update foto
            refProdukImage = storageRef.child("gambar/" + System.currentTimeMillis() + ".jpg"); //akses path dan filename storage di firebase untuk menyimpan gambar
            StorageReference photoImagesRef = storageRef.child("gambar/" + System.currentTimeMillis() + ".jpg");
            refProdukImage.getName().equals(photoImagesRef.getName());
            refProdukImage.getPath().equals(photoImagesRef.getPath());

            //mengambil gambar dari imageview yang sudah di set menjadi selected image sebelumnya
            imageView.setDrawingCacheEnabled(true);
            imageView.buildDrawingCache();
            Bitmap bitmap = imageView.getDrawingCache(); //convert imageview ke bitmap
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos); //convert bitmap ke bytearray
            byte[] data = baos.toByteArray();

            UploadTask uploadTask = refProdukImage.putBytes(data); //upload image yang sudah dalam bentuk bytearray ke firebase storage
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(AddTodo.this, "berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                    //setelah selesai upload, ambil url gambar
                    String img_url = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                    //push atau insert data ke firebase database
                    String id = databaseTodo.push().getKey();
                    Todo todo = new Todo(id,nama,deskripsi,img_url);
                    databaseTodo.child(id).setValue(todo);

                    Toast.makeText(AddTodo.this, "berhasil ditambahkan", Toast.LENGTH_SHORT).show();

                    eTNama.setText("");
                    eTDeskripsi.setText("");
                    Drawable myDrawable = getResources().getDrawable(R.mipmap.ic_launcher);
                    imageView.setImageDrawable(myDrawable);
                }
            });
        } else {
            Toast.makeText(this, "masukan nama menu", Toast.LENGTH_LONG).show();
        }
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

}
