create or replace procedure createAnuncio( 

    p_idTarefa tarefa.IdTarefa%type,
    p_dataInicioPub anuncio.dataInicioPublicitacao%type,
    p_dataFimPub anuncio.dataFimPublicitacao%type,
    p_dataInicioCand anuncio.dataInicioCandidatura%type,
    p_dataFimCand anuncio.dataFimCandidatura%type,
    p_dataInicioSer anuncio.dataInicioSeriacao%type,
    p_dataFimSer anuncio.dataFimSeriacao%type) 
    is
    begin 
insert into Anuncio(IdTarefa, dataInicioPublicitacao, dataFimPublicitacao, dataInicioCandidatura, dataFimCandidatura,
                    dataInicioSeriacao, dataFimSeriacao) values (p_idTarefa, p_dataInicioPub, p_dataFimPub,
                    p_dataInicioCand, p_dataFimCand, p_dataInicioSer, p_dataFimSer); 
end;
/

create or replace function getAnunciobyRefTarefa_IdOrg in p_refTarefa in p_IdOrg(

    p_refTarefa tarefa.ReferenciaTarefa%type,
    p_IdOrg organizacao.IdOrganizacao%type

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