create table Tarefa( 

    idTarefa integer primary key 
        constraint ck_Tarefa_idTarefa check (idTarefa > 0), 
    referenciaTarefa integer 
        constraint ck_Tarefa_referenciaTarefa check (referenciaTarefa > 0),
    idOrganizacao integer 
        constraint ck_Tarefa_idOrganizacao check (idOrganizacao > 0), 
    idCategoria integer 
        constraint nn_Tarefa_idCategoria not null 
        constraint ck_Tarefa_idCategoria check (idCategoria > 0), 
    idEstadoTarefa integer 
        constraint nn_Tarefa_idEstadoTarefa not null 
        constraint ck_Tarefa_idEstadoTarefa check (idEstadoTarefa > 0),
    designacao varchar(100) 
        constraint nn_Tarefa_designacao not null, 
    descricaoInformal varchar(250) 
        constraint nn_Tarefa_descricaoInformal not null, 
    descricaoTecnica varchar(500) 
        constraint nn_Tarefa_descricaoTarefa not null, 
    estimativaDuracao integer 
        constraint nn_Tarefa_estimativaDuracao not null 
        constraint ck_Tarefa_estimativaDuracao check (estimativaDuracao > 0), 
    estimativaCusto float(2) 
        constraint nn_Tarefa_estimativaCusto not null 
        constraint ck_Tarefa_estimativaCusto check (estimativaCusto > 0), 
     
    constraint uq_Tarefa_referenciaTarefa_idOrganizacao unique (referenciaTarefa, idOrganizacao)
);

create table EstadoTarefa (
    idEstadoTarefa integer primary key 
        constraint ck_EstadoTarefa_idEstadoTarefa check (idEstadoTarefa > 0),
    designacao varchar(100) 
        constraint nn_EstadoTarefa_designacao not null
);

create table Anuncio (
    idAnuncio integer primary key 
        constraint ck_Anuncio_idAnuncio check (idAnuncio > 0),
    idTarefa integer 
        constraint uq_Anuncio_idTarefa unique 
        constraint ck_Anuncio_idTarefa check (idTarefa > 0),
    dataInicioPublicitacao date 
        constraint nn_Anuncio_dataInicioPublicitacao not null 
        constraint ck_Anuncio_dataInicioPublicitacao check (dataInicioPublicitacao > TO_DATE('2021-01-01', 'yyyy-mm-dd')),
    dataFimPublicitacao date 
        constraint nn_Anuncio_dataFimPublicitacao not null,
    dataInicioCandidatura date 
        constraint nn_Anuncio_dataInicioCandidatura not null,
    dataFimCandidatura date 
        constraint nn_Anuncio_dataFimCandidatura not null,
    dataInicioSeriacao date 
        constraint nn_Anuncio_dataInicioSeriacao not null,
    dataFimSeriacao date 
        constraint nn_Anuncio_dataFimSeriacao not null
);

create table Candidatura (
    idAnuncio integer 
        constraint ck_Candidatura_idAnuncio check (idAnuncio > 0),
    idFreelancer integer 
        constraint ck_Candidatura_idFreelancer check(idFreelancer > 0),
    dataCandidatura date 
        constraint nn_Candidatura_dataCandidatura not null,
    valorPretendido float(2) 
        constraint nn_Candidatura_valorPretendido not null 
        constraint ck_Candidatura_valorPretendido check (valorPretendido > 0),
    nrDias integer 
        constraint ck_Candidatura_nrDias check (nrDias > 0),
    txtApresentacao varchar(500) 
        constraint nn_Candidatura_txtApresentacao not null,
    txtMotivacao varchar(500) 
        constraint nn_Candidatura_txtMotivacao not null,
    
    constraint pk_Candidatura_idAnuncio_idFreelancer primary key (idAnuncio, idFreelancer)
);

create table ProcessoSeriacao (
    idAnuncio integer primary key 
        constraint ck_ProcessoSeriacao_idAnuncio check (idAnuncio > 0),
    idTipoRegimento integer 
        constraint nn_ProcessoSeriacao_idTipoRegimento not null 
        constraint ck_ProcessoSeriacao_idTipoRegimento check (idTipoRegimento > 0),
    dataRealizacao date 
        constraint nn_ProcessoSeriacao_dataRealizacao not null
);

create table Classificacao (
    idAnuncio integer 
        constraint ck_Classificacao_idAnuncio check (idAnuncio > 0),
    idCandidatura integer 
         constraint ck_Classificacao_idCandidatura check (idCandidatura > 0),
    lugar integer 
        constraint nn_Classificacao_lugar not null 
        constraint ck_Classificacao_lugar check (lugar > 0),
    
    constraint pk_Classificacao_idAnuncio_idCandidatura primary key (idAnuncio, idCandidatura)
);

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