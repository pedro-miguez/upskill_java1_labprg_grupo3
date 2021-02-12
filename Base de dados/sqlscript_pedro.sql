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


alter table Tarefa
add constraint fk_Tarefa_idOrganizacao FOREIGN KEY (idOrganizacao) references Organizacao (idOrganizacao);

alter table Tarefa
add constraint fk_Tarefa_idCategoria FOREIGN KEY (idCategoria) references CategoriaTarefa (idCategoria);

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


create or replace trigger trgAnuncio after insert on Anuncio for each row
declare
 v_datainiciopub date;
 v_datafimpub date;
 v_datainiciocandidatura date;
 v_datafimcandidatura date;
 v_datainicioseri date;
 v_datafimseri date;
begin
 v_datainiciopub := trunc(:new.dataInicioPublicitacao);
 v_datafimpub := trunc(:new.dataFimPublicitacao);
 v_datainiciocandidatura := trunc(:new.dataInicioCandidatura);
 v_datafimcandidatura := trunc(:new.dataFimCandidatura);
 v_datainicioseri := trunc(:new.dataInicioSeriacao);
 v_datafimseri := trunc(:new.dataFimSeriacao);

 if v_datafimpub <= v_datainiciopub then
    raise_application_error(-20000, 'Erro na data de fim de publicitacao');
 else if v_datainiciocandidatura <= v_datafimpub then
    raise_application_error(-20000, 'Erro na data de inicio de candidatura');
 else if v_datafimcandidatura <= v_datainiciocandidatura then
    raise_application_error(-20000, 'Erro na data de fim de candidatura');
 else if v_datainicioseriacao <= v_datafimcandidatura then
    raise_application_error(-20000, 'Erro na data de inicio de seriacao');
 else if v_datafimseriaca <= v_datainicioseriacao then
    raise_application_error(-20000, 'Erro na data de fim de seriacao');
 end if;
end;
/

create or replace trigger trgProcessoSeriacao after insert on ProcessoSeriacao for each row

declare
 v_dataRealizacao date;
 v_datainicioseri date;
 v_datafimseri date;

begin
 SELECT dataInicioSeriacao INTO v_datainicioseri from Anuncio where idAnuncio = :new.idAnuncio;
 SELECT dataFimSeriacao INTO v_datafimseri from Anuncio where idAnuncio = :new.idAnuncio;

 if v_dataRealizacao <= v_datainicioseri then
    raise_application_error(-20000, 'Erro na data de realizacao (inferior à data de inicio de seriação do anuncio)');
 else if v_dataRealizacao >= v_datafimseri then
    raise_application_error(-20000, 'Erro na data de realizacao (superior à data de fim de seriação do anuncio)');
 end if;
end;
/





