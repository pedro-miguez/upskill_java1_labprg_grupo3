--SQL ProcessoSeriacaoColaborador

CREATE TABLE ProcessoSeriacaoColaborador {
    --dataRealizacao
    idAnuncio INTEGER CHECK (0 <) PRIMARY KEY, FOREIGN KEY,
    idColaborador INTEGER CHECK (0 <) PRIMARY KEY, FOREIGN KEY,
    idOrganizacao INTEGER CHECK (0 <) PRIMARY KEY, FOREIGN KEY
}

--DROP TABLE ProcessoSeriacaoColaborador CASCADE CONSTRAINTS PURGE;


--SQL TipoRegimento

CREATE TABLE TipoRegimento {
    idTipoRegimento INTEGER CHECK (0 <) PRIMARY KEY,
    designacao VARCHAR(500) not null,
    descricaoRegras VARCHAR(1000) not null
}

--DROP TABLE TipoRegimento CASCADE CONSTRAINTS PURGE;


--SQL CompetenciaTecnica

CREATE TABLE CompetenciaTecnica {
    idCompetenciaTecnica INTEGER CHECK (0 <) PRIMARY KEY,
    idAreaAtividade INTEGER CHECK (0 <) FOREIGN KEY,
    nome VARCHAR(50) not null,
    descricaoBreve VARCHAR(100) not null,
    descricaoDetalhada VARCHAR(1000) not null
    --grausProficiencia
}

--DROP TABLE CompetenciaTecnica CASCADE CONSTRAINTS PURGE;


--SQL GrauProficiencia

CREATE TABLE GrauProficiencia {
    idCompetenciaTecnica INTEGER CHECK (0 <) PRIMARY KEY, FOREIGN KEY,
    nivel INTEGER CHECK (0 <) PRIMARY KEY,
    designacao VARCHAR(50) not null
}

--DROP TABLE GrauProficiencia CASCADE CONSTRAINTS PURGE;


--SQL AreaAtividade

CREATE TABLE AreaAtividade {
    idAreaAtividade INTEGER CHECK (0 <) PRIMARY KEY,
    descricaoBreve VARCHAR(100) not null,
    descricaoDetalhada VARCHAR(1000) not null
}

--DROP TABLE AreaAtividade CASCADE CONSTRAINTS PURGE;


--SQL EnderecoPostal

CREATE TABLE EnderecoPostal {
    idOrganizacao INTEGER CHECK (0 <) PRIMARY KEY, FOREIGN KEY,
    morada VARCHAR(100) not null,
    codigoPostal VARCHAR(8) not null CHECK (regex),
    localidade VARCHAR(50) not null
}

--DROP TABLE EnderecoPostal CASCADE CONSTRAINTS PURGE;


ALTER TABLE ProcessoSeriacaoColaborador
    ADD CONSTRAINTS fkProcessoSeriacaoColaboradoridAnuncio FOREIGN KEY (idAnuncio)
        REFERENCES Anuncio (idAnuncio);

ALTER TABLE ProcessoSeriacaoColaborador
    ADD CONSTRAINTS fkProcessoSeriacaoColaboradoridColaborador FOREIGN KEY (idColaborador)
        REFERENCES Colaborador (idColaborador);

ALTER TABLE ProcessoSeriacaoColaborador
    ADD CONSTRAINTS fkProcessoSeriacaoColaboradoridOrganizacao FOREIGN KEY (idOrganizacao)
        REFERENCES Organizacao (idOrganizacao);


--ALTER TABLE TipoRegimento


ALTER TABLE CompetenciaTecnica
    ADD CONSTRAINTS fkCompetenciaTecnicaidAreaAtividade FOREIGN KEY (idAreaAtividade)
        REFERENCES AreaAtividade (idAreaAtividade);


ALTER TABLE GrauProficiencia
    ADD CONSTRAINTS fkGrauProficienciaidCompetenciaTecnica FOREIGN KEY (idCompetenciaTecnica)
        REFERENCES CompetenciaTecnica (idCompetenciaTecnica);


--ALTER TABLE AreaAtividade


ALTER TABLE EnderecoPostal
    ADD CONSTRAINTS fkEnderecoPostalidOrganizacao FOREIGN KEY (idOrganizacao)
        REFERENCES Organizacao (idOrganizacao);
