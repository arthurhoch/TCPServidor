/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.redesII.funcoes;

import br.unisc.redesII.funcao.Funcao;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arthurhoch
 */
public class Base64e extends Funcao {

    @Override
    public String executar(String mensagem) {
        try {
            return Base64.getEncoder().encodeToString(mensagem.getBytes("utf-8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Base64d.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Não deu certo :/";
    }

}
