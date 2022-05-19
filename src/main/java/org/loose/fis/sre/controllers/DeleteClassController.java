package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.loose.fis.sre.exceptions.NoClassSelected;
import org.loose.fis.sre.exceptions.NoReqSelected;
import org.loose.fis.sre.model.Classes;
import org.loose.fis.sre.services.ClassesService;
import org.loose.fis.sre.services.ReqService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeleteClassController {

    @FXML
    private ListView<String> luni = new ListView<>() ;

    private List<Classes> listLuni =new ArrayList<>();

    private List <String> listaString = new ArrayList<>();
    private int selectedIndex;
    private String selectedClass;

    @FXML
    private Text registrationMessage;

    public void setSelectedClassNull(){
        this.selectedClass= "";
    }

    private void checkSelectedClass() throws NoClassSelected {
        if (Objects.equals(this.selectedClass,""))
        {
            throw new NoClassSelected();
        }
    }

    public void refreshClase() {
        listLuni= ClassesService.getClassesList();
        for (Classes clasa : listLuni) {
            listaString.add(clasa.toString());
        }
        luni.getItems().addAll(listaString);

    }






    public void deleteClass(){
        try {
            checkSelectedClass();
            luni.getItems().remove(this.selectedIndex);
            ClassesService.deleteClass(this.selectedClass);
            registrationMessage.setText("Class successfully deleted");
            this.selectedClass="";
        }catch (NoClassSelected e)
        {
            registrationMessage.setText(e.getMessage());
        }
    }

    public void selectedReq(){
        this.selectedClass = luni.getSelectionModel().getSelectedItem();
        this.selectedIndex = luni.getSelectionModel().getSelectedIndex();

    }


}
