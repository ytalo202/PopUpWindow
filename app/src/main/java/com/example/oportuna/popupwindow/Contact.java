package com.example.oportuna.popupwindow;

public class Contact {

    private String Name;
    private String Phone;
    private int Photo;
    private String numPuntos;

    private String descrip;
    public Contact() {
    }

    public Contact(String name, String phone, int photo) {
        Name = name;
        Phone = phone;
        Photo = photo;
    }


    public Contact(String name, String phone, int photo, String numPuntos, String descrip) {
        Name = name;
        Phone = phone;
        Photo = photo;
        this.numPuntos = numPuntos;
        this.descrip = descrip;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getPhoto() {
        return Photo;
    }

    public void setPhoto(int photo) {
        Photo = photo;
    }

    public String getNumPuntos() {
        return numPuntos;
    }

    public void setNumPuntos(String numPuntos) {
        this.numPuntos = numPuntos;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }
}
