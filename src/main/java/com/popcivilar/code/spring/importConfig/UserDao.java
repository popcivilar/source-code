package com.popcivilar.code.spring.importConfig;

import org.springframework.stereotype.Repository;

public interface UserDao {
    @MockSelect("select * from user")
    public String list();
}
