create or replace procedure createOrganizacao(
 p_NIF Organizacao.NIF%type
, p_emailOrg Organizacao.email%type
, p_nomeOrg Organizacao.nome%type
, p_telefoneOrg Organizacao.telefone%type
, p_websiteOrg Organizacao.website%type
, p_moradaOrg EnderecoPostal.morada%type
, p_codigoPostalOrg EnderecoPostal.morada%type
, p_localidadeOrg EnderecoPostal.localidade%type
, p_nomeGestor utilizador.nome%type
, p_emailGestor utilizador.email%type
, p_palavraPasseGestor utilizador.palavraPasse%type
, p_telefoneGestor colaborador.telefone%type)
is
 v_id Organizacao.idOrganizacao%type;
begin

 insert into Organizacao(NIF, email, nome, telefone, website)
 values(p_NIF, p_emailOrg, p_nomeOrg, p_telefoneOrg, p_websiteOrg) returning idOrganizacao into v_id;

 insert into EnderecoPostal(idOrganizacao, morada, codigoPostal, localidade) values (v_id, p_moradaOrg, p_codigoPostalOrg, p_localidadeOrg);

 createUtilizadorGestor(p_nomeGestor, p_emailGestor, p_palavraPasseGestor, p_telefoneGestor, v_id);
end;
/

create or replace procedure createUtilizadorGestor(
 p_nome utilizador.nome%type
, p_email utilizador.email%type
, p_palavraPasse utilizador.palavraPasse%type
, p_telefone colaborador.telefone%type
, p_idOrganizacao Organizacao.idOrganizacao%type)
is
 v_id utilizador.idUtilizador%type;
begin
 insert into Utilizador(nome, email, palavraPasse, designacao)
 values(p_nome, p_email, p_palavraPasse, 'gestor') returning idUtilizador into v_id;
 insert into Colaborador(idColaborador, idOrganizacao, nome, funcao, telefone, email) 
 values(v_id, p_idOrganizacao, p_nome, 'gestor', p_telefone, p_email);
 update Organizacao set idgestor = v_id where idOrganizacao = p_idOrganizacao;
end;
/

create or replace procedure createAreaAtividade( 
    p_codunico AreaAtividade.IdAreaAtividade%type, 
    p_descricao AreaAtividade.descricaoBreve%type, 
    p_descdetailed AreaAtividade.descricaoDetalhada%type) 
    is
    begin 
insert into AreaAtividade(IdAreaAtividade, descricaoBreve, descricaoDetalhada) values (p_codunico, p_descricao, p_descdetailed); 
end;
/

create or replace procedure createCompetenciaTecnica(
p_idCompetenciaTecnica competenciaTecnica.idCompetenciaTecnica%type,
p_descricao competenciaTecnica.descricaoBreve%type,
p_descDetalhada competenciaTecnica.descricaoDetalhada%type,
p_areaAtividade AreaAtividade.idAreaAtividade%type)
is 
 v_count int;   
 ex_areaAtividade exception;
begin
    select count (*) into v_count
        from AreaAtividade where idAreaAtividade = p_areaAtividade;
        if v_count= 0 then
        raise ex_areaAtividade;
        end if;
    insert into CompetenciaTecnica(idCompetenciaTecnica, idAreaAtividade, descricaoBreve, descricaoDetalhada) values (p_idCompetenciaTecnica, p_areaAtividade, p_descricao, p_descDetalhada);
    exception when ex_areaAtividade then
    raise_application_error(-20001,'Área de Atividade inexistente');
end;
/

create or replace procedure createCategoriaTarefa( 
    p_codunico AreaAtividade.IdAreaAtividade%type, 
    p_descricao CategoriaTarefa.descricao%type) 
    is
    begin 
insert into CategoriaTarefa(IdAreaAtividade, descricao) values (p_codunico, p_descricao); 
end;
/

create or replace procedure createCaraterizacaoCompetenciaTecnica( 
    p_idCompetenciaTecnica CompetenciaTecnica.idCompetenciaTecnica%type,
    p_idCategoria CategoriaTarefa.idCategoria%type,
    p_caracter CaraterizacaoCompetenciaTecnica.caracter%type, 
    p_nivelGrauMinimo GrauProficiencia.nivel%type) 
    is
    begin 
insert into CaraterizacaoCompetenciaTecnica(idCompetenciaTecnica, idCategoria, caracter, nivelGrauMinimo) values (p_idCompetenciaTecnica, p_idCategoria, p_caracter, p_nivelGrauMinimo); 
end;
/