/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.redesII.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Douglas
 */
public class TCPServidor {

    public static void main(String[] args) throws IOException {
        
        ServerSocket server = new ServerSocket(2525);

        System.out.println("Aguardando conexão....");

        while (true) {
            //por meio do socket se dá a comunicação cliente servidor
            Socket socket = server.accept();

            Runnable r = new Tratamento(socket, server);
            new Thread(r).start();

        }

    }
}
