-- UC2 - Definir Área de Atividade

-- createAreaAtividade()

create or replace procedure createAreaAtividade( 
    p_codunico AreaAtividade.IdAreaAtividade%type, 
    p_descricao AreaAtividade.descricaoBreve%type, 
    p_descdetailed AreaAtividade.descricaoDetalhada%type) 
    is
    begin 
insert into AreaAtividade(IdAreaAtividade, descricaoBreve, descricaoDetalhada) values (p_codunico, p_descricao, p_descdetailed); 
end;
/

begin
createAreaAtividade(4, 'Informática', 'Desenvolvimento de software');
end;
/

declare
    v_cod int;
begin
    v_cod := createAreaAtividade('AA000', 'Descricao a testar', 'Descricao detalhada');
    dbms_output.put_line('Nova area de atividade:'||v_cod);
end;
/

-- constraint codigo unico regex

--codigoUnico.matches("^([a-zA-Z]){3}(-\\d{2})?$")
