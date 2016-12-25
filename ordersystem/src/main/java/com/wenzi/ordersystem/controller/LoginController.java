package com.wenzi.ordersystem.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Yue on 2016/12/7.
 */
@Controller
public class LoginController {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login() {
//        List list = jdbcTemplate.queryForList("select * from sdk_users");
//        JSONObject retJson = new JSONObject();
//        return new ResponseEntity<String>(list.toString(), HttpStatus.OK);
        JSONObject retJson = new JSONObject();
        retJson.put("msg", "Success");
        return new ResponseEntity<String>(retJson.toJSONString(), HttpStatus.OK);
    }
}
