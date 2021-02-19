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