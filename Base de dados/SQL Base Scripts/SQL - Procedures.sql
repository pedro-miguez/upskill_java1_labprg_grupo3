create or replace procedure createGestor(
 p_nome utilizador.nome%type
, p_email utilizador.email%type
, p_palavraPasse utilizador.palavraPasse%type
, p_telefone colaborador.telefone%type
, p_idOrganizacao Organizacao.idOrganizacao%type)
is
 v_id utilizador.idUtilizador%type;
begin
select idUtilizador into v_id from Utilizador where email = p_email;
insert into Colaborador(idColaborador, idOrganizacao, nome, funcao, telefone, email) 
values(v_id, p_idOrganizacao, p_nome, 'gestor', p_telefone, p_email);
update Organizacao set idgestor = v_id where idOrganizacao = p_idOrganizacao;
end;
/


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

create or replace procedure createGrauProficiencia( 
    p_idCompetenciaTecnica competenciaTecnica.idCompetenciaTecnica%type,
    p_nivel GrauProficiencia.nivel%type,
    p_designacao GrauProficiencia.designacao%type) 
    is
    begin 
insert into GrauProficiencia(idCompetenciaTecnica, nivel, designacao) values (p_idCompetenciaTecnica, p_nivel, p_designacao); 
end;
/

create or replace function getOrganizacaoByEmailColaborador (p_email Colaborador.Email%type) return int 
is
v_id int;
begin
select idOrganizacao into v_id from Colaborador where email = p_email;
return v_id;
end;
/

create or replace procedure createTarefa(
p_refTarefa Tarefa.referenciaTarefa%type,
p_designacao Tarefa.designacao%type,
p_descInformal Tarefa.descricaoInformal%type,
p_descTecnica Tarefa.descricaoTecnica%type,
p_duracao Tarefa.estimativaDuracao%type,
p_custo Tarefa.estimativaCusto%type,
p_idOrganizacao Organizacao.idOrganizacao%type,
p_idCategoria CategoriaTarefa.idCategoria%type)
is 
begin
    insert into Tarefa(referenciaTarefa, idOrganizacao, idCategoria, idEstadoTarefa, designacao, descricaoInformal, descricaoTecnica, estimativaDuracao, estimativaCusto) 
    values (p_refTarefa, p_idOrganizacao, p_idCategoria, 1, p_designacao, p_descInformal, p_descTecnica, p_duracao, p_custo);
end;
/

create or replace procedure createAdministrativo(
 p_nome utilizador.nome%type,
 p_email utilizador.email%type)
is
 v_id utilizador.idUtilizador%type;
begin
select idUtilizador into v_id from Utilizador where email = p_email;
 insert into Administrativo(idAdministrativo, nome, email) 
 values(v_id, p_nome, p_email);
end;
/

create or replace procedure createFreelancer(
 p_nome utilizador.nome%type
, p_email utilizador.email%type
, p_palavraPasse utilizador.palavraPasse%type
, p_telefone Freelancer.telefone%type
, p_nif Freelancer.NIF%type
)
is
 v_id utilizador.idUtilizador%type;
begin
select idUtilizador into v_id from Utilizador where email = p_email;
insert into Freelancer(idFreelancer, NIF, nome, telefone, email) 
values(v_id, p_NIF, p_nome, p_telefone, p_email);
end;
/

create or replace function getFreelancerIDByEmail (p_email Freelancer.Email%type) return int 
is
v_id int;
begin
select idFreelancer into v_id from Freelancer where email = p_email;
return v_id;
end;
/

create or replace procedure createHabilitacaoAcademica(
  p_idFreelancer Freelancer.idFreelancer%type
, p_grau HabilitacaoAcademica.grau%type
, p_designacaoCurso HabilitacaoAcademica.designacaoCurso%type
, p_nomeInstituicao HabilitacaoAcademica.nomeInstituicao%type
, p_mediaCurso HabilitacaoAcademica.mediaCurso%type
)
is
begin
insert into HabilitacaoAcademica(idFreelancer, grau, designacaoCurso, nomeInstituicao, mediaCurso) 
 values(p_idFreelancer, p_grau, p_designacaoCurso, p_nomeInstituicao, p_mediaCurso);

end;
/

create or replace procedure createReconhecimentoCT(
  p_idFreelancer Utilizador.idUtilizador%type
, p_idCompetenciaTecnica CompetenciaTecnica.idCompetenciaTecnica%type
, p_nivelGrauProficiencia GrauProficiencia.nivel%type
, p_dataReconhecimento ReconhecimentoCT.dataReconhecimento%type
)
is
begin
insert into ReconhecimentoCT(idFreelancer, idCompetenciaTecnica, nivelGrauProficiencia, dataReconhecimento) 
 values(p_idFreelancer, p_idCompetenciaTecnica, p_nivelGrauProficiencia, p_dataReconhecimento);

end;
/


create or replace procedure createAnuncio( 

    p_idTarefa tarefa.IdTarefa%type,
    p_idTipoRegimento tipoRegimento.idTipoRegimento%type,
    p_dataInicioPub anuncio.dataInicioPublicitacao%type,
    p_dataFimPub anuncio.dataFimPublicitacao%type,
    p_dataInicioCand anuncio.dataInicioCandidatura%type,
    p_dataFimCand anuncio.dataFimCandidatura%type,
    p_dataInicioSer anuncio.dataInicioSeriacao%type,
    p_dataFimSer anuncio.dataFimSeriacao%type,
    p_idColaborador Colaborador.idcolaborador%type) 
    is
    begin 
insert into Anuncio(IdTarefa, idTipoRegimento, dataInicioPublicitacao, dataFimPublicitacao, dataInicioCandidatura, dataFimCandidatura,
                    dataInicioSeriacao, dataFimSeriacao, idColaborador) values (p_idTarefa, p_idTipoRegimento, p_dataInicioPub, p_dataFimPub,
                    p_dataInicioCand, p_dataFimCand, p_dataInicioSer, p_dataFimSer,p_idColaborador ); 
end;
/


create or replace function getAnunciobyRefTarefa_IdOrg(
    p_refTarefa in tarefa.ReferenciaTarefa%type,
    p_IdOrg in organizacao.IdOrganizacao%type
)
return int 
is
v_idT int;
v_idA int;
begin
select idTarefa into v_idT from Tarefa where ReferenciaTarefa = p_refTarefa and IdOrganizacao = p_IdOrg;
select idAnuncio into v_idA from Anuncio where IdTarefa = v_idT;
return v_idA;
end;
/

create or replace procedure createUtilizador(
    p_username Utilizador.nome%type,
    p_email Utilizador.email%type,
    p_palavraPasse Utilizador.palavraPasse%type
) 
is
begin 
insert into Utilizador(nome, email, palavraPasse) values (p_username, p_email, p_palavraPasse); 
end;
/

create or replace procedure createAtribuicao( 
    p_idFreelancer Freelancer.idFreelancer%type, 
    p_idAnuncio Anuncio.idAnuncio%type, 
    p_data_inicio Atribuicao.data_inicio%type, 
    p_data_fim Atribuicao.data_fim%type, 
    p_data_atribuicao Atribuicao.data_atribuicao%type    
)
is
v_idOrganizacao Organizacao.idOrganizacao%type;
v_designacao_tarefa Tarefa.designacao%type;
v_idTarefa Tarefa.idTarefa%type;
v_valor_remuneracao Atribuicao.valor_remuneracao%type; 
begin
    select idTarefa into v_idTarefa from Anuncio where idAnuncio = p_idAnuncio;
    select designacao into v_designacao_tarefa from tarefa where idTarefa = v_idTarefa;
    select idOrganizacao into v_idOrganizacao from tarefa where  idTarefa = v_idTarefa;
    select valorPretendido into v_valor_remuneracao from candidatura where idAnuncio = p_idAnuncio and idFreelancer = p_idFreelancer;
    insert into Atribuicao(idOrganizacao, idFreelancer, idAnuncio, designacao_tarefa, data_inicio, data_fim, data_atribuicao, valor_remuneracao, idTarefa)
    values (v_idOrganizacao, p_idFreelancer, p_idAnuncio, v_descricao_tarefa, p_data_inicio, p_data_fim, p_data_atribuicao, v_valor_remuneracao, v_idTarefa);
end;
