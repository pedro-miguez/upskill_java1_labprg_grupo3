/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.controller;

import com.company.dto.ErroDTO;
import com.company.dto.ColaboradorDTO;
import com.company.dto.ListaColaboradorDTO;
import com.company.service.ColaboradoresService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

/**
 *
 * @author Grupo 3
 */
public class ColaboradoresController {
    
    @RequestMapping(value = "/colaboradores",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Object> getColaboradores() {
        try {
            ListaColaboradorDTO listaColaboradorDTO = ColaboradoresService.getColaboradores();
            if (listaColaboradorDTO != null) {
                return new ResponseEntity<>(listaColaboradorDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/colaboradores/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Object> getColaborador(@PathVariable("id") int nr) {
        try {
            ColaboradorDTO colaboradorDTO = ColaboradoresService.getColaborador(nr);
            if (colaboradorDTO != null) {
                return new ResponseEntity<>(colaboradorDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/colaboradores",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Object> addColaborador(@RequestBody ColaboradorDTO colaboradorDTO) {
        try {
            ColaboradoresService.addColaborador(colaboradorDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/colaboradores/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Object> updateColaborador(@PathVariable("id") int nr, @RequestBody ColaboradorDTO colaboradorDTO) {
        try {
            ColaboradoresService.updateColaborador(nr, colaboradorDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/colaboradores/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Object> removeColaborador(@PathVariable("id") int nr) {
        try {
            ColaboradoresService.removeColaborador(nr);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.CONFLICT);
        }
    }
    
}
