package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

public class StartPageController {

    @FXML
    public void handleClickRegisterAction() throws IOException {
    try {
        Parent root;
        root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Register");
        stage.setScene(new Scene(root, 700 , 350));
        stage.show();
    }
    catch(IOException ex)
        {
            ex.printStackTrace();
        }



    }


    @FXML
    public void handleClickLogInAction() throws IOException {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getClassLoader().getResource("logIn.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root, 700 , 350));
            stage.show();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }



    }

}