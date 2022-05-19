package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerChoiceController {

    @FXML
    public void handleClickViewClassesAction() throws  IOException{
        try {

            Parent root;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/managerViewClasses.fxml"));
            root = loader.load();
            ManagerViewClassesController managerViewClassesController = loader.getController();
            managerViewClassesController.refreshClase();

//            root = FXMLLoader.load(getClass().getClassLoader().getResource("managerViewClasses.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Classes");
            stage.setScene(new Scene(root, 700 , 350));
            stage.show();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }

    }

    @FXML
    public void handleClickViewReqAction() throws IOException {
        try {
            Parent root;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/managerViewReq.fxml"));
            root = loader.load();
            ManagerViewReqController managerViewReqController = loader.getController();
            managerViewReqController.refreshReq();
            managerViewReqController.setSelectedReqNull();


//            root = FXMLLoader.load(getClass().getClassLoader().getResource("managerViewReq.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Requests");

            stage.setScene(new Scene(root, 700 , 350));
            stage.show();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }



    }

    @FXML
    public void handleClickDeleteClass() throws IOException {
        try {

            Parent root;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/deleteClass.fxml"));
            root = loader.load();
            DeleteClassController  deleteClassController = loader.getController();
            deleteClassController.refreshClase();

//            root = FXMLLoader.load(getClass().getClassLoader().getResource("managerViewClasses.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Delete Classes");
            stage.setScene(new Scene(root, 700 , 350));
            stage.show();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }

    }


    @FXML
    public void handleClickAddClassAction() throws IOException {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getClassLoader().getResource("addClass.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Add Class");
            stage.setScene(new Scene(root, 700 , 350));
            stage.show();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }



    }
}
