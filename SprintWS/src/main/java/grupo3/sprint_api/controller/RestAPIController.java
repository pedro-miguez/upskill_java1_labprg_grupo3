package grupo3.sprint_api.controller;

import grupo3.sprint_api.domain.Context;
import grupo3.sprint_api.domain.Email;
import grupo3.sprint_api.dto.*;
import grupo3.sprint_api.service.AuthenticationService;
import grupo3.sprint_api.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestAPIController {

    @RequestMapping(value = "/test",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> test() {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setDescricao("desc");
        roleDTO.setDesignacao("design");
        return new ResponseEntity<>(roleDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/context",
            method = RequestMethod.GET,
            params = { "app_key"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getContext(@RequestParam("app_key") String appKey) {
        try {

            ContextDTO contextDTO = AuthenticationService.generateContext(appKey);
            if (contextDTO != null) {
                return new ResponseEntity<>(contextDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/roles",
            method = RequestMethod.GET,
            params = { "app_context"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> userRoles(@RequestParam("app_context") String appContext) {
        try {
            ContextDTO contextDTO = new ContextDTO();
            contextDTO.setAppContext(appContext);

            if (!AuthenticationService.validateContext(contextDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            ListaRoleDTO listaRoleDTO = UsersService.getRoles();
            if (listaRoleDTO != null) {
                return new ResponseEntity<>(listaRoleDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/userRoles",
            method = RequestMethod.GET,
            params = { "app_context", "user_id"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserRoles(@RequestParam("app_context") String appContext,
                                            @RequestParam("user_id") String username) {

        try {

            ContextDTO contextDTO = new ContextDTO();
            contextDTO.setAppContext(appContext);

            if (!AuthenticationService.validateContext(contextDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            RoleDTO roleDTO = UsersService.getUserRoles(username);
            if (roleDTO != null) {
                return new ResponseEntity<>(roleDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/userRoles",
            method = RequestMethod.POST,
            params = { "app_context", "user_id", "rolenames"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addRoleToUser(@RequestParam("app_context") String appContext,
                                                @RequestParam("user_id") String username,
                                                @RequestParam("rolenames") String designacao) {
        try {

            ContextDTO contextDTO = new ContextDTO();
            contextDTO.setAppContext(appContext);

            if (!AuthenticationService.validateContext(contextDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            UserDTO userDTO = new UserDTO();
            userDTO.setUserName(username);

            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setDesignacao(designacao);

            UsersService.addRoleToUser(userDTO, roleDTO);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/roles",
            method = RequestMethod.POST,
            params = { "app_context", "rolename", "description"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUserRole(@RequestParam("app_context") String appContext,
                                            @RequestParam("description") String description,
                                            @RequestParam("rolename") String designacao) {
        try {

            ContextDTO contextDTO = new ContextDTO();
            contextDTO.setAppContext(appContext);

            if (!AuthenticationService.validateContext(contextDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setDescricao(description);
            roleDTO.setDesignacao(designacao);

            UsersService.createUserRole(roleDTO);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/roles",
            method = RequestMethod.DELETE,
            params = { "app_context", "rolename"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUserRole(@RequestParam("app_context") String appContext,
                                            @RequestParam("rolename") String rolename) {
        try {

            ContextDTO contextDTO = new ContextDTO();
            contextDTO.setAppContext(appContext);

            if (!AuthenticationService.validateContext(contextDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setDesignacao(rolename);

            UsersService.deleteUserRole(roleDTO);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/userRoles",
            method = RequestMethod.DELETE,
            params = { "app_context", "user_id", "rolenames"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteRoleFromUser(@RequestParam("app_context") String appContext,
                                            @RequestParam("user_id") String username,
                                            @RequestParam("rolenames") String rolename) {
        try {

            ContextDTO contextDTO = new ContextDTO();
            contextDTO.setAppContext(appContext);

            if (!AuthenticationService.validateContext(contextDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            UserDTO userDTO = new UserDTO();
            userDTO.setUserName(username);
            userDTO.setRole(rolename);

            UsersService.deleteRoleFromUser(userDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/registerUser",
            method = RequestMethod.POST,
            params = { "app_context", "username", "email", "password" },
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(@RequestParam("app_context") String appContext,
                                               @RequestParam("username") String username,
                                               @RequestParam("email") String email,
                                               @RequestParam("password") String password) {
        try {

            ContextDTO contextDTO = new ContextDTO();
            contextDTO.setAppContext(appContext);

            if (!AuthenticationService.validateContext(contextDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            UserDTO userDTO = new UserDTO();
            userDTO.setUserName(username);
            userDTO.setEmail(email);
            userDTO.setPassword(password);

            UsersService.registerUser(userDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/registerUserWithRoles",
            method = RequestMethod.POST,
            params = { "app_context", "username", "email", "password", "rolenames" },
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUserWithRoles(@RequestParam("app_context") String appContext,
                                               @RequestParam("username") String username,
                                               @RequestParam("email") String email,
                                               @RequestParam("password") String password,
                                               @RequestParam("rolenames") String rolename) {
        try {

            ContextDTO contextDTO = new ContextDTO();
            contextDTO.setAppContext(appContext);

            if (!AuthenticationService.validateContext(contextDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            UserDTO userDTO = new UserDTO();
            userDTO.setRole(rolename);
            userDTO.setPassword(password);
            userDTO.setUserName(username);
            userDTO.setRole(rolename);

            UsersService.registerUserWithRoles(userDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            params = { "app_context", "user_id", "password" },
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestParam("app_context") String appContext,
                                               @RequestParam("user_id") String username,
                                               @RequestParam("password") String password) {
        try {
            ContextDTO contextDTO = new ContextDTO();
            contextDTO.setAppContext(appContext);

            if (!AuthenticationService.validateContext(contextDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            LoginDTO loginDTO = new LoginDTO();
            loginDTO.setUsername(username);
            loginDTO.setPassword(password);

            if (AuthenticationService.login(loginDTO, contextDTO)) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/logout",
            method = RequestMethod.POST,
            params = { "app_context" },
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> logout(@RequestParam("app_context") String appContext) {
        try {
            ContextDTO contextDTO = new ContextDTO();
            contextDTO.setAppContext(appContext);

            if (!AuthenticationService.validateContext(contextDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            if (AuthenticationService.logout(contextDTO)) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
