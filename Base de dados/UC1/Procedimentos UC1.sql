 - createOrganização()
 	- cria organização sem idGestor
 
 - createUtilizadorGestor()
 	- cria utilizador
 	- cria gestor (com idUtilizador, com idOrganizacao)
 	- associa idGestor à organização


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
 insert into Organizacao(idgestor) values (v_id) where idOrganizacao = p_idOrganizacao;
end;
/
