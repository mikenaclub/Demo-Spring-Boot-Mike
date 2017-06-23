package com.miniproject.com.miniproject.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * Created by STR02119 on 6/21/2017.
 */
@RestController
public class Usercontroller {

    @Autowired
    Userrepository userrepository;

    /*@PostMapping("/api/regisUser/Createuser")
    @ResponseBody
    public void Createuser() {
        System.out.print("Create User!!!");
//        userrepository.createuser(userinfo);
        //return new ResponseEntity("Create Success", HttpStatus.OK);
    }*/
    @PostMapping("/api/test")
    @ResponseBody
    public void test() {
        System.out.print("TEST");
    }
}
