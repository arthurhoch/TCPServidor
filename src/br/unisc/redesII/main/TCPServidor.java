/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.redesII.main;

import br.unisc.redesII.tratamento.Servidor;
import java.io.IOException;

/**
 *
 * @author Douglas
 */
public class TCPServidor {

    private final static int PORTA = 2525;

    public static void main(String[] args) throws IOException {

        Servidor servidor = new Servidor(PORTA);
        servidor.inicializar();

    }
}
