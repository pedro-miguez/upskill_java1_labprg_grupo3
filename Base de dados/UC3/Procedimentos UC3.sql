 - getAreasAtividade - select * from AreaAtividade;

 - getCompetenciasTecnicasByAreaAtividade - select * from AreaAtividade where id_AreaAtividade = idAreaAtividade;


 - getAreaAtividadeToStringCompletoByIdAreaAtividade
create or replace function funcGetCompetenciasTecnicasByAreaAtividade1(p_idAreaAtividade AreaAtividade.idAreaAtividade%type) return
sys_refcursor
is
 v_ret sys_refcursor;
begin
 open v_ret for
 select *
 from CompetenciaTecnica where idAreaAtividade = p_idAreaAtividade;
 return v_ret;
 end;
/

create or replace procedure getCompetenciasTecnicasByAreaAtividade1(p_idAreaAtividade AreaAtividade.idAreaAtividade%type) 
is 
 v_cur sys_refcursor;
 r CompetenciaTecnica%rowtype;
begin
 v_cur := funcGetCompetenciasTecnicasByAreaAtividade1(p_idAreaAtividade);
 fetch v_cur into r;
 while v_cur%found loop
 dbms_output.put_line('ID Competência Técnica: '||r.idCompetenciaTecnica||', ID Área Atividade: '||r.idAreaAtividade||', Nome: '||r.nome||', Descrição Breve: '||r.descricaoBreve||', Descrição Detalhada: '||r.descricaoDetalhada);
 fetch v_cur into r;
 end loop;
 close v_cur;
end;
/

- createCaracterizacaoCompetenciaTecnica(ArrayList<CaraterizacaoCompetenciatecnica> lista)
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


- createCategoriaTarefa(idAreaAtividade)
create or replace procedure createCategoriaTarefa( 
    p_codunico AreaAtividade.IdAreaAtividade%type, 
    p_descricao CategoriaTarefa.descricao%type) 
    is
    begin 
insert into CategoriaTarefa(IdAreaAtividade, descricao) values (p_codunico, p_descricao); 
end;
/

begin
createAreaAtividade(4, 'Informática', 'Desenvolvimento de software');
end;
/



