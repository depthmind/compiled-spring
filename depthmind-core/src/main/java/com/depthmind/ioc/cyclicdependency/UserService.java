package com.depthmind.ioc.cyclicdependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liuhan
 */
//@Service
public class UserService {

//	@Resource(name = "indexService")
	@Autowired
    private IndexService indexService;

    public UserService() {
		//indexService.sout();
        System.out.println("UserService Constructor");
    }
}
