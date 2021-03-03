package grupo3.sprint_api.controller;

import grupo3.sprint_api.dto.ErroDTO;
import grupo3.sprint_api.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersController {

    @RequestMapping(value = "/roles",
            method = RequestMethod.GET,
            params = { "app_context"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> userRoles(@RequestParam("app_context") String appContext) {
        try {
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
    public ResponseEntity<Object> userRoles(@RequestParam("app_context") String appContext,
                                            @RequestParam("user_id") String username) {
        try {
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
    public ResponseEntity<Object> userRoles(@RequestParam("app_context") String appContext,
                                            @RequestParam("user_id") String username,
                                            @RequestParam("rolenames") String rolename) {
        try {
            UsersService.postUserRoles(username, rolename);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/roles",
            method = RequestMethod.POST,
            params = { "app_context", "rolename", "description"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> userRoles(@RequestParam("app_context") String appContext,
                                            @RequestParam("description") String description,
                                            @RequestParam("rolename") String rolename) {
        try {
            UsersService.postRoles(rolename, description);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/roles",
            method = RequestMethod.DELETE,
            params = { "app_context", "rolename"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> userRoles(@RequestParam("app_context") String appContext,
                                            @RequestParam("rolename") String rolename) {
        try {
            UsersService.deleteRoles(rolename);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/userRoles",
            method = RequestMethod.DELETE,
            params = { "app_context", "user_id", "rolenames"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> userRoles(@RequestParam("app_context") String appContext,
                                            @RequestParam("user_id") String username,
                                            @RequestParam("rolenames") String rolename) {
        try {
            UsersService.deleteUserRoles(username, rolename);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/registerUser",
            method = RequestMethod.POST,
            params = { "app_context", "username", "email", "password" },
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registerUser(@RequestParam("app_context") String appContext,
                                               @RequestParam("username") String username,
                                               @RequestParam("email") String email,
                                               @RequestParam("password") String password) {
        try {
            UsersService.registerUser(username, email, password);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/registerUserWithRoles",
            method = RequestMethod.POST,
            params = { "app_context", "username", "email", "password", "rolenames" },
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registerUserWithRoles(@RequestParam("app_context") String appContext,
                                               @RequestParam("username") String username,
                                               @RequestParam("email") String email,
                                               @RequestParam("password") String password,
                                               @RequestParam("rolenames") String rolename) {
        try {
            UsersService.registerUserWithRoles(username, email, password, rolename);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            params = { "app_context", "user_id", "password" },
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@RequestParam("app_context") String appContext,
                                               @RequestParam("user_id") String username,
                                               @RequestParam("password") String password) {
        try {
            if (UsersService.login(username, password)) {
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
    public ResponseEntity<Object> logout(@RequestParam("app_context") String appContext) {
        try {
            if (UsersService.logout()) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
