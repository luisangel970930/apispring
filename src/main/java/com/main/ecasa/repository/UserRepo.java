package com.main.ecasa.repository;

import com.main.ecasa.model.Appuser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Appuser,Long> {


    Appuser findByUsername(String username);


}
