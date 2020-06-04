package com.depthmind.ioc.cyclicdependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    private IndexService indexService;

    public UserService() {
        System.out.println("UserService Constructor");
    }
}
