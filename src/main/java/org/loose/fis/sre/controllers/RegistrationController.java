package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.exceptions.BadPasswordException;
import org.loose.fis.sre.exceptions.MissingRoleException;
import org.loose.fis.sre.exceptions.MissingUsernameException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.UserService;

public class RegistrationController {

    @FXML
    private Text registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private ChoiceBox role;

    @FXML
    public void initialize() {
        role.getItems().addAll("Client", "Admin");
    }

    @FXML
    public void handleRegisterAction() {
        try {
            UserService.checkUsername(usernameField.getText());
            UserService.checkPassword(passwordField.getText());
            UserService.checkRole(role);
            UserService.addUser(usernameField.getText(), passwordField.getText(), (String) role.getValue());
            MembershipsController membershipsController = new MembershipsController();
            membershipsController.setUsername(usernameField.getText());
            registrationMessage.setText("Account created successfully!");
        } catch (UsernameAlreadyExistsException e) {
            registrationMessage.setText(e.getMessage());
        }
        catch (MissingUsernameException e)
        {
            registrationMessage.setText(e.getMessage());
        }
        catch (BadPasswordException e)
        {
            registrationMessage.setText(e.getMessage());
        }
        catch (MissingRoleException e)
        {
            registrationMessage.setText(e.getMessage());
        }
    }
}
