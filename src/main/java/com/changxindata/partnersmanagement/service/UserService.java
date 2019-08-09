package com.changxindata.partnersmanagement.service;

import com.changxindata.partnersmanagement.common.ApplicationBean;
import com.changxindata.partnersmanagement.domain.system.User;
import com.changxindata.partnersmanagement.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final static String SORT_BY_DATE_CREATED = "dateCreated";

    @Autowired
    private UserRepository userRepo;

    public User checkUser(String username, String password) {
        User user = new User();
        Optional opt = userRepo.findUserByUsernameAndPassword(username, password);
        if(opt.isPresent()) {
            user = (User)opt.get();
        }
        return user;
    }

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
    public boolean updateUserProperty(ApplicationBean application) {
        String userId = application.getUserId();
        Optional opt = userRepo.findById(userId);
        if(opt.isPresent()) {
            User user = new User();
            BeanUtils.copyProperties(application, user);
            userRepo.deleteById(userId);
            userRepo.save(user);
            return true;
        } else {
            return false;
        }
    }


}
