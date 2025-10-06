package com.ms.product.model;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message){
        super(message);
    }

}
