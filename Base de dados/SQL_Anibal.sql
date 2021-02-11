--SQL ProcessoSeriacaoColaborador

CREATE TABLE ProcessoSeriacaoColaborador {
    dataRealizacao
}

--SQL TipoRegimento

CREATE TABLE TipoRegimento {
    designacao
    descricaoRegras
}

--SQL CompetenciaTecnica

CREATE TABLE CompetenciaTecnica {
    nome 
    identificadorCompetenciaTecnica
    descricaoBreve
    descricaoDetalhada
    grausProficiencia
}

--SQL GrauProficiencia

CREATE TABLE GrauProficiencia {
    nivel
    designacao
}

--SQL AreaAtividade

CREATE TABLE AreaAtividade {
    identificadorAreaAtividade
    descricaoBreve
    descricaoDetalhada
}

--SQL EnderecoPostal

CREATE TABLE EnderecoPostal {
    idOrganizacao PRIMARY KEY,
    morada
    codigoPostal
    localidade
}
