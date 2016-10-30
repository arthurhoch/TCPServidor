/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.redesII;

import java.io.BufferedReader;
import java.io.IOException;
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

    private Socket socket;
    private ServerSocket sc;

    public Tratamento(Socket socket, ServerSocket sc) {
        this.sc = sc;
        this.socket = socket;
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
                if ("FIM".equals(mensagem)) {
                    break;
                } else if ("\\autores".equals(mensagem)) {
                    String autores = Easy.autores;
                    out.println(autores);
                } else if ("\\datahora".equals(mensagem)) {
                    Easy e = new Easy();
                    String datahora = e.getDataHora();
                    out.println(datahora);
                } else if ("\\rndemoji".equals(mensagem)) {
                    Easy e = new Easy();
                    String emoji = e.getEmoji();
                    out.println(emoji);
                } else {
                    out.println("Mensagem invalida");
                }

                System.out.println("MENSAGEM RECEBIDA DO CLIENTE[" + socket.getInetAddress().getHostName() + "]" + mensagem);

                //so exibe a mensagem do cliente pode apagar depois
                //out.println(mensagem);
                //out.flush();
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
