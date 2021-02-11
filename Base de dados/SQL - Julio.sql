create table Plataforma(
    designacao varchar(40) PRIMARY KEY
);

create table Organizacao(
    idOrganizacao integer PRIMARY KEY
        constraint ckOrganizacaoIdOrganizacaoValido check (idOrganizacao > 0),
    NIF integer UNIQUE
        constraint ckOrganizacaoNIFValido check (NIF between 100000000 and 999999999)
        constraint nnOrganizacaoNIF not null,
    email varchar(40) UNIQUE
        constraint ckOrganizacaoEmailValido check (regexp_like(email, '^[\w!#$%&+/=?{|}~^-]+(?:\.[\w!#$%&+/=?{|}~^-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,6}$'))
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
        constraint nnAdministrativoIdUtilizador not null
);

create table Colaborador(
    idColaborador integer 
        constraint ckColaboradorIdColaboradorValido check (idColaborador > 0),
    idOrganizacao integer
        constraint ckColaboradorIdOrganizacaoValido check (idOrganizacao > 0),
    nome varchar(40)
        constraint nnColaboradorNome not null, 
    idFuncao integer
        constraint ckColaboradorIdFuncaoValido check (idFuncao > 0)
        constraint nnColaboradorIdFuncao not null,
    telefone integer
        constraint ckColaboradorTelefoneValido check (telefone between 100000000 and 999999999)
        constraint nnColaboradorTelefone not null,
    email varchar(40) UNIQUE
        constraint ckColaboradorEmailValido check (regexp_like(email, '^[\w!#$%&+/=?{|}~^-]+(?:\.[\w!#$%&+/=?{|}~^-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,6}$'))
        constraint nnColaboradorEmail not null,
    constraint pkColaboradorIdColaboradorIdOrganizacao PRIMARY KEY (idColaborador, idOrganizacao)
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
        constraint ckFreelancerEmailValido check (regexp_like(email, '^[\w!#$%&+/=?{|}~^-]+(?:\.[\w!#$%&+/=?{|}~^-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,6}$'))
        constraint nnFreelancerEmail not null
);

create table HabilitacaoAcademica(
    idHabilitacao integer PRIMARY KEY
        constraint ckHabilitacaoAcademicaIdHabilitacao check (idHabilitacao > 0),
    idFreelancer integer
        constraint ckHabilitacaoAcademicaIdFreelancer check (idFreelancer > 0),
    grau varchar(40)
        constraint nnHabilitacaoAcademicaGrau not null,
    designacaoCurso varchar(40)
        constraint nnHabilitacaoAcademicaDesigancaoCurso not null,
    nomeInstituicao varchar(40)
        constraint nnHabilitacaoAcademicaNomeInstituicao not null,
    mediaCurso float(2,1) 
        constraint nnHabilitacaoAcademicaMedia not null
        constraint ckHabilitacaoAcademicaMediaCurso check (mediaCurso between 0 and 20)
);

alter table Organizacao
add constraint fkOrganizacaoIdGestor FOREIGN KEY (idGestor) references Colaborador (idColaborador);

alter table Administrativo
add constraint fkAdministrativoIdUtilizador FOREIGN KEY (idUtilizador) references Utilizador (idUtilizador);

alter table Colaborador
add constraint fkColaboradorIdOrganizacao FOREIGN KEY (idOrganizacao) references Organizacao (idOrganizacao);

alter table Colaborador
add constraint fkColaboradorIdFuncao FOREIGN KEY (idFuncao) references Funcao (idFuncao);

alter table HabilitacaoAcademica
add constraint fkHabilitacaoAcademicaIdFreelancer FOREIGN KEY (idFreelancer) referencer Freelancer (idFreelancer);