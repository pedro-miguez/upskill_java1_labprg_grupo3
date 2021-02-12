
create function funcGetAreasAtividade return sys_refcursor is
v_ret sys_refcursor;
begin
    open v_ret for
        select * from AreaAtividade
    return v_ret;
end;
/

create or replace function createCompetenciaTecnica(
p_idCompetenciaTecnica competenciaTecnica.idCompetenciaTecnica%type,
p_descricao competenciaTecnica.descricao%type,
p_descDetalhada competenciaTecnica.descDetalhada%type,
p_areaAtividade AreaAtividade.idAreaAtividade%type) 
return competenciaTecnica.idCompetenciaTecnica%type
is 
v_id competenciaTecnica.idCompetenciaTecnica%type;
begin
    insert into CompetenciaTecnica(idCompetenciaTecnica, areaAtividade, descricao, descDetalhada) values (p_idCompetenciaTecnica, p_areaAtividade, p_descricao, p_descDetalhada) 
    returning idCompetenciaTecnica into v_id;
return v_id;
end;
/
