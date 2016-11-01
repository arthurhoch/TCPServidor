/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.redesII.outros;

/**
 *
 * @author arthurhoch
 */
public class Log {

    public void info(String tag, String mensagem) {
        System.out.println("[" + tag + "]" + "\t\t" + mensagem);
    }
    
    public void info(String tag, String info, String mensagem) {
        System.out.println("[" + tag + "]" + "\t\t" + "[" + info + "]" + "\t\t" + mensagem);
    }

}
