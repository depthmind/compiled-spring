package com.depthmind.ioc.cyclicdependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author liuhan
 */
@Service
public class IndexService {

//    @Autowired
//    private UserService userService;

    public IndexService() {
        System.out.println("IndexService Constructor");
    }

    public void sout() {
        System.out.println("hhh");
    }
}
