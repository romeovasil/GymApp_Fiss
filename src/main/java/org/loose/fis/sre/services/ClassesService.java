package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.ClasaAlreadyExistsException;
import org.loose.fis.sre.exceptions.InvalidAccountException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.model.Classes;
import org.loose.fis.sre.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class ClassesService {
    private static ObjectRepository<Classes> clasaRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("classes5.db").toFile())
                .openOrCreate("clasa", "clasa");

        clasaRepository = database.getRepository(Classes.class);
    }

    private static int maxId (){
        int nr =0;
        for (Classes clasa : clasaRepository.find()) {
            nr++;

        }
        return nr;
    }

    public static void addClasa(String name, String day, String time) throws ClasaAlreadyExistsException {
        checkClasaDoesNotAlreadyExist(name,day,time);

        int nr = maxId();
        clasaRepository.insert(new Classes(nr,name , day, time));
    }

//    public static void checkValidUser(String username, String password) throws InvalidAccountException {
//        int ok=0;
//        String pass = encodePassword(username,password);
//        for (User user : userRepository.find()) {
//            if (Objects.equals(username, user.getUsername()) && Objects.equals(pass,user.getPassword()))
//                ok =1;
//        }
//        if ( ok ==0){
//            throw new InvalidAccountException();
//        }
//    }

    private static void checkClasaDoesNotAlreadyExist(String name, String day , String time) throws ClasaAlreadyExistsException {
        for (Classes clasa : clasaRepository.find()) {
            if (Objects.equals(name, clasa.getName()) && Objects.equals(day,clasa.getDay()) && Objects.equals(time,clasa.getTime()))
                throw new ClasaAlreadyExistsException();
        }
    }




}