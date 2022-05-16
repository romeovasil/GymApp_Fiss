package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.*;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;
import java.util.Objects;

public class LogInController {

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
    public void handleLogInAction() {
        try {
            UserService.checkUsername(usernameField.getText());
            UserService.checkPassword(passwordField.getText());
            UserService.checkRole(role);
            UserService.checkValidUser(usernameField.getText(), passwordField.getText(),(String) role.getValue());

            registrationMessage.setText("Valid Account!");


            if (Objects.equals(role.getValue(), "Client")) try {
                Parent root;




                FXMLLoader loader = new FXMLLoader(getClass().getResource("/memberships.fxml"));
                root = loader.load();
                MembershipsController membershipsController = loader.getController();
                membershipsController.setUsername(usernameField.getText());
                membershipsController.setDaysLeft();


                loader = new FXMLLoader(getClass().getResource("/welcome.fxml"));
                root = loader.load();
                WelcomeController welcomeController = loader.getController();
                welcomeController.refreshClase();
                welcomeController.setUsername(usernameField.getText());




                Stage stage = new Stage();
                stage.setTitle("GymApp");
                stage.setScene(new Scene(root, 300, 300));
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            else if (Objects.equals(role.getValue(), "Admin")){
                try {





                    Parent root;
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("managerChoice.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("ManagerChoice");
                    stage.setScene(new Scene(root, 300, 300));
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
            else
            {
                registrationMessage.setText("Select the user type!");
            }




        } catch (InvalidAccountException e) {
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
