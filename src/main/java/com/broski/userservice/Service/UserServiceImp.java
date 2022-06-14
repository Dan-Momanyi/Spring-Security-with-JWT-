package com.broski.userservice.Service;

import com.broski.userservice.Domain.AppUser;
import com.broski.userservice.Domain.Role;
import com.broski.userservice.Repo.RoleRepo;
import com.broski.userservice.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
@RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImp implements UserService, UserDetailsService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Override
    //bean for user detail service: for loading users
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepo.findByUsername(username);
        if(user == null){
            log.error("User not found in the database 😣");
            throw new UsernameNotFoundException("User not found in the database.");
        } else{
            log.error("User {} found in the database 😋", username);
        }
        //defining authorities
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
    @Override
    public AppUser saveUser(AppUser user) {
        log.info("Saving new user {} into the database", user.getName());
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} into the database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        log.info("Adding new role {} to user {} in the database", roleName, userName);
        AppUser user = userRepo.findByUsername(userName);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);

    }

    @Override
    public AppUser getUser(String userName) {
        log.info("Fetching user {}", userName);
        return userRepo.findByUsername(userName);
    }

    @Override
    public List<AppUser> getUsers() {
        return userRepo.findAll();
    }

}
