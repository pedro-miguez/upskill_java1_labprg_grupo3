package api;

import dto.ErroDTO;
import network.*;
import utils.Response;
import xml.XmlHandler;

public class PessoasController {

    public static Response getPessoas(String uri) {
        Response response = null;
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.GET, uri);
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);
        switch (httpResponse.getStatus()) {
            case HttpStatusCode.OK:
                response = new Response(HttpStatusCode.OK, httpResponse.getBody());
                break;
            case HttpStatusCode.Conflict:
                ErroDTO erroDTO = XmlHandler.deSerializeXML2ErroDTO(httpResponse.getBody());
                response = new Response(HttpStatusCode.Conflict, erroDTO.getMensagemErro());
                break;
        }
        return response;
    }
/*
    public static Response getPessoa(String uri) {
        Response response = null;
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.GET, uri);
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);
        switch (httpResponse.getStatus()) {
            case HttpStatusCode.OK:
                PessoaDTO pessoaDTO = XmlHandler.deSerializeXML2PessoaDTO(httpResponse.getBody());
                Pessoa pessoa = Mapper.pessoaDTO2Pessoa(pessoaDTO);
                response = new Response(HttpStatusCode.OK, pessoa);
                break;
            case HttpStatusCode.Conflict:
                ErroDTO erroDTO = XmlHandler.deSerializeXML2ErroDTO(httpResponse.getBody());
                response = new Response(HttpStatusCode.Conflict, erroDTO.getMensagemErro());
                break;
        }
        return response;
    }

    public static Response addPessoa(String uri, PessoaDTO pessoaDTO) {
        Response response = null;
        final String body = XmlHandler.serializePessoaDTO2XML(pessoaDTO);


        HttpRequest httpRequest = new HttpRequest(HttpRequestType.POST, uri);
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);
        switch (httpResponse.getStatus()) {
            case HttpStatusCode.Created:
                response = new Response(HttpStatusCode.Created, null);
                break;
            case HttpStatusCode.Conflict:
                ErroDTO erroDTO = XmlHandler.deSerializeXML2ErroDTO(httpResponse.getBody());
                response = new Response(HttpStatusCode.Conflict, erroDTO.getMensagemErro());
                break;
        }
        return response;
    }

    public static Response updatePessoa(String uri, PessoaDTO pessoaDTO) {
        Response response = null;
        final String body = XmlHandler.serializePessoaDTO2XML(pessoaDTO);
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.PUT, uri);
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
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.DELETE, uri);
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
