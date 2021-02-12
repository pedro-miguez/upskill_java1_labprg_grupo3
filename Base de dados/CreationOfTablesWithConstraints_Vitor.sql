create table ReconhecimentoCT (
    idCompetenciaTecnica {FK} INTEGER
        constraint nnReconhecimentoCTidCompetenciaTecnica not null
        constraint ckReconhecimentoCTidCompetenciaTecnica check (0 < idCompetenciaTecnica)
    idFreelancer {FK} INTEGER
        constraint nnReconhecimentoCTidFreelancer not null
        constraint ckReconhecimentoCTidFreelancer check (0 < idFreelancer)
    nivelGrauProficiencia {FK} INTEGER 
    --(nivel - GrauProficiencia) (trigger para verificar se o nível está dentro dos limites
    -- min e max dos niveis associados à competencia tecnica em GrauProficiencia)
        constraint nnReconhecimentoCTnivelGrauProficiencia not null
        --constraint ckReconhecimentoCTnivelGrauProficiencia check (0 < nivelGrauProficiencia =< 5)********************************
    dataReconhecimento date
        constraint nnReconhecimentoCTdataReconhecimento not null
        constraint ckReconhecimentoCTdataReconhecimento check (sysdate < dataReconhecimento < 2000))

    constraint pkReconhecimentoCTidCompetenciaTecnicaidFreelancer primary key (idCompetenciaTecnica, idFreelancer)
)

create table Utilizador (
    idUtilizador INTEGER
        constraint nnUtilizadorIdUtilizador not null
        constraint ckUtilizadorIdUtilizador check (0 < idUtilizador)
        constraint pkUtilizadorIdUtilizador primary key
    nome varchar(40)
        constraint nnUtilizadorNome not null
    email varchar(40)
        constraint nnUtilizadorEmail not null
        constraint ckUtilizadorEmail check (regexp_like(email, '^[\w!#$%&+/=?{|}~^-]+(?:.[\w!#$%&+/=?{|}~^-]+)*@(?:[a-zA-Z0-9-]+.)+[a-zA-Z]{2,6}$'))**********
    password varchar(10)
        constraint nnUtilizadorPassword not null
    designacao(role) {FK} varchar(40)
        constraint nnUtilizadorDesignacao not null
)

create table Role (
    idUtilizador{FK} INTEGER
        constraint ckRoleidUtilizador check (0 < idUtilizador)
        constraint nnRoleidUtilizador not null
    designacao varchar(40)
        constraint nnRoleDesignacao not null

    constraint pkRoleIdUtilizadorDesignacao primary key (idUtilizador, designacao)
)

create table CategoriaTarefa (
    idCategoria INTEGER
        constraint nnCategoriaTarefaIdCategoria not null
        constraint ckCategoriaTarefaIdCategoria check (0 < idCategoria)
    idAreaAtividade {FK} INTEGER
        constraint nnCategoriaTarefaIdAreaAtividade not null
        constraint ckCategoriaTarefaIdAreaAtividade check (0 < idAreaAtividade)
    descricao varchar(100)
        constraint nnCategoriaTarefaDescricao not null

    constraint pkCategoriaTarefaIdCategoriaIdAreaAtividade primary key (idCategoria, idAreaAtividade)
)

create table  CaraterizacaoCategoriaTarefa (
    idCompTec {FK} INTEGER
        constraint nnCaraterizaçãoCategoriaTarefaIdCompTec not null
        constraint ckCaraterizaçãoCategoriaTarefaIdCompTec check (0 < idCompTec)
    idCategoria {FK} INTEGER
        constraint nnCaraterizaçãoCategoriaTarefaIdCategoria not null
        constraint ckCaraterizaçãoCategoriaTarefaIdCategoria check (0 < idCategoria)
    caracter char(3)
        constraint nnCaraterizaçãoCategoriaTarefaCaracter not null
        constraint ckCaraterizaçãoCategoriaTarefaCaracter check (upper(caracter) in ('OBR ', 'OPC'))
    nivelGrauMinimo {FK} INTEGER
    -- (nivel - GrauProficiencia) (trigger para verificar se o nível está dentro dos limites min e max dos
    -- niveis associados à competencia tecnica em GrauProficiencia)
        constraint nnCaraterizaçãoCategoriaTarefaNivelGrauMinimo not null
        --constraint ckCaraterizaçãoCategoriaTarefaNivelGrauMinimo check (0 < nivelGrauMinimo =< 5)***************

    constraint pkCaraterizaçãoCategoriaTarefaIdCompTecIdCategoria primary key (idCompTec, idCategoria)
)
