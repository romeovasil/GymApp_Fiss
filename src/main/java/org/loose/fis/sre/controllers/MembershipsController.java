package org.loose.fis.sre.controllers;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
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

    public Label successMessage;
    public Text currentDays;
    private String username;
    @FXML
    private ChoiceBox<String> membership;
    @FXML
    private Text daysLeft;

    private int nrDays;

    @FXML
    public void initialize() {
        membership.getItems().addAll("1 day - 20 ron", "7 days - 75 ron","30 days - 180 ron" , "1 year - 1200 ron");
        setEventListener();
        this.daysLeft.setText(String.valueOf(0));
    }
    @FXML
    public void setUsername(String username){
        this.username=username;
        setDaysLeft();
    }

    public void setEventListener(){
        membership.setOnAction(event -> {
            if(membership.getValue().contains("year")) {
                this.daysLeft.setText(Integer.toString(365));
            }
            else {
                this.daysLeft.setText(membership.getValue().substring(0, 2));
            }
        });
    }

    public void setDaysLeft() {
        this.nrDays = UserService.getDaysLeft(this.username);
        this.currentDays.setText(Integer.toString(nrDays));
        this.daysLeft.setText(Integer.toString(nrDays));
    }


    public void onAddClick(ActionEvent actionEvent) {
        String selected = membership.getSelectionModel().getSelectedItem().toString();
        int days = 0;
        int price = 0;
        if(selected.contains("year")) {
            days = 365;
            price = 1200;
        }
        else {
            days = Integer.parseInt(selected.substring(0, 2).split(" ")[0]);
            price = Integer.parseInt(selected.split(" ")[3]);
        }
        UserService.updateMembership(username, 0, price);
        successMessage.setText("You have added a membership of " + days + " days for " + price + " RON to the cart");
    }


}