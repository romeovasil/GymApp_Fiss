package org.loose.fis.sre.exceptions;

public class BadPasswordException extends Exception {



    public  BadPasswordException() {
        super( "Missing password or too short password ( use at least 4 characters)");

    }


}
