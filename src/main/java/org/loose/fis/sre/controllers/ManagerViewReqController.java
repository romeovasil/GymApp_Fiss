package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.loose.fis.sre.services.ClassesService;
import org.loose.fis.sre.services.ReqService;

import java.util.ArrayList;
import java.util.List;

public class ManagerViewReqController {

    @FXML
    private ListView<String> req = new ListView<>() ;

    private List<String> listReq =new ArrayList<>();



    public void acceptReq(){


    }

    public void declineReq(){

    }

    public void selectedReq(){
//        this.selectedC=luni.getSelectionModel().getSelectedItem();
    }



    public void refreshReq() {
        listReq= ReqService.getReqList();
        req.getItems().addAll(listReq);

    }
}
