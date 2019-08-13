package com.changxindata.partnersmanagement.controller;

import com.changxindata.partnersmanagement.common.ApplicationBean;
import com.changxindata.partnersmanagement.common.PermissionRoleBean;
import com.changxindata.partnersmanagement.common.Response;
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
import java.util.Optional;

@CrossOrigin(origins = "*")
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
    public Response addUser(@RequestBody ApplicationBean application) {
        Response response = Response.EMPTY;

        if(DEFAULT_OPERATOR.equals(application.getOperator()) || !DEFAULT_OPERATOR.equals(application.getName())) {
            Optional findUser = userRepo.findUserByUsername(application.getUsername());
            if(findUser.isPresent()) {
                response.setResultCode(HttpStatus.OK.value());
                response.setResultMsg("用户已存在");
                return response;
            }

            User user = new User();
            BeanUtils.copyProperties(application, user);

            userRepo.save(user);
            response.setResultCode(HttpStatus.OK.value());
            response.setResultMsg(HttpStatus.OK.getReasonPhrase());
        } else {
            response.setResultCode(HttpStatus.METHOD_NOT_ALLOWED.value());
            response.setResultMsg(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
        }
        return response;
    }

    @PostMapping("/updateUserRole")
    @ResponseBody
    public Response updateUserRole(@RequestBody ApplicationBean application) {
        Response response = Response.EMPTY;

        if(application.getOperator().equals(DEFAULT_OPERATOR)) {
            userRepo.updateRole(application.getRoleId(), application.getUserId());
            response.setResultCode(HttpStatus.OK.value());
            response.setResultMsg(HttpStatus.OK.getReasonPhrase());
        } else {
            response.setResultCode(HttpStatus.METHOD_NOT_ALLOWED.value());
            response.setResultMsg(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
        }
        return response;
    }

    @GetMapping(value = "/userList")
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
    public Response addRole(@RequestBody ApplicationBean application) {
        Response response = Response.EMPTY;

        if(application.getOperator().equals(DEFAULT_OPERATOR)) {
            Role role = new Role();
            BeanUtils.copyProperties(application, role);
            roleRepo.save(role);
            response.setResultCode(HttpStatus.OK.value());
            response.setResultMsg(HttpStatus.OK.getReasonPhrase());
        } else {
            response.setResultCode(HttpStatus.METHOD_NOT_ALLOWED.value());
            response.setResultMsg(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
        }
        return response;
    }

    @PostMapping("/removeRole")
    @ResponseBody
    public Response removeRole(@RequestBody ApplicationBean application) {
        Response response = Response.EMPTY;

        if(application.getOperator().equals(DEFAULT_OPERATOR)) {
            String[] ids = application.getRoleId().split(",");
            for(String id : ids) {
                roleRepo.deleteById(id);
            }
            response.setResultCode(HttpStatus.OK.value());
            response.setResultMsg(HttpStatus.OK.getReasonPhrase());
        } else {
            response.setResultCode(HttpStatus.METHOD_NOT_ALLOWED.value());
            response.setResultMsg(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
        }
        return response;
    }

    @GetMapping(value = "/roleList")
    public ResponseEntity<List<Role>> getAllRoles() {
        return new ResponseEntity<>(roleRepo.findAll(), HttpStatus.OK);
    }

    @PostMapping("/updatePermissionRole")
    @ResponseBody
    public Response updatePermissionRole(@RequestBody PermissionRoleBean application) {
        Response response = Response.EMPTY;

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
            response.setResultCode(HttpStatus.OK.value());
            response.setResultMsg(HttpStatus.OK.getReasonPhrase());
        } else {
            response.setResultCode(HttpStatus.METHOD_NOT_ALLOWED.value());
            response.setResultMsg(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
        }
        return response;
    }


    //=====Permission====
    @PostMapping("/addPermission")
    @ResponseBody
    public Response addPermission(@RequestBody ApplicationBean application) {
        Response response = Response.EMPTY;

        if(application.getOperator().equals(DEFAULT_OPERATOR)) {
            Permission permission = new Permission();
            BeanUtils.copyProperties(application, permission);
            permissionRepo.save(permission);
            response.setResultCode(HttpStatus.OK.value());
            response.setResultMsg(HttpStatus.OK.getReasonPhrase());
        } else {
            response.setResultCode(HttpStatus.METHOD_NOT_ALLOWED.value());
            response.setResultMsg(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
        }
        return response;
    }

    @PostMapping("/removePermission")
    @ResponseBody
    public Response removePermission(@RequestBody ApplicationBean application) {
        Response response = Response.EMPTY;

        if(application.getOperator().equals(DEFAULT_OPERATOR)) {
            String[] ids = application.getPermissionId().split(",");
            for(String id : ids) {
                permissionRepo.deleteById(id);
            }
            response.setResultCode(HttpStatus.OK.value());
            response.setResultMsg(HttpStatus.OK.getReasonPhrase());
        } else {
            response.setResultCode(HttpStatus.METHOD_NOT_ALLOWED.value());
            response.setResultMsg(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
        }
        return response;
    }

    @GetMapping(value = "/permissionList")
    public ResponseEntity<List<Permission>> getAllPermissions() {
        return new ResponseEntity<>(permissionRepo.findAll(), HttpStatus.OK);
    }


}
