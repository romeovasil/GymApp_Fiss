package org.loose.fis.sre.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.loose.fis.sre.exceptions.ClasaAlreadyExistsException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.model.Classes;
import org.loose.fis.sre.model.User;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testfx.assertions.api.Assertions.assertThat;

class ClassesServiceTest {

    public static final String CLASSNAME = "zumba";

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
        ClassesService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        System.out.println("After each");
    }


    @Test
    @DisplayName("Database is initialized, and there are no classes")
    void testDatabaseIsInitializedAndNoClassIsPersisted() {
        assertThat(ClassesService.getAllClasses()).isNotNull();
        assertThat(ClassesService.getAllClasses()).isEmpty();
    }

    @Test
    @DisplayName("Class is successfully persisted to Database")
    void testClassIsAddedToDatabase() throws ClasaAlreadyExistsException   {
        ClassesService.addClasa(CLASSNAME, CLASSNAME, CLASSNAME);
        assertThat(ClassesService.getAllClasses()).isNotEmpty();
        assertThat(ClassesService.getAllClasses()).size().isEqualTo(1);
        Classes clasa = ClassesService.getAllClasses().get(0);
        assertThat(clasa).isNotNull();
        assertThat(clasa.getName()).isEqualTo(CLASSNAME);
        assertThat(clasa.getDay()).isEqualTo(CLASSNAME);
        assertThat(clasa.getTime()).isEqualTo(CLASSNAME);
    }


    @Test
    @DisplayName("class can not be added twice")
    void testClassCanNotBeAddedTwice() {
        assertThrows(ClasaAlreadyExistsException.class, () -> {
            ClassesService.addClasa(CLASSNAME,CLASSNAME,CLASSNAME);
            ClassesService.addClasa(CLASSNAME,CLASSNAME,CLASSNAME);
        });
    }
}