create table ReconhecimentoCT (
    idCompetenciaTecnica INTEGER
        constraint nnReconhecimentoCTidCompetenciaTecnica not null
        constraint ckReconhecimentoCTidCompetenciaTecnica check (0 < idCompetenciaTecnica),
    idFreelancer INTEGER
        constraint nnReconhecimentoCTidFreelancer not null
        constraint ckReconhecimentoCTidFreelancer check (0 < idFreelancer),
    nivelGrauProficiencia INTEGER 
    --(nivel - GrauProficiencia) (trigger para verificar se o nível está dentro dos limites
    -- min e max dos niveis associados à competencia tecnica em GrauProficiencia)
        constraint nnReconhecimentoCTnivelGrauProficiencia not null,
    dataReconhecimento date
        constraint nnReconhecimentoCTdataReconhecimento not null
        constraint ckReconhecimentoCTdataReconhecimento check (dataReconhecimento > TO_DATE('2021-01-01', 'yyyy-mm-dd')),

    constraint pkReconhecimentoCTidCompetenciaTecnicaidFreelancer primary key (idCompetenciaTecnica, idFreelancer)
);

create table Utilizador (
    idUtilizador INTEGER
        constraint nnUtilizadorIdUtilizador not null
        constraint ckUtilizadorIdUtilizador check (0 < idUtilizador)
        constraint pkUtilizadorIdUtilizador primary key,
    nome varchar(40)
        constraint nnUtilizadorNome not null,
    email varchar(40)
        constraint nnUtilizadorEmail not null
        constraint ckUtilizadorEmail check (regexp_like(email, '^[\w!#$%&+/=?{|}~^-]+(?:.[\w!#$%&+/=?{|}~^-]+)*@(?:[a-zA-Z0-9-]+.)+[a-zA-Z]{2,6}$')),
    palavraPasse varchar(10)
        constraint nnUtilizadorPassword not null,
    designacao varchar(40)
        constraint nnUtilizadorDesignacao not null
);

create table Role (
    designacao varchar(40) primary key
);

create table CategoriaTarefa (
    idCategoria INTEGER
        constraint nnCategoriaTarefaIdCategoria not null
        constraint ckCategoriaTarefaIdCategoria check (0 < idCategoria),
    idAreaAtividade INTEGER
        constraint nnCategoriaTarefaIdAreaAtividade not null
        constraint ckCategoriaTarefaIdAreaAtividade check (0 < idAreaAtividade),
    descricao varchar(100)
        constraint nnCategoriaTarefaDescricao not null,

    constraint pkCategoriaTarefaIdCategoriaIdAreaAtividade primary key (idCategoria, idAreaAtividade)
);

create table  CaraterizacaoCompetenciaTecnica (
    idCompetenciaTecnica INTEGER
        constraint nnCaraterizacaoCompetenciaTecnicaIdCompTec not null
        constraint ckCaraterizacaoCompetenciaTecnicaIdCompTec check (0 < idCompetenciaTecnica),
    idCategoria INTEGER
        constraint nnCaraterizacaoCompetenciaTecnicaTarefaIdCategoria not null
        constraint ckCaraterizacaoCompetenciaTecnicaIdCategoria check (0 < idCategoria), 
    caracter char(3)
        constraint nnCaraterizacaoCompetenciaTecnicaCaracter not null
        constraint ckCaraterizacaoCompetenciaTecnicaCaracter check (upper(caracter) in ('OBR ', 'OPC')),
    nivelGrauMinimo INTEGER
    -- (nivel - GrauProficiencia) (trigger para verificar se o nível está dentro dos limites min e max dos
    -- niveis associados à competencia tecnica em GrauProficiencia)
        constraint nnCaraterizacaoCompetenciaTecnicaNivelGrauMinimo not null,
       
    constraint pkCaraterizacaoCompetenciaTecnicaIdCompTecIdCategoria primary key (idCompetenciaTecnica, idCategoria)
);

alter table CaraterizacaoCompetenciaTecnica
add constraint fk_CaraterizacaoCompetenciaTecnica_idCompetenciaTecnica FOREIGN KEY (idCompetenciaTecnica) references CompetenciaTecnica (idCompetenciaTecnica);


alter table CaraterizacaoCompetenciaTecnica
add constraint fk_CaraterizacaoCompetenciaTecnica_idCategoria FOREIGN KEY (idCategoria) references CategoriaTarefa (idCategoria);



alter table CategoriaTarefa
add constraint fk_CategoriaTarefa_idAreaAtividade FOREIGN KEY (idAreaAtividade) references AreaAtividade (idAreaAtividade);


alter table Utilizador
add constraint fk_Utilizador_designacao FOREIGN KEY (designacao) references Role (designacao);


alter table ReconhecimentoCT
add constraint fk_ReconhecimentoCT_idCompetenciatecnica FOREIGN KEY (idCompetenciatecnica) references CompetenciaTecnica (idCompetenciatecnica);


alter table ReconhecimentoCT
add constraint fk_ReconhecimentoCT_idFreelancer FOREIGN KEY (idFreelancer) references Freelancer (idFreelancer);



