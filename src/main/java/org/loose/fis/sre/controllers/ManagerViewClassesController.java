package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.loose.fis.sre.services.ClassesService;

import java.util.ArrayList;
import java.util.List;

public class ManagerViewClassesController {

    @FXML
    private ListView<String> luni = new ListView<>() ;

    private List<String> listLuni =new ArrayList<>();



    public void refreshClase() {
        listLuni=ClassesService.getClassesList();
        luni.getItems().addAll(listLuni);

    }



}
