package com.broski.userservice.Service;

import com.broski.userservice.Domain.AppUser;
import com.broski.userservice.Domain.Role;

import java.util.List;

public interface UserService {
    //methods that will manage the users
    AppUser saveUser(AppUser user);
    Role saveRole(Role role);
    void addRoleTOUser(String username, String roleName);
    AppUser getUser(String username);
    List<AppUser> getUsers();
}
