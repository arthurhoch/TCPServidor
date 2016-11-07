/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.redesII.funcao;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author arthurhoch
 */
public class Loader {

    private static final String PACKPAGE = "br.unisc.redesII.funcoes.";
    private final List<Funcao> funcoes;

    public Loader() {
        this.funcoes = new LinkedList<>();

    }

    public String run(String input) {

        int div = divFirstSpace(input);

        String nome;
        String parametros;

        if (div != -1) {
            nome = input.substring(0, div);
            parametros = input.substring(div + 1, input.length());
        } else {
            nome = input;
            parametros = "";
        }

        for (Funcao funcao : funcoes) {
            if (funcao.getNome().compareToIgnoreCase(nome) == 0) {
                return funcao.executar(parametros);
            }
        }

        Funcao funcao = loader(nome);

        if (funcao != null) {
            funcoes.add(funcao);
            return funcao.executar(parametros);
        }

        return "This function isn't here, my badass!!! :/";
    }

    private int divFirstSpace(String input) {

        for (int i = 0; i < input.length(); i++) {
            if (i > 0 && input.charAt(i) == ' ') {
                return i;
            }
        }

        return -1;
    }

    private Funcao loader(String name) {

        String className = Character.toUpperCase(name.charAt(0)) + name.substring(1);

        Funcao f = null;

        try {
            Class cls = Class.forName(PACKPAGE + className);
            Object obj = cls.newInstance();

            f = (Funcao) obj;
            f.setNome(name);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException ex) {
            //Logger.getLogger(TCPServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return f;
    }

}
