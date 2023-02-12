package com.talk.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"home"})
@RestController
public class HelloController {

    @ApiOperation(value = "home리턴")
    @GetMapping("/home")
    public String home(){
            return "home";
        }
}
