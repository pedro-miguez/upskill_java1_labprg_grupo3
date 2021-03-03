/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.repo;

import com.company.model.Plataforma;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;

/**
 *
 * @author Anibal
 */
public class Dados {
    
    static final String PLATAFORMA_FILE = "plataforma_dados.dat";

    public static Plataforma carregarDados() {
        Plataforma plataforma = new Plataforma("Tasks 4 Joe");
        Path file = Paths.get(PLATAFORMA_FILE);
        try {
            ObjectInputStream o = new ObjectInputStream(new FileInputStream(file.toString()));
            plataforma = (Plataforma) o.readObject();
            o.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return plataforma;
    }

    public static void guardarDados(Plataforma plataforma) {
        Path file = Paths.get(PLATAFORMA_FILE);
        try {
            ObjectOutputStream o = new ObjectOutputStream(Files.newOutputStream(file, CREATE));
            o.writeObject(plataforma);
            o.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
