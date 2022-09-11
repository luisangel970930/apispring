package com.main.ecasa.controller;

import com.main.ecasa.model.Appuser;
import com.main.ecasa.model.Role;
import com.main.ecasa.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/ecasa")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping( path = "/users")
    public ResponseEntity<List<Appuser>>getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());

    }
    @PostMapping( path = "/user/save")
    public ResponseEntity<Appuser>saveUser(@RequestBody Appuser appuser){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());

        return ResponseEntity.created(uri).body(userService.saveUser(appuser));

    }
    @PostMapping( path = "/role/save")
    public ResponseEntity<Role>saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());

        return ResponseEntity.created(uri).body(userService.saveRole(role));

    }
    @PostMapping( path = "/role/addtouser")
    public ResponseEntity<Role>saveRole(@RequestBody RoleToUserForm form){

        userService.addRoleToUser(form.getUsername(),form.getRoleName());
        return ResponseEntity.ok().build();

    }

}
@Data
class RoleToUserForm{
    private String username;
    private String roleName;

}
