package org.loose.fis.sre.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import org.loose.fis.sre.services.UserService;

public class MembershipsController {


    @FXML
    private ChoiceBox membership;
    @FXML
    private Text daysLeft;

    @FXML
    public void initialize() {
        membership.getItems().addAll("1 day - 20 ron", "7 days - 75 ron","30 days - 180 ron" , "1 year - 1200 ron");
        daysLeft.setText(Integer.toString(3));
    }


    }