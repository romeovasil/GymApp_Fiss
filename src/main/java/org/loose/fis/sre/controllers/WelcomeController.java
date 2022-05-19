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
import org.loose.fis.sre.exceptions.NoReqSelected;
import org.loose.fis.sre.exceptions.ReqAlreadyExistsException;
import org.loose.fis.sre.model.Classes;
import org.loose.fis.sre.services.ClassesService;
import org.loose.fis.sre.services.ReqService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class WelcomeController {

    @FXML
    private ListView<String> luni = new ListView<>() ;

    private List<Classes> listLuni =new ArrayList<>();

    private String selectedC ;

    private String username;

    @FXML
    private Text reqMessage;


    private List <String> listaString = new ArrayList<>();
    public void setSelectedReqNull(){
        this.selectedC= "";
    }

    public void refreshClase() {
        listLuni=ClassesService.getClassesList();
        for (Classes clasa : listLuni) {
            listaString.add(clasa.toString());
        }
        luni.getItems().addAll(listaString);

    }


    @FXML

    public void selectedClass(){
        this.selectedC=luni.getSelectionModel().getSelectedItem();
    }

    public void setUsername(String username)
    {
        this.username=username;
    }

    @FXML
    public void handleClickBookTheClass(){
        try {
            checkSelectedReq();
            ReqService.addReq(this.username, this.selectedC);
            reqMessage.setText("Req sent");
            this.selectedC="";
        }
        catch (NoReqSelected e )
        {
            reqMessage.setText(e.getMessage());
            this.selectedC= "";
        }
        catch (ReqAlreadyExistsException e)
        {
            reqMessage.setText(e.getMessage());
            this.selectedC= "";
        }
    }
    private void checkSelectedReq() throws NoReqSelected {
        if (Objects.equals(this.selectedC,""))
        {
            throw new NoReqSelected();
        }
    }
    @FXML
    public void handleClickMembershipsAction() throws IOException {
        try {
            Parent root;


            root = FXMLLoader.load(getClass().getClassLoader().getResource("memberships.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Memberships");
            stage.setScene(new Scene(root, 700 , 350));
            stage.show();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }



    }

    @FXML
    public void handleClickReqStatus() throws IOException {
        try {
            Parent root;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/requestsStatus.fxml"));
            root = loader.load();
            RequestsStatusController requestsStatusController = loader.getController();
            requestsStatusController.refreshStatus(this.username);



            Stage stage = new Stage();
            stage.setTitle("Requests status");
            stage.setScene(new Scene(root, 700 , 350));
            stage.show();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }



    }



}
