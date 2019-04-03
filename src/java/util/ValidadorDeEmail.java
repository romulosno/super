/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author romulo
 */
public class ValidadorDeEmail {

    public static boolean validacaoEmail(String email) {
        char arroba = '@';
        char ponto = '.';
        int i=0 ,iDoArroba=0;
        for(char letra: email.toCharArray()){
            if(letra==arroba  && i!=0 && i!=email.length()){
                iDoArroba = i;
            }
            if(iDoArroba!=0 && letra==ponto  && i!=0 && i!=email.length() && iDoArroba<i){
                return true;
            }
            i++;
        }
        return false;
    }

}
