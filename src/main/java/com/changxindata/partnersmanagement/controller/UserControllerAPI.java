package com.changxindata.partnersmanagement.controller;

import com.changxindata.partnersmanagement.common.ApplicationBean;
import com.changxindata.partnersmanagement.domain.system.User;
import com.changxindata.partnersmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserControllerAPI {

    @Autowired
    private UserService userService;

    @PostMapping(value ="/loginApply")
    @ResponseBody
    public ResponseEntity<Boolean> login(@RequestBody User user) {
        Boolean result = userService.checkUser(user.getUsername(), user.getPassword());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/updateInfo")
    @ResponseBody
    public ResponseEntity<Boolean> updateUserInfo(@RequestBody ApplicationBean application) {
        Boolean result = userService.updateUserProperty(application);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @GetMapping(value = "/list")
//    public ResponseEntity<Page<User>> getAllUsers(@RequestParam("start") Integer start,
//                                  @RequestParam("size") Integer size) {
//        int pageStart = (start == null) ? 0 : start.intValue();
//        int pageSize = (size == null) ? 50 : size.intValue();
//
//        return new ResponseEntity<>(userService.getAllUsers(pageStart, pageSize), HttpStatus.OK);
//    }

    @GetMapping(value = "/get")
    public ResponseEntity<User> getUserById(@RequestParam("id") String id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }
}
