/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.redesII.funcoes;

import br.unisc.redesII.funcao.Funcao;
import java.io.File;

/**
 *
 * @author Douglas
 */
public class Rmvarq extends Funcao {

    @Override
    public String executar(String mensagem) {
        System.out.println("MENSAGEM AQUI: " + mensagem);
        String msg;
        File f = new File(mensagem);
        if (f.delete()) {
            msg = "Arquivo removido com sucesso";
        } else {
            msg = "Não foi possível remover o arquivo";
        }
        return msg;

    }

}
