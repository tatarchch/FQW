package com.example.FQW.exception.ServiceException;

public class ServiceNotFoundException extends RuntimeException {
    public ServiceNotFoundException() {
        super("Услуга не найдена");
    }
}
