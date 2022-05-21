package org.loose.fis.sre.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.exceptions.IncorrectCardDataException;
import org.loose.fis.sre.services.UserService;

public class CardPaymentController {
    public Label labelMembership;
    public Text successMessage;
    public TextField cvvField;
    public TextField nameField;
    public TextField numberField;
    public TextField dateField;

    private String username;
    private String membership;

    @FXML
    public void setUsername(String username){
        this.username=username;
    }

    public void handleAddCardAction(ActionEvent actionEvent) {
        String number = numberField.getText();
        String name = nameField.getText();
        String cvv = cvvField.getText();
        String date = dateField.getText();

        try {
            UserService.checkValidCard(number, name, date, cvv);
            int days = 0;
            int price = 0;
            if(membership.contains("year")) {
                days = 365;
                price = 1200;
            }
            else {
                days = Integer.parseInt(membership.substring(0, 2).split(" ")[0]);
                price = Integer.parseInt(membership.split(" ")[3]);
            }
            UserService.updateMembership(this.username, days, membership);
            successMessage.setText("The membership is paid - " + price + " RON");
        } catch (IncorrectCardDataException e){
            successMessage.setText(e.getMessage());
        }
    }

    public void setLabel(String membership) {
        this.labelMembership.setText(membership);
        this.membership = membership;
    }
}
