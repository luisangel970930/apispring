package com.main.ecasa.service;

import com.main.ecasa.model.Appuser;
import com.main.ecasa.model.Role;
import com.main.ecasa.repository.RoleRepo;
import com.main.ecasa.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService {
private final UserRepo userRepo;
private final RoleRepo roleRepo;

    @Override
    public Appuser saveUser(Appuser appuser) {
        log.info("Saving new user {} to database", appuser.getName());
        return userRepo.save(appuser);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to database",role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {

        log.info("Adding role {} to user {} ",roleName,username);
        Appuser appuser = userRepo.findByUsername(username);
   Role role =roleRepo.findByName(roleName);
    appuser.getRoles().add(role);
    }

    @Override
    public Appuser getUser(String username) {
        log.info("Fetching user {} ",username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<Appuser> getUsers() {
        log.info("Fetching users ");
        return userRepo.findAll();
    }
}
