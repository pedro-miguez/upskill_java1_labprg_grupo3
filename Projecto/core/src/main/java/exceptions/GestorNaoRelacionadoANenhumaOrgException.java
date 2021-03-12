/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 * This class will be called if any manager is not associated with any organization.
 *
 * @author Grupo 3 - Turma 1
 */
public class GestorNaoRelacionadoANenhumaOrgException extends IllegalArgumentException {
    public GestorNaoRelacionadoANenhumaOrgException(String s) {
        super(s);
    }
}
