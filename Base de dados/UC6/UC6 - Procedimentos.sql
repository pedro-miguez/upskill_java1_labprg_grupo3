- getCategoriasTarefa() 

select * from CategoriaTarefa;

- getOrganizacaoByEmailColaborador()
 
create or replace function getOrganizacaoByEmailColaborador (p_email Colaborador.Email%type) return int 
is
v_id int;
begin
select idOrganizacao into v_id from Colaborador where email = p_email;
return v_id;
end;
/

 
- createTarefa()

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


- createUtilizadorFreelancer()

create or replace procedure createUtilizadorFreelancer(
 p_nome utilizador.nome%type
, p_email utilizador.email%type
, p_palavraPasse utilizador.palavraPasse%type
, p_telefone Freelancer.telefone%type,
p_nif Freelancer.NIF%type)
is
 v_id utilizador.idUtilizador%type;
begin
 insert into Utilizador(nome, email, palavraPasse, designacao)
 values(p_nome, p_email, p_palavraPasse, 'freelancer') returning idUtilizador into v_id;
 insert into Freelancer(idFreelancer, NIF, nome, telefone, email) 
 values(v_id, p_NIF, p_nome, p_telefone, p_email);
end;
/

- createUtilizadorAdministrativo()

create or replace procedure createUtilizadorAdministrativo(
 p_nome utilizador.nome%type,
 p_email utilizador.email%type)
is
 v_id utilizador.idUtilizador%type;
begin
 insert into Utilizador(nome, email, palavraPasse, designacao)
 values(p_nome, p_email, p_palavraPasse, 'administrativo') returning idUtilizador into v_id;
 insert into Administrativo(idAdministrativo, nome, email) 
 values(v_id, p_nome, p_email);
end;
/