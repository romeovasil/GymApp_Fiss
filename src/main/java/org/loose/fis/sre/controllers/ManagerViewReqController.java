package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.loose.fis.sre.exceptions.NoReqSelected;
import org.loose.fis.sre.model.Classes;
import org.loose.fis.sre.model.Requests;
import org.loose.fis.sre.services.ClassesService;
import org.loose.fis.sre.services.ReqService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ManagerViewReqController {
    @FXML
    private Text registrationMessage;

    @FXML
    private ListView<String> req = new ListView<>() ;

    private List<Requests> listReq =new ArrayList<>();

    private List <String> listaString = new ArrayList<>();

    private String selectedReq;
    private int selectedIndex;

    public void setSelectedReqNull(){
        this.selectedReq= "";
    }

    public void refreshReq() {

        listReq=ReqService.getReqList();
        for (Requests requests : listReq) {
            if(!(Objects.equals(requests.getStatus(),"  - ACCEPTED")||Objects.equals(requests.getStatus(),"  - REJECTED")))
                listaString.add(requests.toString());
        }
        req.getItems().addAll(listaString);

    }
    private void checkSelectedReq() throws NoReqSelected{
        if (Objects.equals(this.selectedReq,""))
        {
            throw new NoReqSelected();
        }
    }

    public void acceptReq(){
        try {
            checkSelectedReq();
            ReqService.addStatus(this.selectedReq, "  - ACCEPTED");
            req.getItems().remove(this.selectedIndex);
            registrationMessage.setText("Req successfully accepted");
            this.selectedReq="";
        }catch (NoReqSelected e)
        {
            registrationMessage.setText(e.getMessage());
        }


    }

    public void declineReq(){
        try {
            checkSelectedReq();

            ReqService.addStatus(this.selectedReq, "  - REJECTED");
            req.getItems().remove(this.selectedIndex);
            registrationMessage.setText("Req successfully declined");
            this.selectedReq="";
        }catch (NoReqSelected e)
        {
            registrationMessage.setText(e.getMessage());
        }
    }

    public void selectedReq(){
        this.selectedReq=req.getSelectionModel().getSelectedItem();
        this.selectedIndex = req.getSelectionModel().getSelectedIndex();
    }




}
