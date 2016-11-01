/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.redesII.outros;

import br.unisc.redesII.outros.Log;
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
    private final Log log;

    public Tratamento(Socket socket, ServerSocket sc) {
        this.sc = sc;
        this.socket = socket;
        this.loader = new Loader();
        this.log = new Log();
    }

    @Override
    public void run() {

        log.info("CONEXÃO", "ESTABELECIDA");
        try {
            //INPUT DO SERVER ESTÁ CONECTADO AO OUTPUT DO CLIENTE E VICE VERSA
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            PrintStream out;
            // TRANSFORMA O OUTPUTSTREAM BYTE EM UMA STRING
            try ( //TRANSFORMA UM INPUTSTREAM BYTE EM UMA STRING
                    BufferedReader in = new BufferedReader(new InputStreamReader(input))) {
                // TRANSFORMA O OUTPUTSTREAM BYTE EM UMA STRING
                out = new PrintStream(output);
                while (true) {
                    String mensagem = in.readLine();
                    if ("\\FIM".equals(mensagem)) {
                        break;
                    } else {
                        out.println(loader.run(mensagem));
                    }

                    log.info("MENSAGEM RECEBIDA", "DE [" + socket.getInetAddress().getHostName() + "]");
                    log.info("MENSAGEM RECEBIDA", mensagem);
                }

                log.info("CONEXAO", "ENCERRADA");
            }
            out.close();
            socket.close();
            log.info("SERVIDOR", "ENCERRADO");

            sc.close();

        } catch (Exception e) {
        }

    }

}
