/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;


/**
 * Representa uma exceção para ser lançada quando uma aplicação tenta 
 * criar/modificar uma instância de Data para representar um dia cujo mês é 
 * inválido.
 *
 * @author Grupo 3
 */
public class MesInvalidoException extends IllegalArgumentException {
    
    /**
      * Constrói uma MesInvalidoException com a mensagem "Mês é inválido!".
     */    
    public MesInvalidoException() {
        super("Mês é inválido!!");
    }
    
    /**
     * Constrói uma MesInvalidoException contendo a mensagem recebida.
     * 
     * @param mensagem a mensagem transmitida pela exceção
     */
    public MesInvalidoException(String mensagem) {
        super(mensagem);
    }
    
}
