/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.redesII.funcoes;

import br.unisc.redesII.funcao.Funcao;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author arthurhoch
 */
public class Rndnoticia extends Funcao {
    
    private final Random rand = new Random();

    @Override
    public String executar(String mensagem) {
        
        String site = rndSite();
        System.out.println(site);
        
        return getRndPage(rndSite());
    }

    public String getRndPage(String url) {
        Document doc;

        List<String> listaLinks = new LinkedList<>();

        try {
            doc = Jsoup.connect(url).get();
            String asc = null;
            Elements links = doc.select("a[href]");
            for (Element link : links) {
                asc = (link.attr("href"));
                if (asc.contains("http")) {

                    boolean contem = false;
                    if (asc.contains(".html") || asc.contains(".xhtml")) {
                        contem = true;
                    }

                    if (contem) {
                        listaLinks.add(asc);
                    }
                }
            }
        } catch (Exception e) {
            System.err.printf("In: " + url, e);
        }

        return listaLinks.get(rand.nextInt(listaLinks.size()));
    }

    private String rndSite() {

        

        List<String> sites = new LinkedList<>();
        sites.add("http://g1.globo.com/");
        sites.add("http://www.r7.com/");
        sites.add("http://www.estadao.com.br/");
        sites.add("https://br.noticias.yahoo.com/");

        return sites.get(rand.nextInt(sites.size()));
    }

}
