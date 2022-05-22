package org.loose.fis.sre.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.ReqService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
public class ManagerViewReqControllerTest {

    @Start
    void start(Stage primaryStage) throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".registration-example";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        ReqService.initDatabase();
        UserService.initDatabase();
        UserService.addUser("assda", "cevaaa", "Client");
        ReqService.addReq("assda", "aaaa");
        ReqService.addReq("assda2", "aaaa1");

        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/managerViewReq.fxml"));
        root = loader.load();
        ManagerViewReqController managerViewReqController = loader.getController();
        managerViewReqController.refreshReq();


        primaryStage.setTitle("Manager Req List");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    @Test
    void testAcceptReq(FxRobot robot) {

        robot.clickOn("#req");
        robot.type(KeyCode.ENTER);
        robot.clickOn("#acc");
        assertThat(robot.lookup("#registrationMessage").queryText().getText()).
                isEqualTo("Req successfully accepted");

    }

}
