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

    public void inicializar() throws IOException {
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
                
            } catch (Exception e) {
            }
        }
    }

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
                        String mensagem = in.readLine();
                        log.info("MENSAGEM R", socket.getInetAddress().getHostName(), mensagem);
                        if ("\\FIM".equals(mensagem)) {
                            break;
                        } else {
                            mensagem = loader.run(mensagem);
                            out.println(loader.run(mensagem));
                            log.info("MENSAGEM E", socket.getInetAddress().getHostName(), mensagem);
                        }
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
}
