/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.RedesII;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Douglas
 */
public class TCPServidor {
    public static void main(String[] args) throws IOException {
        
        System.out.println("Inicializando servidor");
        
        ServerSocket server =  new ServerSocket(2525);
        
        System.out.println("Aguardando conexão...");
        
        while (true){
            //por meio do socket se dá a comunicação cliente servidor
            Socket socket = server.accept();
            
            Runnable r = new Tratamento(socket,server);
            new Thread(r).start();
            
        }
        
        
    }

}
