package com.zdf.taskmanager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdf.taskmanager.model.User;
import com.zdf.taskmanager.payload.response.UserSearchResponse;
import com.zdf.taskmanager.repository.UserRepository;
import com.zdf.taskmanager.util.CommonUtil;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return UserDetailsImpl.build(user);
    }

    public List<UserSearchResponse> getUserDetails(String searchStr) {
        if (StringUtils.isNotBlank(searchStr)) {
            List<User> userList = userRepository.findByUserNameOrEmployeeId(searchStr);
            if (CommonUtil.isNotEmpty(userList)) {
                return userList.stream()
                        .map(user -> new UserSearchResponse(user.getEmployeeId(),
                                user.getFirstName() + user.getLastName(), user.getUsername()))
                        .collect(Collectors.toList());
            }
        }
        return null;
    }

}
