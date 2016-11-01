/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.redesII.funcoes;

import br.unisc.redesII.funcao.Funcao;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import java.io.Reader;
import java.io.StringReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

/**
 *
 * @author arthurhoch
 */
public class Cotacao extends Funcao {

    private final String LINK = "http://api.promasters.net.br/cotacao/v1/valores?alt=xml";

    @Override
    public String executar(String moeda) {

        try {
            String xml = Jsoup.connect(LINK).get().html();

            Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

            try {
                if (hasElement(xml, moeda)) {

                    Element element = doc.select(moeda).first();

                    String valor = element.getElementsByTag("valor").text();

                    if (valor != null) {
                        return "O valor da moeda é: " + valor;
                    }
                }
            } catch (XMLStreamException ex) {
                Logger.getLogger(Cotacao.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (IOException ex) {
            Logger.getLogger(Cotacao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "Use example: USD/EUR/ARS/GBP/BTC";
    }

    public static boolean hasElement(String document, String localName)
            throws XMLStreamException {
        Reader reader = new StringReader(document);
        XMLStreamReader xml = XMLInputFactory.newFactory()
                .createXMLStreamReader(reader);
        try {
            while (xml.hasNext()) {
                if (xml.next() == XMLStreamConstants.START_ELEMENT
                        && localName.equals(xml.getLocalName())) {
                    return true;
                }
            }
        } finally {
            xml.close();
        }
        return false;
    }

}
