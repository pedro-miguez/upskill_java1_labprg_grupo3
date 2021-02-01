package application;

import dto.ErroDTO;
import network.HttpConnection;
import network.HttpRequest;
import network.HttpRequestType;
import network.HttpResponse;
import network.HttpStatusCode;
import utils.Response;
import xml.XmlHandler;

/**
 * @author pbs
 */
public class UsersAPI {

    private static final String appKey =



/*
    public static Response updatePessoa(String uri, PessoaDTO pessoaDTO) {
        Response response = null;
        final String body = XmlHandler.serializePessoaDTO2XML(pessoaDTO);
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.PUT, uri, body);
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);
        switch (httpResponse.getStatus()) {
            case HttpStatusCode.OK:
                response = new Response(HttpStatusCode.OK, null);
                break;
            case HttpStatusCode.Conflict:
                ErroDTO erroDTO = XmlHandler.deSerializeXML2ErroDTO(httpResponse.getBody());
                response = new Response(HttpStatusCode.Conflict, erroDTO.getMensagemErro());
                break;
        }
        return response;
    }

    public static Response deletePessoa(String uri) {
        Response response = null;
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.DELETE, uri, "");
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);
        switch (httpResponse.getStatus()) {
            case HttpStatusCode.OK:
                response = new Response(HttpStatusCode.OK, null);
                break;
            case HttpStatusCode.Conflict:
                ErroDTO erroDTO = XmlHandler.deSerializeXML2ErroDTO(httpResponse.getBody());
                response = new Response(HttpStatusCode.Conflict, erroDTO.getMensagemErro());
                break;
        }
        return response;
    }*/
}