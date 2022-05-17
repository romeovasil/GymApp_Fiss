package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.loose.fis.sre.model.Requests;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.ReqService;

import java.util.ArrayList;
import java.util.List;

public class RequestsStatusController {

    @FXML
    private ListView<String> reqStatus = new ListView<>() ;

    private List<Requests> listStat =new ArrayList<>();

    private List <String> listaString = new ArrayList<>();

    public void refreshStatus(String username) {
        listStat = ReqService.getReqWithStatusList(username);
        for (Requests requests : listStat) {
            listaString.add(requests.toString()+requests.getStatus());
        }
        reqStatus.getItems().addAll(listaString);

    }
}
