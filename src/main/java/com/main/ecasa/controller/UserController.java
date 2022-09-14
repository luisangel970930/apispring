package com.main.ecasa.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.ecasa.model.Appuser;
import com.main.ecasa.model.Product;
import com.main.ecasa.model.Role;
import com.main.ecasa.service.UserService;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;


import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api/ecasa")
@RequiredArgsConstructor
@Api(value = "UserController", description = "News class with Operations GET, POST, PUT, DELETE")
public class UserController {

    private final UserService userService;


    @ApiOperation(value = "Return list of users", notes = "", response = Appuser.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has been successful. The list of users has been obtained"),
            @ApiResponse(code = 500, message = "The server encountered an unexpected condition that prevented it from fulfilling the request."),
            @ApiResponse(code = 400, message = "The server can not or will not process the request due to something that is perceived as a client error (for example, the syntax of the request)") })

    @GetMapping( path = "/users")
    public ResponseEntity<List<Appuser>>getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());

    }
    @ApiOperation(value = "Adding an user ", notes = "", response = Appuser.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has been successful. Save a user"),
            @ApiResponse(code = 500, message = "The server encountered an unexpected condition that prevented it from fulfilling the request."),
            @ApiResponse(code = 400, message = "The server can not or will not process the request due to something that is perceived as a client error (for example, the syntax of the request)") })

    @PostMapping( path = "/user/save")
    public ResponseEntity<Appuser>saveUser(@RequestBody Appuser appuser){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());

        return ResponseEntity.created(uri).body(userService.saveUser(appuser));

    }
    @ApiOperation(value = "Adding a role", notes = "", response = Role.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has been successful. Save role"),
            @ApiResponse(code = 500, message = "The server encountered an unexpected condition that prevented it from fulfilling the request."),

            @ApiResponse(code = 400, message = "The server can not or will not process the request due to something that is perceived as a client error (for example, the syntax of the request)") })
    @PostMapping( path = "/role/save")
    public ResponseEntity<Role>saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());

        return ResponseEntity.created(uri).body(userService.saveRole(role));

    }

    @ApiOperation(value = "Add role to a user", notes = "",response = RoleToUserForm.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has been successful. Role was added to a user"),
            @ApiResponse(code = 500, message = "The server encountered an unexpected condition that prevented it from fulfilling the request."),

            @ApiResponse(code = 400, message = "The server can not or will not process the request due to something that is perceived as a client error (for example, the syntax of the request)") })
    @PostMapping( path = "/role/addtouser")
    public ResponseEntity<Role>saveRole(@RequestBody RoleToUserForm form){

        userService.addRoleToUser(form.getUsername(),form.getRoleName());
        return ResponseEntity.ok().build();

    }

    @ApiOperation(value = "Return new token and refresh token", notes = "")

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has been successful. The access token and refresh token has been obtained"),
            @ApiResponse(code = 500, message = "The server encountered an unexpected condition that prevented it from fulfilling the request."),

            @ApiResponse(code = 400, message = "The server can not or will not process the request due to something that is perceived as a client error (for example, the syntax of the request)") })
    @GetMapping( path = "/token/refresh")
    public void refreshtoken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String authorizationHeader= request.getHeader(AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){

            try {

                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm =Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT= verifier.verify(refresh_token);
                String username =decodedJWT.getSubject();
                Appuser appuser = userService.getUser(username);

                String access_token = JWT.create()
                        .withSubject(appuser.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis()+10*60*1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles",appuser.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String,String> tokens =new HashMap<>();
                tokens.put("access_token",access_token);
                tokens.put("refresh_token",refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),tokens);

            }catch (Exception exception){

                response.setHeader("error",exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                // response.sendError(FORBIDDEN.value());
                Map<String,String> error =new HashMap<>();
                error.put("error_message",exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),error);
            }

        }else {
           throw new RuntimeException("Refresh token is missing");
        }


    }

}
@Data
class RoleToUserForm{
    private String username;
    private String roleName;

}

