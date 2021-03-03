package grupo3.sprint_api.controller;

import grupo3.sprint_api.dto.ErroDTO;
import grupo3.sprint_api.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersController {

    @RequestMapping(value = "/pessoas",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getPessoas() {
        try {
            ListaPessoaDTO listaPessoaDTO = PessoasService.getPessoas();
            if (listaPessoaDTO.getPessoas().size() > 0) {
                return new ResponseEntity<>(listaPessoaDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/pessoas/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getPessoa(@PathVariable("id") long nif) {
        try {
            PessoaDTO pessoaDTO = PessoasService.getPessoa(nif);
            if (pessoaDTO != null) {
                return new ResponseEntity<>(pessoaDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.CONFLICT);
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
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.CONFLICT);
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
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.CONFLICT);
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
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.CONFLICT);
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
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/pessoas/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updatePessoa(@PathVariable("id") long nif, @RequestBody PessoaDTO pessoaDTO
    ) {
        try {
            PessoasService.updatePessoa(nif, pessoaDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/pessoas/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Object> removePessoa(@PathVariable("id") long nif) {
        try {
            PessoasService.removePessoa(nif);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.CONFLICT);
        }
    }
}
