
alter table CaraterizacaoCompetenciaTecnica
add constraint fk_CaraterizacaoCompetenciaTecnica_idCategoria FOREIGN KEY (idCategoria) references CategoriaTarefa (idCategoria); 


alter table Tarefa
add constraint fk_Tarefa_idCategoria FOREIGN KEY (idCategoria) references CategoriaTarefa (idCategoria);  


alter table Tarefa
add constraint fk_Tarefa_idOrganizacao FOREIGN KEY (idOrganizacao) references Organizacao (idOrganizacao);

alter table Tarefa
add constraint fk_Tarefa_idEstadoTarefa FOREIGN KEY (idEstadoTarefa) references EstadoTarefa (idEstadoTarefa);

alter table Anuncio
add constraint fk_Anuncio_idTarefa FOREIGN KEY (idTarefa) references Tarefa (idTarefa);

alter table Candidatura
add constraint fk_Candidatura_idAnuncio FOREIGN KEY (idAnuncio) references Anuncio (idAnuncio);

alter table Candidatura
add constraint fk_Candidatura_idFreelancer FOREIGN KEY (idFreelancer) references Freelancer (idFreelancer);

alter table Classificacao
add constraint fk_Classificacao_idAnuncio FOREIGN KEY (idAnuncio) references Anuncio (idAnuncio);

alter table Classificacao
add constraint fk_Classificacao_idFreelancer FOREIGN KEY (idFreelancer) references Freelancer (idFreelancer);

alter table ProcessoSeriacao
add constraint fk_ProcessoSeriacao_idAnuncio FOREIGN KEY (idAnuncio) references Anuncio (idAnuncio);

alter table ProcessoSeriacao
add constraint fk_ProcessoSeriacao_idTipoRegimento FOREIGN KEY (idTipoRegimento) references TipoRegimento (idTipoRegimento);

ALTER TABLE ProcessoSeriacaoColaborador
    ADD CONSTRAINTS fkProcessoSeriacaoColaboradoridAnuncio FOREIGN KEY (idAnuncio)
        REFERENCES Anuncio (idAnuncio);

ALTER TABLE ProcessoSeriacaoColaborador
    ADD CONSTRAINTS fkProcessoSeriacaoColaboradoridColaborador FOREIGN KEY (idColaborador)
        REFERENCES Colaborador (idColaborador);

ALTER TABLE ProcessoSeriacaoColaborador
    ADD CONSTRAINTS fkProcessoSeriacaoColaboradoridOrganizacao FOREIGN KEY (idOrganizacao)
        REFERENCES Organizacao (idOrganizacao);

ALTER TABLE CompetenciaTecnica
    ADD CONSTRAINTS fkCompetenciaTecnicaidAreaAtividade FOREIGN KEY (idAreaAtividade)
        REFERENCES AreaAtividade (idAreaAtividade);


ALTER TABLE GrauProficiencia
    ADD CONSTRAINTS fkGrauProficienciaidCompetenciaTecnica FOREIGN KEY (idCompetenciaTecnica)
        REFERENCES CompetenciaTecnica (idCompetenciaTecnica);

ALTER TABLE EnderecoPostal
    ADD CONSTRAINTS fkEnderecoPostalidOrganizacao FOREIGN KEY (idOrganizacao)
        REFERENCES Organizacao (idOrganizacao);

alter table Organizacao
add constraint fkOrganizacaoIdGestor FOREIGN KEY (idGestor) references Utilizador (idUtilizador);

alter table Administrativo
add constraint fkAdministrativoIdUtilizador FOREIGN KEY (idUtilizador) references Utilizador (idUtilizador);

alter table Colaborador
add constraint fkColaboradorIdColaborador FOREIGN KEY (idColaborador) references Utilizador (idUtilizador);

alter table Colaborador
add constraint fkColaboradorIdOrganizacao FOREIGN KEY (idOrganizacao) references Organizacao (idOrganizacao);

alter table HabilitacaoAcademica
add constraint fkHabilitacaoAcademicaIdFreelancer FOREIGN KEY (idFreelancer) references Freelancer (idFreelancer);

alter table CaraterizacaoCompetenciaTecnica
add constraint fk_CaraterizacaoCompetenciaTecnica_idCompetenciaTecnica FOREIGN KEY (idCompetenciaTecnica) references CompetenciaTecnica (idCompetenciaTecnica);




alter table CategoriaTarefa
add constraint fk_CategoriaTarefa_idAreaAtividade FOREIGN KEY (idAreaAtividade) references AreaAtividade (idAreaAtividade);


alter table Utilizador
add constraint fk_Utilizador_designacao FOREIGN KEY (designacao) references Role (designacao);


alter table ReconhecimentoCT
add constraint fk_ReconhecimentoCT_idCompetenciatecnica FOREIGN KEY (idCompetenciatecnica) references CompetenciaTecnica (idCompetenciatecnica);




alter table ReconhecimentoCT
add constraint fk_ReconhecimentoCT_idCompetenciatecnica_nivelGrauProficiencia FOREIGN KEY (idCompetenciatecnica, nivelGrauProficiencia) references GrauProficiencia (idCompetenciatecnica, nivel);


alter table ReconhecimentoCT
add constraint fk_ReconhecimentoCT_idFreelancer FOREIGN KEY (idFreelancer) references Freelancer (idFreelancer);


alter table CategoriaTarefa
add constraint uk_CategoriaTarefa_descricao_idAreaAtividade UNIQUE (descricao, idAreaAtividade);


alter table Anuncio
add constraint fk_Anuncio_idTipoRegimento FOREIGN KEY (idTipoRegimento) references TipoRegimento (idTipoRegimento);

alter table Anuncio
add constraint fk_Anuncio_idEstadoAnuncio FOREIGN KEY (idEstadoAnuncio) references EstadoAnuncio (idEstadoAnuncio);