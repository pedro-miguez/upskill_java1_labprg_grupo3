package grupo3.sprint_api.controller;

import grupo3.sprint_api.dto.*;
import grupo3.sprint_api.service.AuthenticationService;
import grupo3.sprint_api.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class which manage the HTTP requests. They receive HTTP requests from
 * , they forward the request to service classes, receive the response from services
 * classes, then build the HTTP response and send to client.
 */
@RestController
public class RestAPIController {

    /**
     * It allows to obtain the data of the active session.
     *
     * @param appContext
     * @return ResponseEntity
     */
    @RequestMapping(value = "/session",
            method = RequestMethod.GET,
            params = { "app_context"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSession(@RequestParam("app_context") String appContext) {

        try {
            ContextDTO contextDTO = new ContextDTO();
            contextDTO.setAppContext(appContext);

            if (!AuthenticationService.validateContext(contextDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            SessionDTO sessionDTO = AuthenticationService.getSession(contextDTO);

            if (sessionDTO != null) {
                return new ResponseEntity<>(sessionDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Allows you to obtain a context key that identifies a user session.
     * This context key will be needed to run all of the remaining methods available.
     * This key is valid for a period of time (in milliseconds) that is returned
     * by the API after obtaining the context, until it is a login or user registration
     * is performed. If the login or registration is not carried out user within the
     * specified period of time, a new context key must be obtained.
     *
     * @param appKey
     * @return ResponseEntity
     */
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

    /**
     * Allows you to obtain all the roles / functions existing in the system.
     *
     * @param appContext
     * @return ResponseEntity
     */
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


    /**
     * Allows you to get the list of roles / functions associated with a given user.
     *
     * @param appContext
     * @param username
     * @return ResponseEntity
     */
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


    /**
     * Allows associate roles / functions to a given user.
     *
     * @param appContext
     * @param username
     * @param designacao
     * @return ResponseEntity
     */
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


            UsersService.addRoleToUser(username, designacao);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Allows you to define the existence of a new role / function of interest for the application.
     *
     * @param appContext
     * @param description
     * @param designacao
     * @return ResponseEntity
     */
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
            roleDTO.setRolenames(designacao);

            UsersService.createUserRole(roleDTO);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Allows you to eliminate the role / function existing in the system only if it does not have associated users.
     *
     * @param appContext
     * @param rolename
     * @return ResponseEntity
     */
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


            UsersService.deleteUserRole(rolename);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Allows eliminate the association of roles / functions to a given user.
     *
     * @param appContext
     * @param username
     * @param rolename
     * @return ResponseEntity
     */
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


            UsersService.deleteRoleFromUser(username, rolename);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * It allows defining the existence of a new user who can use the application.
     *
     * @param appContext
     * @param username
     * @param email
     * @param password
     * @return ResponseEntity
     */
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
            userDTO.setUsername(username.replace("!", " "));
            userDTO.setEmail(email);
            userDTO.setPassword(password);

            UsersService.registerUser(userDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Similar to the previous method, with the advantage
     * to immediately associate a role to that user.
     *
     * @param appContext
     * @param username
     * @param email
     * @param password
     * @param rolename
     * @return ResponseEntity
     */
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
            userDTO.setEmail(email);
            userDTO.setPassword(password);
            userDTO.setUsername(username.replace("!", " "));


            UsersService.registerUserWithRoles(userDTO, rolename);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Allows you to authenticate a user with a view to using the application.
     * The user_id parameter can be the username or the user's email.
     *
     * @param appContext
     * @param username
     * @param password
     * @return ResponseEntity
     */
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
            loginDTO.setUsername(username.replace("!", " "));
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


    /**
     * End the user session that is active on the time.
     *
     * @param appContext
     * @return ResponseEntity
     */
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
