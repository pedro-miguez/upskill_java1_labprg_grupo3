create or replace procedure createCandidatura( 
    p_idAnuncio Anuncio.idAnuncio%type,
    p_idFreelancer Freelancer.idFreelancer%type,
    p_dataCandidatura Candidatura.dataCandidatura%type,
    p_valorPretendido Candidatura.valorPretendido%type,
    p_nrDias Candidatura.nrDias%type,
    p_txtApresentacao Candidatura.txtApresentacao%type,
    p_txtMotivacao Candidatura.txtMotivacao%type
    ) 
    is
    begin 
insert into Candidatura(idAnuncio, idFreelancer, dataCandidatura, valorPretendido, nrDias, txtApresentacao, txtMotivacao) values (p_idAnuncio, p_idFreelancer, p_dataCandidatura, p_valorPretendido,
                    p_nrDias, p_txtApresentacao, p_txtMotivacao); 
end;
/