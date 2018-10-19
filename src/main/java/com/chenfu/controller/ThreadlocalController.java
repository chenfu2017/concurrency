package com.chenfu.controller;

import com.chenfu.threadlocal.RequestHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/threadlocal")
public class ThreadlocalController {

    @RequestMapping("/test")
    @ResponseBody
    public Long test(){
        return RequestHolder.get();
    }

}
