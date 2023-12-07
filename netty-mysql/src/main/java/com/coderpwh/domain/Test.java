package com.coderpwh.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.parser.ParserConfig;
import com.coderpwh.domain.User;

public class Test {


    public static void main(String[] args) {


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_name", "傻屌慧慧");
        jsonObject.put("password", "123456");
        jsonObject.put("create_time", "1111");

        ParserConfig parserConfig = new ParserConfig();
        parserConfig.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;

        User user = JSON.parseObject(jsonObject.toJSONString(),User.class,parserConfig);
        System.out.println(user.getUserName());
        System.out.println(user.getCreateTime());

        System.out.println(user);



    }

}
