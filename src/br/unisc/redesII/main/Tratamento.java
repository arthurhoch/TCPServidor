/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.redesII.main;

import br.unisc.redesII.funcao.Loader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Douglas
 */
public class Tratamento implements Runnable {
    
    private final Socket socket;
    private final ServerSocket sc;
    private final Loader loader;
    
    public Tratamento(Socket socket, ServerSocket sc) {
        this.sc = sc;
        this.socket = socket;
        this.loader = new Loader();
    }
    
    @Override
    public void run() {
        
        System.out.println("CONEXÃO ESTABELECIDA!!!");
        try {
            //INPUT DO SERVER ESTÁ CONECTADO AO OUTPUT DO CLIENTE E VICE VERSA
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            //TRANSFORMA UM INPUTSTREAM BYTE EM UMA STRING
            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            // TRANSFORMA O OUTPUTSTREAM BYTE EM UMA STRING
            PrintStream out = new PrintStream(output);
            
            while (true) {
                String mensagem = in.readLine();
                if ("\\FIM".equals(mensagem)) {
                    break;
                } else {
                    out.println(loader.run(mensagem));
                }
                
                System.out.println("MENSAGEM RECEBIDA DO CLIENTE[" + socket.getInetAddress().getHostName() + "]" + mensagem);

            }
            System.out.println("ENCERRANDO A CONEXAO!!!");
            
            in.close();
            out.close();
            socket.close();
            System.out.println("ENCERRANDO SERVIDOR !!!");
            
            sc.close();
            
        } catch (Exception e) {
        }
        
    }
    
}
