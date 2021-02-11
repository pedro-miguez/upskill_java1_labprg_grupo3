create table Plataforma(
    designacao varchar(40) PRIMARY KEY
);

create table Organizacao(
    idOrganizacao integer PRIMARY KEY
        constraint ckIdOrganizacaoValido check (idOrganizacao > 0),
    NIF integer UNIQUE
        constraint ckNIFValido check (NIF between 100000000 and 999999999)
        constraint nnNIF not null,
    email varchar(40) UNIQUE
        constraint ckEmailValido check (regexp_like, '^[\w!#$%&+/=?{|}~^-]+(?:\.[\w!#$%&+/=?{|}~^-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,6}$')
        constraint nnEmail not null,
    idGestor integer 
        constraint ckIdGestorValido check (idGestor > 0),
    nome varchar(40)
        constraint nnNome not null,
    telefone integer
        constraint ckTelefoneValido check (telefone between 100000000 and 999999999)
        constraint nnTelefone not null,
    website varchar(40)
        constraint ckWebsiteValido check (regexp_like, '(http:\/\/|https:\/\/)?(www.)?([a-zA-Z]+.[a-zA-Z]*.[a-z0-9]+)\.(([a-z]){2,3})?')
        constraint nnWebsite not null
);

create table Administrativo(
    nome varchar(40) PRIMARY KEY,
    idUtilizador integer
        constraint ckIdUtilizador check (idUtilizador > 0)
        constraint nnIdUtilizador not null
);


alter table Organizacao
add constraint fkOrganizacaoIdGestor FOREIGN KEY (idGestor) references Colaborador (idColaborador);

alter table Administrativo
add constraint fkAdministrativoIdUtilizador FOREIGN KEY (idUtilizador) references Utilizador (idUtilizador);