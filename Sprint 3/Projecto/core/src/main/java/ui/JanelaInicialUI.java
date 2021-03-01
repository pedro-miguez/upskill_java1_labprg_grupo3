package ui;

import domain.Plataforma;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class JanelaInicialUI {


    public Button btnAbout;

    public void registarOrganizacaoAction(ActionEvent actionEvent) {
        MainApp.screenController.activate("RegistarOrganizacao");
    }

    public void loginAction(ActionEvent actionEvent) {
        MainApp.screenController.activate("Login");
    }

    public void aboutAction(ActionEvent actionEvent) {
        AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Tasks 4 Joe - About",
                "A startup Tasks for Joe (T4J) dedica-se a facilitar e promover o contacto entre pessoas que " +
                        "trabalham por conta própria (freelancers) e organizações que pretendem contratar alguém " +
                        "externo (outsourcing) para a realização de determinadas tarefas. Para promover e suportar o " +
                        "seu negócio, a T4J pretende desenvolver uma plataforma informática que, por um lado, " +
                        "permita que qualquer organização interessada possa registar-se na plataforma de forma a " +
                        "poder publicar tarefas e gerirem elas próprias o processo de adjudicação dessas tarefas a " +
                        "freelancers; e por outro lado, permita que os freelancers acedam facilmente a essas tarefas e " +
                        "possam candidatar-se à realização das mesmas.\n" +
                        "\nEsta aplicação foi desenvolvida pelo Grupo 3 da Turma JAVA 1 do programa Upskill. \n" +
                        "Aníbal Farraia, Júlio Sá, Pedro Miguez, Vitor Luís").show();
    }
}
