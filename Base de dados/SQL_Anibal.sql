--SQL ProcessoSeriacaoColaborador

CREATE TABLE ProcessoSeriacaoColaborador (
    --dataRealizacao
    idAnuncio INTEGER
        CONSTRAINT ckProcessoSeriacaoColaboradorIdAnuncio CHECK (idAnuncio > 0),

    idColaborador INTEGER 
        CONSTRAINT ckProcessoSeriacaoColaboradorIdColaborador CHECK (idColaborador > 0),

    idOrganizacao INTEGER 
        CONSTRAINT ckProcessoSeriacaoColaboradorIdOrganizacao CHECK (idOrganizacao > 0),

    CONSTRAINT pkProcessoSeriacaoColaboradorIdAnuncioIdColaboradorIdOrganizacao PRIMARY KEY (idAnuncio, idColaborador, idOrganizacao)
);

--DROP TABLE ProcessoSeriacaoColaborador CASCADE CONSTRAINTS PURGE;


--SQL TipoRegimento

CREATE TABLE TipoRegimento (
    idTipoRegimento INTEGER PRIMARY KEY
        CONSTRAINT ckTipoRegimentoIdTipoRegimento CHECK (idTipoRegimento > 0),

    designacao VARCHAR(500) not null
        CONSTRAINT nnTipoRegimentoDesignacao not null

    descricaoRegras VARCHAR(1000) not null
        CONSTRAINT nnTipoRegimentoDescricaoRegras not null
);

--DROP TABLE TipoRegimento CASCADE CONSTRAINTS PURGE;


--SQL CompetenciaTecnica

CREATE TABLE CompetenciaTecnica (
    idCompetenciaTecnica INTEGER PRIMARY KEY
        CONSTRAINT ckCompetenciaTecnicaIdCompetenciaTecnica CHECK (idCompetenciaTecnica > 0),

    idAreaAtividade INTEGER CHECK (0 <) FOREIGN KEY,

    nome VARCHAR(50) not null
        CONSTRAINT nnCompetenciaTecnicaNome not null,

    descricaoBreve VARCHAR(100) not null
        CONSTRAINT nnCompetenciaTecnicaDescricaoBreve not null,

    descricaoDetalhada VARCHAR(1000) not null
        CONSTRAINT nnCompetenciaTecnicaDescricaoDetalhada not null

    --grausProficiencia
);

--DROP TABLE CompetenciaTecnica CASCADE CONSTRAINTS PURGE;


--SQL GrauProficiencia

CREATE TABLE GrauProficiencia (
    idCompetenciaTecnica INTEGER PRIMARY KEY, FOREIGN KEY,
        CONSTRAINT ckGrauProficienciaIdCompetenciaTecnica CHECK (idCompetenciaTecnica > 0),

    nivel INTEGER CHECK (0 <) PRIMARY KEY,
        CONSTRAINT ckGrauProficienciaNivel CHECK (nivel > 0),

    designacao VARCHAR(50) not null
        CONSTRAINT nnGrauProficienciaDesignacao not null
);

--DROP TABLE GrauProficiencia CASCADE CONSTRAINTS PURGE;


--SQL AreaAtividade

CREATE TABLE AreaAtividade (
    idAreaAtividade INTEGER PRIMARY KEY
        CONSTRAINT ckAreaAtividadeIdAreaAtividade CHECK (idAreaAtividade > 0),

    descricaoBreve VARCHAR(100) not null
        CONSTRAINT nnAreaAtividadeDescricaoBreve not null,

    descricaoDetalhada VARCHAR(1000) not null
        CONSTRAINT nnAreaAtividadeDescricaoDetalhada not null
);

--DROP TABLE AreaAtividade CASCADE CONSTRAINTS PURGE;


--SQL EnderecoPostal

CREATE TABLE EnderecoPostal (
    idOrganizacao INTEGER PRIMARY KEY, FOREIGN KEY,
        CONSTRAINT ckEnderecoPostalIdOrganizacao CHECK (idOrganizacao > 0),

    morada VARCHAR(100) not null
        CONSTRAINT nnEnderecoPostalMorada not null,

    codigoPostal VARCHAR(8) not null
        CONSTRAINT ckEnderecoPostalCodigoPostal CHECK (regexp_like(^\d{4}(-\d{3})?$)),

    localidade VARCHAR(50) not null
        CONSTRAINT nnEnderecoPostalLocalidade not null
);

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
