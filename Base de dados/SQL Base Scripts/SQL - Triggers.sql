create or replace trigger trgAnuncio after insert on Anuncio for each row
declare
 v_datainiciopub date;
 v_datafimpub date;
 v_datainiciocandidatura date;
 v_datafimcandidatura date;
 v_datainicioseri date;
 v_datafimseri date;
begin
 v_datainiciopub := trunc(:new.dataInicioPublicitacao);
 v_datafimpub := trunc(:new.dataFimPublicitacao);
 v_datainiciocandidatura := trunc(:new.dataInicioCandidatura);
 v_datafimcandidatura := trunc(:new.dataFimCandidatura);
 v_datainicioseri := trunc(:new.dataInicioSeriacao);
 v_datafimseri := trunc(:new.dataFimSeriacao);

 if v_datafimpub <= v_datainiciopub then
    raise_application_error(-20000, 'Erro na data de fim de publicitacao');
 end if;
 if v_datainiciocandidatura > v_datafimpub and v_datainiciocandidatura < v_datainiciopub  then
    raise_application_error(-20000, 'Erro na data de inicio de candidatura');
    end if;
 if v_datafimcandidatura <= v_datainiciocandidatura and v_datafimcandidatura > v_datafimpub and v_datafimcandidatura < v_datainiciopub then
    raise_application_error(-20000, 'Erro na data de fim de candidatura');
    end if;
 if v_datainicioseri <= v_datafimcandidatura and v_datainicioseri > v_datafimpub and v_datainicioseri < v_datainiciopub then
    raise_application_error(-20000, 'Erro na data de inicio de seriacao');
    end if;
 if v_datafimseri <= v_datainicioseri and v_datafimseri > v_datafimpub and v_datafimseri < v_datainiciopub then
    raise_application_error(-20000, 'Erro na data de fim de seriacao');
 end if;
end;
/

create or replace trigger trgProcessoSeriacao after insert on ProcessoSeriacao for each row

declare
 v_dataRealizacao date;
 v_datainicioseri date;
 v_datafimseri date;

begin
 SELECT dataInicioSeriacao INTO v_datainicioseri from Anuncio where idAnuncio = :new.idAnuncio;
 SELECT dataFimSeriacao INTO v_datafimseri from Anuncio where idAnuncio = :new.idAnuncio;

 if v_dataRealizacao <= v_datainicioseri then
    raise_application_error(-20000, 'Erro na data de realizacao (inferior à data de inicio de seriação do anuncio)');
    end if;
 if v_dataRealizacao >= v_datafimseri then
    raise_application_error(-20000, 'Erro na data de realizacao (superior à data de fim de seriação do anuncio)');
 end if;
end;
/

create or replace trigger trgAnuncioTarefa after insert on Anuncio for each row

declare 
begin
UPDATE Tarefa SET idEstadoTarefa = 2 WHERE idTarefa = :new.idTarefa;
end;
/


create or replace trigger trgidEstadoAnuncio after startup on database for each row

declare
v_datainicioseri date;
v_datafimseri date;

begin
select dataInicioSeriacao into v_datainicioseri from Anuncio;
select dataFimSeriacao into v_datafimseri from Anuncio;

update Anuncio SET idEstadoAnuncio = 2 where database_event between v_datainicioseri and v_datafimseri;

end;
/


create or replace trigger trgAnuncioIdColaborador after insert on Anuncio for each row

declare
 v_idOrg1 number;
 v_idOrg2 number;
 

begin
 SELECT idOrganizacao INTO v_idOrg1 from Colaborador where idColaborador = :new.idColaborador;
 SELECT idOrganizacao INTO v_idOrg2 from Tarefa where idTarefa = :new.idTarefa;

 if v_idOrg1 != v_idOrg2 then
    raise_application_error(-20000, 'Colaborador não pertence à organizacao correcta');
    end if;
end;
/

create or replace trigger trgAtribuicaoTarefa after insert on Atribuicao for each row

declare 
begin
UPDATE Tarefa SET idEstadoTarefa = 3 WHERE idTarefa = :new.idTarefa;
end;
/