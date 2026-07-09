package com.example.demo.controller;

import com.example.demo.model.Request;
import com.example.demo.model.Response;
import com.example.demo.services.Optimizeservice;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(
        origins = "http://localhost:5173"
)
@RequestMapping("/api")
public class Optimizecontroller {

    private final Optimizeservice service;

    public Optimizecontroller(
            Optimizeservice service
    ) {
        this.service = service;
    }

    @PostMapping("/optimize")
    public Response optimize(
            @RequestBody Request req
    ) {
        return service.optimize(req);
    }

    @GetMapping("/test")
    public String test() {
        return "API Working 🚀";
    }
}