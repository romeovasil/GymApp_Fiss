package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.exceptions.ClasaAlreadyExistsException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.services.ClassesService;
import org.loose.fis.sre.services.UserService;

public class AddClassController {
    @FXML
    private Text registrationMessage;
    @FXML
    private TextField nameField;
    @FXML
    private TextField dayField;
    @FXML
    private TextField timeField;


    @FXML
    public void handleAddClassAction() {
        try {
            ClassesService.addClasa(nameField.getText(), dayField.getText(),  timeField.getText());
            registrationMessage.setText("Class added successfully!");
        } catch (ClasaAlreadyExistsException e) {
            registrationMessage.setText(e.getMessage());
        }
    }
}
