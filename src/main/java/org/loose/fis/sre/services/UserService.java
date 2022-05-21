package org.loose.fis.sre.services;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.*;
import org.loose.fis.sre.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class UserService {

    private static ObjectRepository<User> userRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("registration-example11.db").toFile())
                .openOrCreate("test", "test");

        userRepository = database.getRepository(User.class);
    }



    public static int getDaysLeft(String username){
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                return user.getDaysLeft();
        }
        return 0;
    }

    public static void updateMembership(String username, int daysLeft, String memb){
        User u;
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername())) {
                u = user;
                int currDays = u.getDaysLeft();
                u.setDaysLeft(daysLeft + currDays);
                List<String> memberships = u.getMemberships();
                if(daysLeft > 0){
                    memberships.remove(memb);
                    u.setMemberships(memberships);
                } else {
                    memberships.add(memb);
                    u.setMemberships(memberships);
                }
                userRepository.update(u);
            }
        }
    }

    public static List<String> getMemberships(String username){
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                return user.getMemberships();
        }
        return null;
    }

    public static void checkValidCard(String number, String name, String date, String cvv) throws IncorrectCardDataException {
        boolean valid = true;

        if(number.length() != 16)
            valid = false;
        if(name.isEmpty())
            valid = false;
        if(!date.matches("[0-1][0-9]/[0-9][0-9]"))
            valid = false;
        if(!cvv.matches("[0-9]{3}"))
            valid = false;
        if(!valid)
            throw new IncorrectCardDataException();
    }

    @FXML
    public static void addUser(String username, String password, String role) throws UsernameAlreadyExistsException {
        checkUserDoesNotAlreadyExist(username);
        userRepository.insert(new User(0,username, encodePassword(username, password), (String)role));
    }

    public static  void checkUsername(String username) throws MissingUsernameException {
        if ( username.equals("") )
            throw new MissingUsernameException();
    }
    public static  void checkPassword(String password) throws BadPasswordException{
        if (password.length()<=3 )
            throw new BadPasswordException();
    }
    public static  void checkRole(ChoiceBox role) throws MissingRoleException{
        if (role.getSelectionModel().isEmpty())
            throw new MissingRoleException();
    }
    public static List<User> getAllUsers(){
        return userRepository.find().toList();
    }

    public static void checkValidUser(String username, String password , String role) throws InvalidAccountException {
        int ok=0;
        String pass = encodePassword(username,password);
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()) && Objects.equals(pass,user.getPassword()) && Objects.equals(role,user.getRole()))
                ok =1;
        }
        if ( ok ==0){
            throw new InvalidAccountException();
        }
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    public static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }


}
