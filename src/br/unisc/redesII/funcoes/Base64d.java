/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.redesII.funcoes;

import br.unisc.redesII.funcao.Funcao;
import java.util.Base64;

/**
 *
 * @author arthurhoch
 */
public class Base64d extends Funcao {

    @Override
    public String executar(String mensagem) {
        byte[] bytes = Base64.getDecoder().decode(mensagem);
        return new String(bytes);
    }

}
