package org.loose.fis.sre.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
public class CheckoutControllerTest {

    @Start
    void start(Stage primaryStage) throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".registration-example";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        UserService.addUser("a", "aaaa", "client");
        UserService.updateMembership("a", 0, "1 year - 1200 ron");

        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/checkout.fxml"));
        root = loader.load();
        CheckoutController checkoutController = loader.getController();
        checkoutController.setUsername("a");
        checkoutController.setListView();
        primaryStage.setTitle("Checkout");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    @Test
    void testChooseMembershipToPay(FxRobot robot) {

        robot.clickOn("#payButton");
        assertThat(robot.lookup("#registrationMessage").queryText().getText()).
                isEqualTo("Please select a membership to pay");

        robot.clickOn("#cart");
        robot.type(KeyCode.ENTER);
        robot.clickOn("#payButton");

        robot.clickOn("#cart");
        robot.type(KeyCode.ENTER);
        robot.clickOn("#payButton");

        assertThat(robot.lookup("#registrationMessage").queryText().getText()).
                isEqualTo("You selected 1 year - 1200 ron");

    }

}
