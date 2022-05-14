package org.loose.fis.sre.controllers;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.loose.fis.sre.exceptions.ClasaAlreadyExistsException;
import org.loose.fis.sre.model.Classes;
import org.loose.fis.sre.services.ClassesService;

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

}
