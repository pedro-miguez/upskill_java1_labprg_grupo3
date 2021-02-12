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
 if v_datainiciocandidatura <= v_datafimpub then
    raise_application_error(-20000, 'Erro na data de inicio de candidatura');
    end if;
 if v_datafimcandidatura <= v_datainiciocandidatura then
    raise_application_error(-20000, 'Erro na data de fim de candidatura');
    end if;
 if v_datainicioseri <= v_datafimcandidatura then
    raise_application_error(-20000, 'Erro na data de inicio de seriacao');
    end if;
 if v_datafimseri <= v_datainicioseri then
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