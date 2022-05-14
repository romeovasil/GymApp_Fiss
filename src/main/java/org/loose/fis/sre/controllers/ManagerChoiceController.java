package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerChoiceController {

    @FXML
    public void handleClickAddClassAction() throws IOException {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getClassLoader().getResource("addClass.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Add Class");
            stage.setScene(new Scene(root, 300 , 300));
            stage.show();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }



    }
}
