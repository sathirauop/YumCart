package com.sathira.yumcart.utils.exceptions;

public class DuplicateEntityException extends RuntimeException {
    public DuplicateEntityException(String EntityName) {
        super("Trying to create duplicate records with "+EntityName);
    }
}

// TODO : Not only when duplicate entries found but other thinks like a required fiels gets null also this error occurs
//  as this implementation.
