/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import java.sql.SQLException;

/**
 * This class will be called if a problem (or error) is detected.
 *
 * @author Grupo 3 - Turma 1
 */
public class FetchingProblemException extends SQLException {
    public FetchingProblemException(String s) {
        super(s);
    }
}
