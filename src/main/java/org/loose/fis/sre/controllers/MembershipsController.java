package org.loose.fis.sre.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.UserService;

import java.util.Objects;

public class MembershipsController {

    private String username;
    @FXML
    private ChoiceBox membership;
    @FXML
    private Text daysLeft;

    private int nrDays;

    @FXML
    public void initialize() {
        membership.getItems().addAll("1 day - 20 ron", "7 days - 75 ron","30 days - 180 ron" , "1 year - 1200 ron");

    }
    @FXML
    public void setUsername(String username){
        this.username=username;
    }

    public void setDaysLeft(){
        this.nrDays=UserService.getDaysLeft(this.username);
        this.daysLeft.setText(Integer.toString(nrDays));
    }




    }