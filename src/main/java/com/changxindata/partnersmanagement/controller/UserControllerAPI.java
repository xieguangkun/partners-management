package com.changxindata.partnersmanagement.controller;

import com.changxindata.partnersmanagement.common.ApplicationBean;
import com.changxindata.partnersmanagement.common.Response;
import com.changxindata.partnersmanagement.domain.system.Permission;
import com.changxindata.partnersmanagement.domain.system.User;
import com.changxindata.partnersmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins="*")
@RestController
@RequestMapping("/user")
public class UserControllerAPI {

    @Autowired
    private UserService userService;

    @PostMapping(value ="/loginApply")
    @ResponseBody
    public Response login(@RequestBody User user) {
        User registeredUser = userService.checkUser(user.getUsername(), user.getPassword());

        if(registeredUser.getEnable()) {
            Response response = new Response(registeredUser);
            response.setResultCode(HttpStatus.OK.value());
            response.setResultMsg("登陆成功");
            return response;
        } else {
            Response response = Response.EMPTY;
            response.setResultCode(HttpStatus.OK.value());
            response.setResultMsg("账号或密码错误");
            return response;
        }
    }

    @PostMapping(value = "/updateInfo")
    @ResponseBody
    public Response updateUserInfo(@RequestBody ApplicationBean application) {
        Response response = Response.EMPTY;
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

    @GetMapping(value = "/permissions")
    public ResponseEntity<List<String>> getPermissionsByRoleId(@RequestParam("id") String id) {
        return new ResponseEntity<>(userService.getAllPermission(id), HttpStatus.OK);
    }
}
