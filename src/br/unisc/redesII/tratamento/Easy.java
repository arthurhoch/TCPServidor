/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.redesII.tratamento;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author Douglas
 */
public class Easy {

    public static String autores = "Arthur Hoch, Douglas Alves";

    private String dataHora;

    private String emoji;

    public String getDataHora() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date datahora = new Date();

        return dateFormat.format(datahora);
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getEmoji() {
        String[] listaEmoji = {"(͡°͜ʖ͡°)", "▄︻̷̿┻̿═━一", "[̲̅$̲̅(̲̅5̲̅)̲̅$̲̅]", "﴾͡๏̯͡๏﴿ O'RLY?", "(õ_Ô)"};
        Random r = new Random();
        for (int x = 0; x < r.nextInt(listaEmoji.length); x++) {
            emoji = listaEmoji[r.nextInt(listaEmoji.length)];
        }
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

}
