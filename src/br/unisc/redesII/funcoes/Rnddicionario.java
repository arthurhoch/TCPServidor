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
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

/**
 *
 * @author arthurhoch
 */
public class Rnddicionario extends Funcao {

    private final String LINK = "http://dicionario-aberto.net/search-xml/";

    @Override
    public String executar(String mensagem) {
        String xml = null;
        try {
            xml = Jsoup.connect(LINK + mensagem).get().html();
        } catch (IOException ex) {
            Logger.getLogger(Rnddicionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

        Element element = doc.select("sense").first();
        String def = element.getElementsByTag("def").text();
        if (def != null) {
            return def;
        }

        return "Palavra não encontrada.";
    }

}
