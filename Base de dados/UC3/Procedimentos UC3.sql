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

