package com.main.ecasa.service;

import com.main.ecasa.model.Appuser;
import com.main.ecasa.model.Role;
import com.main.ecasa.repository.RoleRepo;
import com.main.ecasa.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
private final UserRepo userRepo;
private final RoleRepo roleRepo;

private final PasswordEncoder passwordEncoder;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
Appuser appuser =userRepo.findByUsername(username);
if (appuser==null){

    log.error("User not found in the database:{}",username);

}else {
    log.info("User found in the database:{}",username);
}
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    appuser.getRoles().forEach(role -> {authorities.add(new SimpleGrantedAuthority(role.getName()));

    });

return  new org.springframework.security.core.userdetails.User(appuser.getUsername(),appuser.getPassword(),authorities);
    }


    @Override
    public Appuser saveUser(Appuser appuser) {
        log.info("Saving new user {} to database", appuser.getName());

        appuser.setPassword(passwordEncoder.encode(appuser.getPassword()));

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
