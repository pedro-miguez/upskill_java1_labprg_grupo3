/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.sprint_api.exception;

/**
 * This class is called whenever a name is not being associated.
 *
 * @author Grupo 3 - Turma 1
 */
public class NomeNaoAssociadoException extends IllegalArgumentException {
    public NomeNaoAssociadoException(String s) {
        super(s);
    }
}
