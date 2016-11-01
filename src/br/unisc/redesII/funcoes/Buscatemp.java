/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.redesII.funcoes;

import br.unisc.redesII.funcao.Funcao;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Douglas
 */
public class Buscatemp extends Funcao {

    @Override
    public String executar(String mensagem) {
        String temp1 = null;
        mensagem = mensagem.trim();
        mensagem = mensagem.replace(" ", "+");//Encruzilhada+do+Sul por exemplo
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+mensagem+"&units=metric&appid=b711e13484283037ca7a1f5dc1b4b5b7&lang=pt");
            Reader br = new InputStreamReader(url.openStream());
            //JSONParser parser = new JSONParser();
            JSONObject jsonObjeto = (JSONObject) new JSONParser().parse(br);
            //System.out.println(jsonObjeto);
            String temp = jsonObjeto.get("main").toString();
            temp1 = temp.substring(8, 13);
        } catch (Exception e) {
            System.out.println("Erro no Buscatemp");        
        }
        return temp1;
    }

}