package org.loose.fis.sre.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.loose.fis.sre.exceptions.ClasaAlreadyExistsException;
import org.loose.fis.sre.exceptions.ReqAlreadyExistsException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.model.Classes;
import org.loose.fis.sre.model.Requests;
import org.loose.fis.sre.model.User;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testfx.assertions.api.Assertions.assertThat;

class ReqServiceTest {

    public static final String USERNAME = "vasil";
    public static final String REQ = "YOGA";


    @BeforeAll
    static void beforeAll() {
        System.out.println("Before Class");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After Class");
    }

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".registration-example";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        ReqService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        System.out.println("After each");
    }


    @Test
    @DisplayName("Database is initialized, and there are no req")
    void testDatabaseIsInitializedAndNoReqIsPersisted() {
        assertThat(ReqService.getAllReq()).isNotNull();
        assertThat(ReqService.getAllReq()).isEmpty();
    }

    @Test
    @DisplayName("Req is successfully persisted to Database")
    void testReqIsAddedToDatabase() throws ReqAlreadyExistsException {
        ReqService.addReq(USERNAME, REQ);
        assertThat(ReqService.getAllReq()).isNotEmpty();
        assertThat(ReqService.getAllReq()).size().isEqualTo(1);
        Requests req = ReqService.getAllReq().get(0);
        assertThat(req).isNotNull();
        assertThat(req.getUsername()).isEqualTo(USERNAME);
        assertThat(req.getClasa()).isEqualTo(REQ);
    }


    @Test
    @DisplayName("Req can not be added twice")
    void testReqCanNotBeAddedTwice() {
        assertThrows(ReqAlreadyExistsException.class, () -> {
            ReqService.addReq(USERNAME,REQ);
            ReqService.addReq(USERNAME,REQ);
        });
    }
}