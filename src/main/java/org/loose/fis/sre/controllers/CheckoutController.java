package org.loose.fis.sre.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

public class CheckoutController {

    public ListView<String> cart = new ListView<>() ;
    public Text registrationMessage;

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
        try {
            if(selectedMembership == null)
                registrationMessage.setText("Please select a membership to pay");
            else {
                Parent root;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/cardPayment.fxml"));
                root = loader.load();
                CardPaymentController cardPaymentController = loader.getController();
                cardPaymentController.setUsername(this.username);
                cardPaymentController.setLabel(this.selectedMembership);
                Stage stage = new Stage();
                stage.setTitle("Memberships");
                stage.setScene(new Scene(root, 700 , 350));
                stage.show();
            }
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
