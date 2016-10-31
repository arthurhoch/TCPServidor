/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.redesII.funcoes;

import br.unisc.redesII.funcao.Funcao;

/**
 *
 * @author arthurhoch
 */
public class Autores extends Funcao {
    
    private static final String AUTORES = "Arthur Hoch, Douglas Alves";

    @Override
    public String executar(String mensagem) {
        return AUTORES;
    }
    
}
