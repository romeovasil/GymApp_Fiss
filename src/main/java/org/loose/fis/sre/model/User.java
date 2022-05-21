package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

import java.util.ArrayList;
import java.util.List;

public class User {
    @Id
    private String username;
    private String password;
    private String role;
    private List<String> statusList ;
    private int daysLeft;
    private List<Integer> memberships;

    public User(int daysLeft ,String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.daysLeft = daysLeft;
        statusList = new ArrayList<>();
        memberships = new ArrayList<>();
    }

    public User() {
    }

    public List<Integer> getMemberships() {
        return memberships;
    }

    public void setMemberships(List<Integer> memberships) {
        this.memberships = memberships;
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
    }

    public void addStatus(String status)
    {
        statusList.add(status);
    }

    public String getUsername() {
        return username;
    }

    public int getDaysLeft(){return daysLeft;}

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return role != null ? role.equals(user.role) : user.role == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
