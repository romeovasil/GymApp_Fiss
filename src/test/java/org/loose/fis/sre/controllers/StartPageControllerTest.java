package org.loose.fis.sre.controllers;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOExceptionList;
import org.assertj.core.internal.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.exceptions.ClasaAlreadyExistsException;
import org.loose.fis.sre.exceptions.NoReqSelected;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.ClassesService;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.ReqService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;


import java.io.IOException;

import static org.assertj.core.api.Assertions.fail;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)

public class StartPageControllerTest {




    @Start
    void start(Stage primaryStage) throws Exception {

        FileSystemService.APPLICATION_FOLDER = ".registration-example";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        ReqService.initDatabase();
        ClassesService.initDatabase();

        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/welcome.fxml"));
        root = loader.load();

        primaryStage.setTitle("Start Page");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();





    }


    @Test
    void testRegBut(FxRobot robot) throws Exception {


        try {
            robot.clickOn("#but2");
        }catch (Exception e )
        {
            fail("error");
        }






    }
    @Test
    void testLoginBut(FxRobot robot) throws Exception {


        try {
            robot.clickOn("#but1");
        }catch (Exception e )
        {
            fail("error");
        }
    }

}