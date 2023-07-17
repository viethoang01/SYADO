package com.example.project4.model;

import com.example.project4.model.Image;

import java.io.Serializable;
import java.util.List;

public class Host implements Serializable {
    private int id;
    private String owner_id;
    private String name,address,phone,price;

    private int rate;
    private double lat,longl;
    private List<Image> imageList;
    private int room_num;
    private List<Comment> comment;

    public Host() {
    }

    public Host(int id, String owner_id, String name, String address, String phone, String price, int rate, double lat, double longl, List<Image> imageList, int room_num, List<Comment> comment) {
        this.id = id;
        this.owner_id = owner_id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.price = price;
        this.rate = rate;
        this.lat = lat;
        this.longl = longl;
        this.imageList = imageList;
        this.room_num = room_num;
        this.comment = comment;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLongl() {
        return longl;
    }

    public void setLongl(double longl) {
        this.longl = longl;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public int getRoom_num() {
        return room_num;
    }

    public void setRoom_num(int room_num) {
        this.room_num = room_num;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }
}
