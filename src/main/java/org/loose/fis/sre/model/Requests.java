package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

import java.util.Objects;

public class Requests {
    @Id
    private int id;
    private String username;
    private String clasa;


    public Requests(int id,String username, String clasa) {
        this.id=id;
        this.username = username;
        this.clasa=clasa;

    }
    public  Requests(){

    }

    public String getUsername(){
        return this.username;

    }

    public String getClasa(){
        return this.clasa;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Requests req = (Requests) o;
        if (username != null ? !username.equals(req.username) : req.username != null) return false;
        return clasa != null ? clasa.equals(req.clasa) : req.clasa == null;
    }

    @Override
    public String toString()
    {
        return this.username +" : "+ this.clasa ;
    }




}
