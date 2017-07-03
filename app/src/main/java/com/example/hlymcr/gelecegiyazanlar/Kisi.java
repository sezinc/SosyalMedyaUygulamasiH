package com.example.hlymcr.gelecegiyazanlar;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by HULYA on 10.06.2017.
 */

public class Kisi implements Serializable {

    private String isim;
    private String password;
    private String email;
    private String cinsiyet;
    private String tarih;
    private ArrayList<Kisi> arkadaslar;
    private String url;


    public Kisi(String isim, String password, String email, String cinsiyet, String tarih, String url) {
        this.isim = isim;
        this.password = password;
        this.email = email;
        this.cinsiyet = cinsiyet;
        this.tarih = tarih;
        arkadaslar = new ArrayList<>();
        this.url = url;
    }




    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public ArrayList<Kisi> getArkadaslar() {
        return arkadaslar;
    }

    public void setArkadaslar(ArrayList<Kisi> arkadaslar) {
        this.arkadaslar = arkadaslar;
    }


    public void arkadasEkle(Kisi k) {
        arkadaslar.add(k);
    }

    public void arkadasSil(int i) {
        arkadaslar.remove(i);
    }

}
