package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.loose.fis.sre.model.Classes;
import org.loose.fis.sre.model.Requests;
import org.loose.fis.sre.services.ClassesService;
import org.loose.fis.sre.services.ReqService;

import java.util.ArrayList;
import java.util.List;

public class ManagerViewReqController {

    @FXML
    private ListView<String> req = new ListView<>() ;

    private List<Requests> listReq =new ArrayList<>();

    private List <String> listaString = new ArrayList<>();

    private String selectedReq;

    public void refreshReq() {

        listReq=ReqService.getReqList();
        for (Requests requests : listReq) {
            listaString.add(requests.toString());
        }
        req.getItems().addAll(listaString);

    }

    public void acceptReq(){
        ReqService.addStatus(this.selectedReq,"  - ACCEPTED");


    }

    public void declineReq(){
        ReqService.addStatus(this.selectedReq,"  - REJECTED");
    }

    public void selectedReq(){
        this.selectedReq=req.getSelectionModel().getSelectedItem();
    }




}
