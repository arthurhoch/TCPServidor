/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.redesII.funcoes;

import br.unisc.redesII.funcao.Funcao;
import java.util.Random;

/**
 *
 * @author arthurhoch
 */
public class Rndemoji extends Funcao {

    @Override
    public String executar(String mensagem) {

        String emoji = " ";

        String[] listaEmoji = {"(͡°͜ʖ͡°)", "▄︻̷̿┻̿═━一", "[̲̅$̲̅(̲̅5̲̅)̲̅$̲̅]", "﴾͡๏̯͡๏﴿ O'RLY?", "(õ_Ô)"};
        Random r = new Random();
        for (int x = 0; x < r.nextInt(listaEmoji.length); x++) {
            emoji = listaEmoji[r.nextInt(listaEmoji.length)];
        }
        return emoji;
    }

}
