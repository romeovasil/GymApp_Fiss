package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

import java.util.Objects;

public class Classes {
    @Id
    private int id;
    private String name;
    private String day;
    private String time;


    public Classes(int id ,String name, String day, String time) {
        this.id = id;
        this.name = name;
        this.day = day;
        this.time = time;

    }
    public  Classes(){

    }



    public String getName() {
        return name;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classes clasa = (Classes) o;
        if (name != null ? !name.equals(clasa.name) : clasa.name != null) return false;
        if (day != null ? !day.equals(clasa.day) : clasa.day != null) return false;
        return time != null ? time.equals(clasa.time) : clasa.time == null;
    }




}
