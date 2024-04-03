package com.sathira.yumcart.utils.exceptions;

public class DuplicateEntityException extends RuntimeException {
    public DuplicateEntityException(String EntityName) {
        super("Trying to create duplicate records with "+EntityName);
    }
}
