package com.main.ecasa.repository;

import com.main.ecasa.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {

    Role findByName(String name);


}
