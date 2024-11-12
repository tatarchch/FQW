package com.example.FQW.response.exception.ServiceException;

public class ServiceNotFoundException extends RuntimeException {
    public ServiceNotFoundException() {
        super("Услуга не найдена");
    }
}
