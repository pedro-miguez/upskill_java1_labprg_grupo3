CREATE TABLE ProcessoSeriacaoColaborador (

    idAnuncio INTEGER
        CONSTRAINT ckProcessoSeriacaoColaboradorIdAnuncio CHECK (idAnuncio > 0),

    idColaborador INTEGER 
        CONSTRAINT ckProcessoSeriacaoColaboradorIdColaborador CHECK (idColaborador > 0),

    idOrganizacao INTEGER 
        CONSTRAINT ckProcessoSeriacaoColaboradorIdOrganizacao CHECK (idOrganizacao > 0),

    CONSTRAINT pkProcessoSeriacaoColaboradorIdAnuncioIdColaboradorIdOrganizacao PRIMARY KEY (idAnuncio, idColaborador, idOrganizacao)
);


CREATE TABLE TipoRegimento (
    idTipoRegimento INTEGER PRIMARY KEY
        CONSTRAINT ckTipoRegimentoIdTipoRegimento CHECK (idTipoRegimento > 0),

    designacao VARCHAR(500) 
        CONSTRAINT nnTipoRegimentoDesignacao not null,

    descricaoRegras VARCHAR(1000)
        CONSTRAINT nnTipoRegimentoDescricaoRegras not null
);

CREATE TABLE CompetenciaTecnica (
    idCompetenciaTecnica INTEGER PRIMARY KEY
        CONSTRAINT ckCompetenciaTecnicaIdCompetenciaTecnica CHECK (idCompetenciaTecnica > 0),

    idAreaAtividade INTEGER
        constraint ck_CompetenciaTecnica_idAreaAtividade CHECK (idAreaAtividade > 0),

    nome VARCHAR(50) 
        CONSTRAINT nnCompetenciaTecnicaNome not null,

    descricaoBreve VARCHAR(100)
        CONSTRAINT nnCompetenciaTecnicaDescricaoBreve not null,

    descricaoDetalhada VARCHAR(1000)
        CONSTRAINT nnCompetenciaTecnicaDescricaoDetalhada not null

);

CREATE TABLE GrauProficiencia (
    idCompetenciaTecnica INTEGER
        CONSTRAINT ckGrauProficienciaIdCompetenciaTecnica CHECK (idCompetenciaTecnica > 0),

    nivel INTEGER,
        CONSTRAINT ckGrauProficienciaNivel CHECK (nivel > 0),

    designacao VARCHAR(50) 
        CONSTRAINT nnGrauProficienciaDesignacao not null,
        
        constraint pk_GrauProficiencia_idCompetenciaTecnica_Nivel primary key (idCompetenciaTecnica, Nivel)
);

CREATE TABLE AreaAtividade (
    idAreaAtividade INTEGER PRIMARY KEY
        CONSTRAINT ckAreaAtividadeIdAreaAtividade CHECK (idAreaAtividade > 0),

    descricaoBreve VARCHAR(100) 
        CONSTRAINT nnAreaAtividadeDescricaoBreve not null,

    descricaoDetalhada VARCHAR(1000) 
        CONSTRAINT nnAreaAtividadeDescricaoDetalhada not null
);

CREATE TABLE EnderecoPostal (
    idOrganizacao INTEGER PRIMARY KEY
        CONSTRAINT ckEnderecoPostalIdOrganizacao CHECK (idOrganizacao > 0),

    morada VARCHAR(100) 
        CONSTRAINT nnEnderecoPostalMorada not null,

    codigoPostal VARCHAR(8) 
        CONSTRAINT ckEnderecoPostalCodigoPostal CHECK ( regexp_like(codigoPostal, '^\d{4}(-\d{3})?$')),

    localidade VARCHAR(50) 
        CONSTRAINT nnEnderecoPostalLocalidade not null
);

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

