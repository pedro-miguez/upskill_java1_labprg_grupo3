/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.sprint_api.exception;

/**
 *
 * @author Grupo 3 - Turma 1
 */
public class ContextNaoAssociadoException extends IllegalArgumentException {

    public ContextNaoAssociadoException(String s) {
        super(s);
    }
}
