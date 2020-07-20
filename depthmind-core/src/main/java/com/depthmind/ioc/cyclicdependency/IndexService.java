package com.depthmind.ioc.cyclicdependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liuhan
 */
@Component("indexService")
public class IndexService {

    @Autowired
    private UserService userService;

    public IndexService() {
        System.out.println("IndexService Constructor");
    }

    public void sout() {
        System.out.println("hhh");
    }
}
