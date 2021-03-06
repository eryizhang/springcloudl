package com.spb.consumer1.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControler
{

    @Autowired
    HelloService helloService;

    @RequestMapping(value = "/hiSimple")
    public String hi(@RequestParam String name)
    {
        return helloService.hiService(name);
    }

    @RequestMapping(value = "/hiFIGN")
    public String hiFIGN(@RequestParam String name)
    {
        return helloService.hiServiceFIGN(name);
    }
}

