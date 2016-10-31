/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.redesII.funcao;

import java.util.List;

/**
 *
 * @author arthurhoch
 * @param <T>
 */
public abstract class Funcao<T> {

    public List<Object> parametros;
    
    private String nome;

    public abstract String executar(String mensagem);
    
    public String getNome() { return nome; }
    
    public void setNome(String nome) { this.nome = nome; }

}
