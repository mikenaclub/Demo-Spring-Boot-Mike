package com.miniproject.com.miniproject.testapi;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by STR02119 on 6/23/2017.
 */
@RestController
public class apitest {

    @PostMapping("/api/test")
    public void testapi() {
        System.out.print("API SUCCESS");
    }
}
