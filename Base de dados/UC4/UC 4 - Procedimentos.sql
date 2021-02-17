--Obter Áreas Atividade

select * from AreaAtividade;

--Procedimento para criar Competência Técnica
create or replace procedure createCompetenciaTecnica(
p_idCompetenciaTecnica competenciaTecnica.idCompetenciaTecnica%type,
p_descricao competenciaTecnica.descricaoBreve%type,
p_descDetalhada competenciaTecnica.descricaoDetalhada%type,
p_areaAtividade AreaAtividade.idAreaAtividade%type)
is 
 v_count int;   
 ex_areaAtividade exception;
 v_id competenciaTecnica.idCompetenciaTecnica%type;
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

create or replace procedure createGrauProficiencia( 
    p_idCompetenciaTecnica competenciaTecnica.idCompetenciaTecnica%type,
    p_nivel GrauProficiencia.nivel%type,
    p_designacao GrauProficiencia.designacao%type) 
    is
    begin 
insert into GrauProficiencia(idCompetenciaTecnica, nivel, designacao) values (p_idCompetenciaTecnica, p_nivel, p_designacao); 
end;
/


--Exemplo
--begin 
--createCompetenciaTecnica(1, 'Good with javadoc', 'Knows how to create javadoc files', 3);
--end;
--/


--Área de Atividade toString
--Função para obter Areas de Atividade como Cursor
create function funcGetAreasAtividade return sys_refcursor is
v_ret sys_refcursor;
begin
    open v_ret for
        select * from AreaAtividade
    return v_ret;
end;
/

--Procedimento para invocar a função
create or replace procedure procGetAreaAtividade is  
v_cur sys_refcursor;
r AreaAtividade%rowtype;
begin
    v_cur:=funcGetAreasAtividade;
    fetch v_cur into r;
    while v_cur%found loop
    dbms_output.put_line('Código Único: '||r.idAreaAtividade|| ', Descrição Breve: ' ||r.descricaoBreve|| ', Descrição Detalhada: ' ||r.descricaoDetalhada );
 fetch v_cur into r;
 end loop;
 close v_cur;
end;
/

--Método para testar o procedimento
--begin
--procGetAreaAtividade;
--end;
--/