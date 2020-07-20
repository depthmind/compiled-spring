package com.depthmind.ioc.cyclicdependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author liuhan
 */
@Component
public class UserService {

//	@Resource(name = "indexService")
	@Autowired
    private IndexService indexService;

    public UserService() {
		indexService.sout();
        System.out.println("UserService Constructor");
    }
}
