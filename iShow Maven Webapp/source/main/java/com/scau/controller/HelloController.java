package com.scau.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    
    @RequestMapping("/hello.do")
    public @ResponseBody String test() {
        return "result";
    }

    @RequestMapping("/hi.do")
    public @ResponseBody ArrayList<HashMap<String, Object>> te() {
        ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
        HashMap<String,Object> map1 = new HashMap<String, Object>();
        map1.put("old", 1);
        map1.put("name", "xiaoming");
       
        HashMap<String,Object> map2 = new HashMap<String, Object>();
        map2.put("old", 1);
        map2.put("name", "xiaoming");
        
        list.add(map1);
        list.add(map2);
        return list;
        		 
    }

}