package org.loose.fis.sre.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.loose.fis.sre.services.UserService;

public class CheckoutController {

    public ListView<String> cart = new ListView<>() ;

    private String username;
    private String selectedMembership;

    @FXML
    public void setUsername(String username){
        this.username=username;
    }

    public void setListView(){
        cart.getItems().addAll(UserService.getMemberships(username));
    }

    public void selectedMembership(MouseEvent mouseEvent) {
        this.selectedMembership = cart.getSelectionModel().getSelectedItem();
    }

    public void payMembership(ActionEvent actionEvent) {

    }
}
