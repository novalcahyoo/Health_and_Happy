package com.example.healthhappy;

/**
 * Created by tanni pc on 3/31/2019.
 */

public class Todo {

    public String id,namaTodo,deskripsiTodo;
    public String image_url;

    public Todo(String id, String namaTodo, String deskripsiTodo, String image_url) {
        this.id = id;
        this.namaTodo = namaTodo;
        this.deskripsiTodo = deskripsiTodo;
        this.image_url = image_url;
    }

    public Todo() {
    }
//setter dan getter makanan

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaTodo() {
        return namaTodo;
    }

    public void setNamaTodo(String namaTodo) {
        this.namaTodo = namaTodo;
    }

    public String getDeskripsiTodo() {
        return deskripsiTodo;
    }

    public void setDeskripsiTodo(String deskripsiTodo) {
        this.deskripsiTodo = deskripsiTodo;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

}
