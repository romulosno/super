/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Random;

/**
 *
 * @author romulo
 */
public class GeradorDeSenhas {

    private static final String letras = "abcdefghijklmnopqrstuvwxyz";
    private static final String numeros = "1234567890";
    private static final String especiais = "!@#$%*()-_";
    private static String novaSenha = "";
    private static final Random random  = new Random();

    public static String gerarSenha() {
        int numRandom = random.nextInt(letras.length());
        novaSenha = novaSenha + letras.substring(numRandom,numRandom+1);
        numRandom = random.nextInt(letras.length());
        novaSenha = novaSenha + letras.substring(numRandom,numRandom+1).toUpperCase();
        numRandom = random.nextInt(numeros.length());
        novaSenha = novaSenha + numeros.substring(numRandom,numRandom+1);
        numRandom = random.nextInt(especiais.length());
        novaSenha = novaSenha + especiais.substring(numRandom,numRandom+1);
        numRandom = random.nextInt(numeros.length());
        novaSenha = novaSenha + numeros.substring(numRandom,numRandom+1);

        return novaSenha;

    }
}
