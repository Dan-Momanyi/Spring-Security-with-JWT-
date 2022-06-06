package com.broski.userservice.Service;

import com.broski.userservice.Domain.AppUser;
import com.broski.userservice.Domain.Role;
import com.broski.userservice.Repo.RoleRepo;
import com.broski.userservice.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImp implements UserService{

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Override
    public AppUser saveUser(AppUser user) {
        log.info("Saving new user {} into the database", user.getUserName());
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} into the database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleTOUser(String username, String roleName) {
        log.info("Adding new role {} to user {} in the database", roleName, username);
        AppUser user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);

    }

    @Override
    public AppUser getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<AppUser> getUsers() {
        return userRepo.findAll();
    }
}
