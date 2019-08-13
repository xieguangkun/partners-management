package com.changxindata.partnersmanagement.service;

import com.changxindata.partnersmanagement.common.ApplicationBean;
import com.changxindata.partnersmanagement.domain.system.Permission;
import com.changxindata.partnersmanagement.domain.system.PermissionRole;
import com.changxindata.partnersmanagement.domain.system.User;
import com.changxindata.partnersmanagement.repository.PermissionRepository;
import com.changxindata.partnersmanagement.repository.PermissionRoleRepository;
import com.changxindata.partnersmanagement.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final static String SORT_BY_DATE_CREATED = "dateCreated";

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PermissionRoleRepository prRepo;

    @Autowired
    private PermissionRepository permissionRepo;

    /**
     *  获取单个用户
     * @param id
     * @return
     */
    public User getUser(String id) {
        User result = null;
        Optional opt = userRepo.findById(id);
        if(opt.isPresent()) {
            result = (User)opt.get();
        }
        return result;
    }

    /**
     * 获取所有用户
     * @param start
     * @param size
     * @return
     */
    public Page<User> getAllUsers(int start, int size) {
        Pageable pageable = PageRequest.of(
                start,
                size,
                new Sort(Sort.Direction.DESC, SORT_BY_DATE_CREATED)
        );
        return userRepo.findAll(pageable);
    }

    /**
     * 更新用户属性
     * @param application
     * @return
     */
    public boolean updateUserProperty(User application) {
        String userId = application.getId();
        Optional opt = userRepo.findById(userId);
        if(opt.isPresent()) {
            userRepo.updateUserInfo(application);
            return true;
        } else {
            return false;
        }
    }

    public List<String> getAllPermission(String roleId) {
        List<PermissionRole> prList =  prRepo.findAllByRoleId(roleId);
        List<String> permissionList = new ArrayList<>();
        for(PermissionRole pr : prList) {
            Optional optional = permissionRepo.findById(pr.getPermissionId());
            if(optional.isPresent()) {
                Permission permission = (Permission)optional.get();
                permissionList.add(permission.getMask());
            }
        }
        return permissionList;
    }


}
