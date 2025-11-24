package com.learnspringboot.product.controller;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;
@SuppressWarnings("unused")
@Component
@Endpoint(id = "custom")
public class CustomEndpoint {

    @ReadOperation
    public String getCustom() {
        return "This is Custom Endpoint";
    }
}