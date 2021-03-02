/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.controller;

import com.company.dto.ErroDTO;
import com.company.dto.OrganizacaoDTO;
import com.company.dto.ListaOrganizacaoDTO;
import com.company.service.OrganizacoesService;
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
public class OrganizacoesController {
    
    @RequestMapping(value = "/organizacoes",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Object> getOrganizacoes() {
        try {
            ListaOrganizacaoDTO listaOrganizacaoDTO = OrganizacoesService.getOrganizacoes();
            if (listaOrganizacaoDTO != null) {
                return new ResponseEntity<>(listaOrganizacaoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/freguesias/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Object> getFreguesia(@PathVariable("id") String nome) {
        try {
            FreguesiaDTO freguesiaDTO = FreguesiasService.getFreguesia(nome);
            if (freguesiaDTO != null) {
                return new ResponseEntity<>(freguesiaDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/freguesias",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Object> addFreguesia(@RequestBody FreguesiaDTO freguesiaDTO) {
        try {
            FreguesiasService.addFreguesia(freguesiaDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/freguesias/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Object> updateFreguesia(@PathVariable("id") String nome, @RequestBody FreguesiaDTO freguesiaDTO) {
        try {
            FreguesiasService.updateFreguesia(nome, freguesiaDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/freguesias/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Object> removeFreguesia(@PathVariable("id") String nome) {
        try {
            FreguesiasService.removeFreguesia(nome);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.CONFLICT);
        }
    }
    
}
