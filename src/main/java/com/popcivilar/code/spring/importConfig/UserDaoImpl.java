package com.popcivilar.code.spring.importConfig;

import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    public UserDaoImpl() {
        System.out.println("实例化了");
    }

    @Override
    public String list() {
        System.out.println(1112);
        return "111";
    }
}
