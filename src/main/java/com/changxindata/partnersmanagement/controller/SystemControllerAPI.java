package com.changxindata.partnersmanagement.controller;

import com.changxindata.partnersmanagement.common.ApplicationBean;
import com.changxindata.partnersmanagement.common.PermissionRoleBean;
import com.changxindata.partnersmanagement.domain.system.Permission;
import com.changxindata.partnersmanagement.domain.system.PermissionRole;
import com.changxindata.partnersmanagement.domain.system.Role;
import com.changxindata.partnersmanagement.domain.system.User;
import com.changxindata.partnersmanagement.repository.PermissionRepository;
import com.changxindata.partnersmanagement.repository.PermissionRoleRepository;
import com.changxindata.partnersmanagement.repository.RoleRepository;
import com.changxindata.partnersmanagement.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sys")
public class SystemControllerAPI {

    private static final String DEFAULT_OPERATOR = "超级管理员";
    private final static String SORT_BY_DATE_CREATED = "dateCreated";

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PermissionRepository permissionRepo;

    @Autowired
    private PermissionRoleRepository permissionRoleRepo;

    //=====User====
    @PostMapping("/addUser")
    @ResponseBody
    public ResponseEntity<Boolean> addUser(@RequestBody ApplicationBean application) {
        if(application.getOperator().equals(DEFAULT_OPERATOR)) {
            User user = new User();
            BeanUtils.copyProperties(application, user);
            userRepo.save(user);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PostMapping("/updateUserRole")
    @ResponseBody
    public ResponseEntity<Boolean> updateUserRole(@RequestBody ApplicationBean application) {
        if(application.getOperator().equals(DEFAULT_OPERATOR)) {
            userRepo.updateRole(application.getRoleId(), application.getUserId());
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping(value = "/list")
    public ResponseEntity<Page<User>> getAllUsers(@RequestParam("start") Integer start,
                                                  @RequestParam("size") Integer size) {
        int pageStart = (start == null) ? 0 : start.intValue();
        int pageSize = (size == null) ? 50 : size.intValue();
        Pageable pageable = PageRequest.of(
                pageStart,
                pageSize,
                new Sort(Sort.Direction.DESC, SORT_BY_DATE_CREATED)
        );
        return new ResponseEntity<>(userRepo.findAll(pageable), HttpStatus.OK);
    }


    //=====Role====
    @PostMapping("/addRole")
    @ResponseBody
    public ResponseEntity<Boolean> addRole(@RequestBody ApplicationBean application) {
        if(application.getOperator().equals(DEFAULT_OPERATOR)) {
            Role role = new Role();
            BeanUtils.copyProperties(application, role);
            roleRepo.save(role);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PostMapping("/removeRole")
    @ResponseBody
    public ResponseEntity<Boolean> removeRole(@RequestBody ApplicationBean application) {
        if(application.getOperator().equals(DEFAULT_OPERATOR)) {
            roleRepo.deleteById(application.getRoleId());
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PostMapping("/updatePermissionRole")
    @ResponseBody
    public ResponseEntity<Boolean> updatePermissionRole(@RequestBody PermissionRoleBean application) {
        if(application.getOperator().equals(DEFAULT_OPERATOR)) {
            String roleId = application.getRoleId();
            permissionRoleRepo.deleteAllByRoleId(roleId);
            List<PermissionRole> list = new ArrayList<>();
            for(String permissionId : application.getPermissionIds()) {
                PermissionRole permissionRole = new PermissionRole();
                permissionRole.setRoleId(roleId);
                permissionRole.setPermissionId(permissionId);
                list.add(permissionRole);
            }
            if(!list.isEmpty()) {
                permissionRoleRepo.saveAll(list);
            }

            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.METHOD_NOT_ALLOWED);
        }
    }


    //=====Permission====
    @PostMapping("/addPermission")
    @ResponseBody
    public ResponseEntity<Boolean> addPermission(@RequestBody ApplicationBean application) {
        if(application.getOperator().equals(DEFAULT_OPERATOR)) {
            Permission permission = new Permission();
            BeanUtils.copyProperties(application, permission);
            permissionRepo.save(permission);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PostMapping("/removePermission")
    @ResponseBody
    public ResponseEntity<Boolean> removePermission(@RequestBody ApplicationBean application) {
        if(application.getOperator().equals(DEFAULT_OPERATOR)) {
            permissionRepo.deleteById(application.getPermissionId());
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.METHOD_NOT_ALLOWED);
        }
    }


}
