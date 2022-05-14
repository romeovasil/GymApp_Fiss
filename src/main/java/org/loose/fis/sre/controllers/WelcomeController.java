package org.loose.fis.sre.controllers;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.ClasaAlreadyExistsException;
import org.loose.fis.sre.model.Classes;
import org.loose.fis.sre.services.ClassesService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class WelcomeController {

    @FXML
    private ListView<String> luni = new ListView<>() ;

    private List<String> listLuni =new ArrayList<>();




    public void refreshClase() {
        listLuni=ClassesService.getClassesList();
        luni.getItems().addAll(listLuni);
    }


    @FXML
    public void handleClickMembershipsAction() throws IOException {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getClassLoader().getResource("memberships.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Memberships");
            stage.setScene(new Scene(root, 300, 300));
            stage.show();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }



    }

}
