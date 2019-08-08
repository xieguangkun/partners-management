package com.changxindata.partnersmanagement.controller;

import com.changxindata.partnersmanagement.common.ApplicationBean;
import com.changxindata.partnersmanagement.common.Response;
import com.changxindata.partnersmanagement.domain.system.User;
import com.changxindata.partnersmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserControllerAPI {

    @Autowired
    private UserService userService;

    @PostMapping(value ="/loginApply")
    @ResponseBody
    public Response login(@RequestBody User user) {
        Response response = new Response();
        Boolean result = userService.checkUser(user.getUsername(), user.getPassword());

        response.setResultCode(HttpStatus.OK.value());
        if(result) {
            response.setResultMsg("登陆成功");
        } else {
            response.setResultMsg("账号或密码错误");
        }
        return response;
    }

    @PostMapping(value = "/updateInfo")
    @ResponseBody
    public Response updateUserInfo(@RequestBody ApplicationBean application) {
        Response response = new Response();
        Boolean result = userService.updateUserProperty(application);

        response.setResultCode(HttpStatus.OK.value());
        if(result) {
            response.setResultMsg("更新成功");
        } else {
            response.setResultMsg("更新失败");
        }
        return response;
    }

    @GetMapping(value = "/get")
    public ResponseEntity<User> getUserById(@RequestParam("id") String id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }
}
