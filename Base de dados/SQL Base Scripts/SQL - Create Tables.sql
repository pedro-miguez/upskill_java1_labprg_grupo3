create table Tarefa( 

    idTarefa integer generated as identity primary key 
        constraint ck_Tarefa_idTarefa check (idTarefa > 0), 
    referenciaTarefa CHAR(6)
        CONSTRAINT ckTarefaReferenciaTarefa CHECK (regexp_like(referenciaTarefa, '^([a-zA-Z]){3}(-\d{2})?$')),
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
    idEstadoTarefa integer generated as identity primary key 
        constraint ck_EstadoTarefa_idEstadoTarefa check (idEstadoTarefa > 0),
    designacao varchar(100) 
        constraint nn_EstadoTarefa_designacao not null
);

create table Anuncio (
    idAnuncio integer generated as identity primary key 
        constraint ck_Anuncio_idAnuncio check (idAnuncio > 0),
    idTarefa integer 
        constraint uq_Anuncio_idTarefa unique 
        constraint ck_Anuncio_idTarefa check (idTarefa > 0),
    idTipoRegimento integer
        constraint nn_Anuncio_idTipoRegimento not null,
    idColaborador integer
        constraint nn_Anuncio_idColaborador not null,
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
    txtApresentacao varchar(500),
    txtMotivacao varchar(500),
    

    
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
    idFreelancer integer 
         constraint ck_Classificacao_idFreelancer check (idFreelancer > 0),
    lugar integer 
        constraint nn_Classificacao_lugar not null 
        constraint ck_Classificacao_lugar check (lugar > 0),
    
    constraint pk_Classificacao_idAnuncio_idFreelancer primary key (idAnuncio, idFreelancer)
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
    idTipoRegimento INTEGER generated as identity PRIMARY KEY
        CONSTRAINT ckTipoRegimentoIdTipoRegimento CHECK (idTipoRegimento > 0),

    designacao VARCHAR(500) 
        CONSTRAINT nnTipoRegimentoDesignacao not null,

    descricaoRegras VARCHAR(1000)
        CONSTRAINT nnTipoRegimentoDescricaoRegras not null
);

CREATE TABLE CompetenciaTecnica (
    idCompetenciaTecnica CHAR(6) PRIMARY KEY
        CONSTRAINT ckCompetenciaTecnicaIdCompetenciaTecnica CHECK (regexp_like(idCompetenciaTecnica, '^([a-zA-Z]){3}(-\d{2})?$')),

    idAreaAtividade CHAR(6) 
        CONSTRAINT ckCompetenciaTecnicaIdAreaAtividade CHECK (regexp_like(idAreaAtividade, '^([a-zA-Z]){3}(-\d{2})?$')),

    descricaoBreve VARCHAR(100)
        CONSTRAINT nnCompetenciaTecnicaDescricaoBreve not null,

    descricaoDetalhada VARCHAR(1000)
        CONSTRAINT nnCompetenciaTecnicaDescricaoDetalhada not null

);

CREATE TABLE GrauProficiencia (
    idCompetenciaTecnica CHAR(6) 
        CONSTRAINT ckGrauProficienciaIdCompetenciaTecnica CHECK (regexp_like(idCompetenciaTecnica, '^([a-zA-Z]){3}(-\d{2})?$')),

    nivel INTEGER,
        CONSTRAINT ckGrauProficienciaNivel CHECK (nivel > 0),

    designacao VARCHAR(50) 
        CONSTRAINT nnGrauProficienciaDesignacao not null,
        
        constraint pk_GrauProficiencia_idCompetenciaTecnica_Nivel primary key (idCompetenciaTecnica, Nivel)
);

CREATE TABLE AreaAtividade (
    idAreaAtividade CHAR(6) PRIMARY KEY
        CONSTRAINT ckAreaAtividadeIdAreaAtividade CHECK (regexp_like(idAreaAtividade, '^([a-zA-Z]){3}(-\d{2})?$')),

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

create table Organizacao(
    idOrganizacao integer generated as identity PRIMARY KEY
        constraint ckOrganizacaoIdOrganizacaoValido check (idOrganizacao > 0),
    NIF integer UNIQUE
        constraint ckOrganizacaoNIFValido check (NIF between 100000000 and 999999999)
        constraint nnOrganizacaoNIF not null,
    email varchar(40) UNIQUE
        --constraint ckOrganizacaoEmailValido check (regexp_like(email, '^[\w!#$%&+/=?{|}~^-]+(?:\.[\w!#$%&+/=?{|}~^-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,6}$'))
        constraint nnOrganizacaoEmail not null,
    idGestor integer 
        constraint ckOrganizacaoIdGestorValido check (idGestor > 0),
    nome varchar(40)
        constraint nnOrganizacaoNomeOrganizacao not null,
    telefone integer
        constraint ckOrganizacaoTelefoneValido check (telefone between 100000000 and 999999999)
        constraint nnOrganizacaoTelefone not null,
    website varchar(40)
        constraint ckOrganizacaoWebsiteValido check (regexp_like(website, '^(http:\/\/|https:\/\/)?(www.)?([a-zA-Z]+.[a-zA-Z]*.[a-z0-9]+)\.(([a-z]){2,3})?$'))
        constraint nnOrganizacaoWebsite not null
);

create table Administrativo(
    nome varchar(40) PRIMARY KEY,
    idUtilizador integer
        constraint ckAdministrativoIdUtilizador check (idUtilizador > 0)
        constraint nnAdministrativoIdUtilizador not null,
    email varchar(40) UNIQUE
        --constraint ckAdministrativoEmailValido check (regexp_like(email, '^[\w!#$%&+/=?{|}~^-]+(?:\.[\w!#$%&+/=?{|}~^-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,6}$'))
        constraint nnAdministrativoEmail not null
);

create table Colaborador(
    idColaborador integer PRIMARY KEY
        constraint ckColaboradorIdColaboradorValido check (idColaborador > 0),
    idOrganizacao integer
        constraint ckColaboradorIdOrganizacaoValido check (idOrganizacao > 0),
    nome varchar(40)
        constraint nnColaboradorNome not null, 
    funcao varchar(20)
        constraint nnColaboradorIdFuncao not null,
    telefone integer
        constraint ckColaboradorTelefoneValido check (telefone between 100000000 and 999999999)
        constraint nnColaboradorTelefone not null,
    email varchar(40) UNIQUE
        --constraint ckColaboradorEmailValido check (regexp_like(email, '^[\w!#$%&+/=?{|}~^-]+(?:\.[\w!#$%&+/=?{|}~^-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,6}$'))
        constraint nnColaboradorEmail not null
);

create table Freelancer(
    idFreelancer integer PRIMARY KEY
        constraint ckFreelancerIdFreelancer check (idFreelancer > 0),
    NIF integer UNIQUE
        constraint ckFreelancerNIFValido check (NIF between 100000000 and 999999999)
        constraint nnFreelancerNIF not null,
    nome varchar(40)
        constraint nnFreelancerNomeFreelancer not null,
    telefone integer
        constraint ckFreelancerTelefoneValido check (telefone between 100000000 and 999999999)
        constraint nnFreelancerTelefone not null,
    email varchar(40) UNIQUE
        --constraint ckFreelancerEmailValido check (regexp_like(email, '^[\w!#$%&+/=?{|}~^-]+(?:\.[\w!#$%&+/=?{|}~^-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,6}$'))
        constraint nnFreelancerEmail not null
);

create table HabilitacaoAcademica(
    idHabilitacao integer generated as identity PRIMARY KEY
        constraint ckHabilitacaoAcademicaIdHabilitacao check (idHabilitacao > 0),
    idFreelancer integer
        constraint ckHabilitacaoAcademicaIdFreelancer check (idFreelancer > 0),
    grau varchar(40)
        constraint nnHabilitacaoAcademicaGrau not null,
    designacaoCurso varchar(40)
        constraint nnHabilitacaoAcademicaDesigancaoCurso not null,
    nomeInstituicao varchar(40)
        constraint nnHabilitacaoAcademicaNomeInstituicao not null,
    mediaCurso float(2) 
        constraint nnHabilitacaoAcademicaMedia not null
        constraint ckHabilitacaoAcademicaMediaCurso check (mediaCurso between 0 and 20)
);

create table ReconhecimentoCT (
    idCompetenciaTecnica CHAR(6) 
        CONSTRAINT ckReconhecimentoCTidCompetenciaTecnica CHECK (regexp_like(idCompetenciaTecnica, '^([a-zA-Z]){3}(-\d{2})?$'))
        constraint nnReconhecimentoCTidCompetenciaTecnica not null,
    idFreelancer INTEGER
        constraint nnReconhecimentoCTidFreelancer not null
        constraint ckReconhecimentoCTidFreelancer check (0 < idFreelancer),
    nivelGrauProficiencia INTEGER 
        constraint nnReconhecimentoCTnivelGrauProficiencia not null,
    dataReconhecimento date
        constraint nnReconhecimentoCTdataReconhecimento not null
        constraint ckReconhecimentoCTdataReconhecimento check (dataReconhecimento > TO_DATE('2021-01-01', 'yyyy-mm-dd')),

    constraint pkReconhecimentoCTidCompetenciaTecnicaidFreelancer primary key (idCompetenciaTecnica, idFreelancer)
);

create table Utilizador (
    idUtilizador INTEGER generated as identity
        constraint nnUtilizadorIdUtilizador not null
        constraint ckUtilizadorIdUtilizador check (0 < idUtilizador)
        constraint pkUtilizadorIdUtilizador primary key,
    nome varchar(40)
        constraint nnUtilizadorNome not null,
    email varchar(40)
        constraint nnUtilizadorEmail not null,
        --constraint ckUtilizadorEmail check (regexp_like(email, '^[\w!#$%&+/=?{|}~^-]+(?:.[\w!#$%&+/=?{|}~^-]+)*@(?:[a-zA-Z0-9-]+.)+[a-zA-Z]{2,6}$')),
    palavraPasse varchar(10)
        constraint nnUtilizadorPassword not null,
    designacao varchar(40)
        constraint nnUtilizadorDesignacao not null
);

create table Role (
    designacao varchar(40) primary key
);

create table CategoriaTarefa (
    idCategoria INTEGER generated as identity PRIMARY KEY
        constraint nnCategoriaTarefaIdCategoria not null
        constraint ckCategoriaTarefaIdCategoria check (0 < idCategoria),
    idAreaAtividade CHAR(6)
        CONSTRAINT ckCategoriaTarefaIdAreaAtividade CHECK (regexp_like(idAreaAtividade, '^([a-zA-Z]){3}(-\d{2})?$'))
        constraint nnCategoriaTarefaIdAreaAtividade not null,
    descricao varchar(100)
        constraint nnCategoriaTarefaDescricao not null
);

create table  CaraterizacaoCompetenciaTecnica (
    idCompetenciaTecnica CHAR(6) 
        CONSTRAINT ckCaraterizacaoCompetenciaTecnicaIdCompTec CHECK (regexp_like(idCompetenciaTecnica, '^([a-zA-Z]){3}(-\d{2})?$'))
        constraint nnCaraterizacaoCompetenciaTecnicaIdCompTec not null,
    idCategoria INTEGER
        constraint nnCaraterizacaoCompetenciaTecnicaTarefaIdCategoria not null
        constraint ckCaraterizacaoCompetenciaTecnicaIdCategoria check (0 < idCategoria), 
    caracter char(3)
        constraint nnCaraterizacaoCompetenciaTecnicaCaracter not null
        constraint ckCaraterizacaoCompetenciaTecnicaCaracter check (upper(caracter) in ('OBR ', 'OPC')),
    nivelGrauMinimo INTEGER
        constraint nnCaraterizacaoCompetenciaTecnicaNivelGrauMinimo not null,
       
    constraint pkCaraterizacaoCompetenciaTecnicaIdCompTecIdCategoria primary key (idCompetenciaTecnica, idCategoria)
);


create table EstadoAnuncio (
    idEstadoAnuncio integer generated as identity primary key 
        constraint ck_EstadoAnuncio_idEstadoAnuncio check (idEstadoAnuncio > 0),
    designacao varchar(100) 
        constraint nn_EstadoAnuncio_designacao not null
);

create table CandidaturaRetiradas (
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
    txtApresentacao varchar(500),
    txtMotivacao varchar(500),
    
    constraint pk_CandidaturaRetirada_idAnuncio_idFreelancer primary key (idAnuncio, idFreelancer)
);


insert into Role (designacao) values ('gestor');
insert into Role (designacao) values ('colaborador');
insert into Role (designacao) values ('freelancer');
insert into Role (designacao) values ('administrativo');

insert into EstadoTarefa (designacao) values ('private');
insert into EstadoTarefa (designacao) values ('published');
insert into EstadoTarefa (designacao) values ('closed');

insert into EstadoAnuncio (designacao) values('candidatura');
insert into EstadoAnuncio (designacao) values('seriação');
insert into EstadoAnuncio (designacao) values('fechado');
