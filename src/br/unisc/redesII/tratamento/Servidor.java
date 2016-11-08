/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.redesII.tratamento;

import br.unisc.redesII.funcao.Loader;
import br.unisc.redesII.outros.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arthurhoch
 */
public class Servidor {

    private final int porta;
    private final Log log = new Log();

    public Servidor(int porta) {
        this.porta = porta;
    }

    public void inicializar() {
        try {
            log.info("INCIANDO", "NA PORTA " + porta);
            ServerSocket server = new ServerSocket(porta);
            log.info("SERVIDOR", "AGUARDANDO CONEXAO....");

            while (!server.isClosed()) {
                //por meio do socket se dá a comunicação cliente servidor
                try {
                    Socket socket = server.accept();
                    log.info("CONEXÃO", "DE " + socket.getInetAddress().getHostName());

                    Runnable r = new Tratamento(socket, server);
                    new Thread(r).start();

                    //Runnable deve retorarnar o fim fo socket
                    //Fechar ServerSocket
                    //log.info("SERVIDOR", "ENCERRADO");
                } catch (IOException e) {
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public class Tratamento implements Runnable {

        private final Socket socket;
        private final ServerSocket server;
        private final Loader loader;
        private final Log log;
        private String mensagem;
        private String retorno;

        public Tratamento(Socket socket, ServerSocket server) {
            this.socket = socket;
            this.loader = new Loader();
            this.log = new Log();
            this.server = server;
        }

        @Override
        public void run() {

            log.info("CONEXÃO", "ESTABELECIDA", socket.getInetAddress().getHostName());
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
                        mensagem = in.readLine();

                        if (mensagem == null) {
                            break;
                        }

                        log.info("MENSAGEM R", socket.getInetAddress().getHostName(), mensagem);
                        mensagem = tratarInput(mensagem);
                        if (mensagem.startsWith("FIM")) {
                            break;
                        } else {
                            retorno = loader.run(mensagem);
                            System.out.println(retorno);
                            out.flush();
                            out.println(retorno);
                            log.info("MENSAGEM E", socket.getInetAddress().getHostName(), retorno);
                        }
                    }

                    log.info("CONEXAO", "ENCERRADA");
                }
                out.close();
                socket.close();

            } catch (Exception e) {
            }

            if (mensagem.contains("SERVIDOR")) {
                try {
                    server.close();
                } catch (IOException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        private String tratarInput(String input) {
            if (input.charAt(0) == '/' || input.charAt(0) == '\\') {
                input = input.substring(1);
            }

            return input;
        }
    }
}
