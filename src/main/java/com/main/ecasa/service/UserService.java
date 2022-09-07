package com.main.ecasa.service;

import com.main.ecasa.model.Appuser;
import com.main.ecasa.model.Role;

import java.util.List;

public interface UserService {
    Appuser saveUser (Appuser appuser);
    Role saveRole (Role role);
    void addRoleToUser (String username,String roleName);
    Appuser getUser (String username);
    List<Appuser> getUsers();
}
