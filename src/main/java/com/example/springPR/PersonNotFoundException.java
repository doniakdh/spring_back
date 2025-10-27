package com.example.springPR;

public class PersonNotFoundException extends RuntimeException{
    public PersonNotFoundException(String message){
        super(message);
    }

}
