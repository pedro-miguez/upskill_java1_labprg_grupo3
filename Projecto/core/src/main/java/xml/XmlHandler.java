package xml;

import domain.User;
import dto.ErroDTO;
import dto.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XmlHandler {

    public static String serializeErroDTO2XML(ErroDTO erroDTO) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            String xml = xmlMapper.writeValueAsString(erroDTO);
            return xml;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(XmlHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ErroDTO deSerializeXML2ErroDTO(String xmlData) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            ErroDTO data = xmlMapper.readValue(xmlData, ErroDTO.class);
            return data;
        } catch (IOException ex) {
            Logger.getLogger(XmlHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String serializeUserDTO2XML(UserDTO userDTO) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            String xml = xmlMapper.writeValueAsString(userDTO);
            return xml;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(XmlHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static UserDTO deSerializeXML2PessoaDTO(String xmlData) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            UserDTO data = xmlMapper.readValue(xmlData, UserDTO.class);
            return data;
        } catch (IOException ex) {
            Logger.getLogger(XmlHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}