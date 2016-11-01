/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.redesII.funcoes;

import br.unisc.redesII.funcao.Funcao;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author arthurhoch
 */
public class Hash extends Funcao {

    @Override
    public String executar(String mensagem) {

        String possivel = "É possivel que seja: ";
        int tamanhoInicial = possivel.length();

        if (testBase64(mensagem)) {
            possivel += "Base64 ";
        }

        if (testURLEncode(mensagem)) {
            possivel += "Url codificada ";
        }

        if (testBinary(mensagem)) {
            possivel += "Binario ";
        }

        if (testHex(mensagem)) {
            possivel += "Hexadecimal ";
        }

        if (testeRot13(mensagem)) {
            possivel += "Rot13 ";
        }

        if (testeMd5(mensagem)) {
            possivel += "Md5 ";
        }

        if (tamanhoInicial != possivel.length()) {
            return possivel;
        }

        return "Hash não encontrada";
    }

    private boolean testeMd5(String mensagem) {
        return mensagem.length() == 32;
    }

    private boolean testeRot13(String mensagem) {
        return rot13(mensagem).equals(rot13(unrot13(mensagem)));

    }

    private String unrot13(String mensagem) {
        return rot13(mensagem);
    }

    private String rot13(String mensagem) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mensagem.length(); i++) {
            char c = mensagem.charAt(i);
            if (c >= 'a' && c <= 'm') {
                c += 13;
            } else if (c >= 'A' && c <= 'M') {
                c += 13;
            } else if (c >= 'n' && c <= 'z') {
                c -= 13;
            } else if (c >= 'N' && c <= 'Z') {
                c -= 13;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    private boolean testBase64(String mensagem) {
        String pattern = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(mensagem);
        return m.find();
    }

    private boolean testURLEncode(String mensagem) {

        if (mensagem.contains("%")) {
            try {
                return mensagem.equals(java.net.URLDecoder.decode(mensagem, "UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Hash.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    private boolean testBinary(String mensagem) {

        int count = 0;
        mensagem = mensagem.trim();
        mensagem = mensagem.replace(" ", "");

        System.out.println(mensagem);

        if ((mensagem.length() >= 8) && 0 == (mensagem.length() % 2)) {
            for (int i = 0; i < mensagem.length(); i++) {

                char c = mensagem.charAt(i);

                switch (c) {
                    case '0':
                    case '1':
                        count++;
                        break;
                    default:
                        break;
                }

            }
        }

        return count == mensagem.length();

    }

    private boolean testHex(String mensagem) {

        mensagem = mensagem.trim();
        mensagem = mensagem.toUpperCase();

        String hexDigits = "0123456789ABCDEF";

        List<String> hexs;

        if ((hexs = split2(mensagem)) != null) {

            for (String hex : hexs) {

                for (int i = 0; i < hex.length(); i++) {
                    if (!hexDigits.contains(hex.substring(0 + i, 1 + i))) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;

    }

    private List<String> split2(String mensagem) {

        if (mensagem.length() % 2 == 0) {

            List<String> strings = new LinkedList<>();
            int index = 0;
            while (index < mensagem.length()) {
                strings.add(mensagem.substring(index, Math.min(index + 4, mensagem.length())));
                index += 2;
            }
            return strings;
        }

        return null;
    }

}
