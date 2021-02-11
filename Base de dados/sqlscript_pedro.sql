create table Tarefa( 
    idTarefa integer check (idTarefa > 0), 
    idOrganizacao integer check (idOrganizacao > 0), 
    idCategoria integer not null check (idCategoria > 0), 
    designacao varchar(100) not null, 
    descricaoInformal varchar(250) not null, 
    descricaoTecnica varchar(500) not null, 
    estimativaDuracao integer not null check (estimativaDuracao > 0), 
    estimativaCusto float(2,2) check (estimativaCusto > 0), 
     
    constraint pk_Tarefa_idTarefa_idOrganizacao primary key (idTarefa, idOrganizacao)
);

create table EstadoTarefa (
    idEstadoTarefa integer primary key check (idEstadoTarefa > 0),
    designacao varchar(100) not null
);

create table Anuncio (
    idAnuncio integer primary key check (idAnuncio > 0),
    idTarefa integer unique check (idTarefa > 0),
    idOrganizacao integer check (idOrganizacao > 0),
    dataInicioPublicitacao date not null check (dataInicioPublicitacao > TO_DATE('2021-01-01', 'yyyy-mm-dd')),
    dataFimPublicitacao date not null,
    dataInicioCandidatura date not null,
    dataFimCandidatura date not null,
    dataInicioSeriacao date not null,
    dataFimSeriacao date not null
);

create table Candidatura (
    idAnuncio integer check (idAnuncio > 0),
    idCandidatura integer check (idCandidatura > 0),
    idFreelancer integer not null check(idFreelancer > 0),
    dataCandidatura date not null,
    valorPretendido float(2,2) not null check (valorPretendido > 0),
    nrDias integer check (nrDias > 0),
    txtApresentacao varchar(500) not null,
    txtMotivacao varchar(500) not null,
    
    constraint pk_Candidatura_idAnuncio_idCandidatura primary key (idAnuncio, idCandidatura)
);

create table ProcessoSeriacao (
    idAnuncio integer primary key check (idAnuncio > 0),
    idTipoRegimento integer not nullcheck (idTipoRegimento > 0),
    dataCandidatura date not null
);

create table Classificacao (
    idAnuncio integer check (idAnuncio > 0),
    idCandidatura integer check (idCandidatura > 0),
    lugar integer not null check (lugar > 0),
    
    constraint pk_Classificacao_idAnuncio_idCandidatura primary key (idAnuncio, idCandidatura)
);
