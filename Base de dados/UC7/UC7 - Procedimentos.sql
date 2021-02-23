- getCategoriasTarefa() 

select * from CategoriaTarefa;


create or replace procedure createUtilizadorFreelancer(
 p_nome utilizador.nome%type
, p_email utilizador.email%type
, p_palavraPasse utilizador.palavraPasse%type
, p_telefone Freelancer.telefone%type
, p_nif Freelancer.NIF%type
)
is
 v_id utilizador.idUtilizador%type;
begin
 insert into Utilizador(nome, email, palavraPasse, designacao)
 values(p_nome, p_email, p_palavraPasse, 'freelancer') returning idUtilizador into v_id;
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

create or replace procedure ReconhecimentoCT(
  p_idFreelancer Freelancer.idFreelancer%type
, p_idCompetenciaTecnica CompetenciaTecnica.idCompetenciaTecnica%type
, p_nivelGrauProficiencia GrauProficiencia.nivel%type
, p_dataReconhecimento ReconhecimentoCT.dataReconhecimento%type
)
is
begin
insert into ReconhecimentoCT(idCompetenciaTecnica, idFreelancer, nivelGrauProficiencia, dataReconhecimento) 
 values(p_idFreelancer, p_idCompetenciaTecnica, p_nivelGrauProficiencia, p_dataReconhecimento);

end;
/
 






