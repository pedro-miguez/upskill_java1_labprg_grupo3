- getCategoriasTarefa() 

select * from CategoriaTarefa;


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