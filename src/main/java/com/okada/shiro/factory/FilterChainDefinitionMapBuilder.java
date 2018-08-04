package com.okada.shiro.factory;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {

    public LinkedHashMap<String, String> buildFilterChainDefinitionMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        //此处声明关系也可是已配置在数据库中的
        map.put("/login.jsp", "anon");
        map.put("/user/login", "anon");
        map.put("/user/logout", "logout");
        map.put("/user.jsp", "roles[user]");
        map.put("/admin.jsp", "roles[admin]");
        map.put("/**", "authc");

        return map;
    }
}
