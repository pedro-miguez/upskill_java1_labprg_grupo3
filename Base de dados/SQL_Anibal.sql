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
