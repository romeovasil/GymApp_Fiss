package org.loose.fis.sre.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.exceptions.ReqAlreadyExistsException;
import org.loose.fis.sre.model.Requests;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.ReqService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(ApplicationExtension.class)
public class MembershipsControllerTest {

    @Start
    void start(Stage primaryStage) throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".registration-example";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        UserService.addUser("abb", "aaaa", "client");

        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/memberships.fxml"));
        root = loader.load();
        MembershipsController membershipsController = loader.getController();
        membershipsController.setUsername("a");
        primaryStage.setTitle("Memberships");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    @Test
    void testChooseMembership(FxRobot robot) {

        robot.clickOn("#addButton");
        assertThat(robot.lookup("#successMessage").queryLabeled().getText()).
                isEqualTo("Please select a membership to add to the cart.");

        robot.clickOn("#membership");
        robot.type(KeyCode.ENTER);

        assertThat(robot.lookup("#daysLeft").queryText().getText()).isEqualTo("1 ");

        robot.clickOn("#addButton");
        assertThat(robot.lookup("#successMessage").queryLabeled().getText()).
                isEqualTo("You have added a membership of 1 days for 20 RON to the cart");
    }

}
