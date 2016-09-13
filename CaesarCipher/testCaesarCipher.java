
/**
 * Write a description of testCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class testCaesarCipher {
    private int[] countLetters(String message){
        String alph = "abcdefghijklmnoqprstuvwxyz";
        int[] counts = new int[26];
        for (int k = 0; k < message.length(); k++){
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if (dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
    public int maxIndex(int[] vals){
        int maxDex = 0;
        for (int k = 0; k < vals.length; k++){
            if (vals[k] > vals[maxDex]){
                maxDex = k;
            }
        }
        return maxDex;
    }
    
    public void simpleTests(){
        CaesarCipher d = new CaesarCipher(0);
        System.out.println(d);
        FileResource a = new FileResource();
        String b = a.asString();
        CaesarCipher c = new CaesarCipher(18);
        String encrypted = c.encrypt(b);
        System.out.println(encrypted);
        String decrypted = c.decrypt(encrypted);
    }
    
    public String breakCaesarCipher(String input){
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4){
            dkey = 26 - (4 - maxDex);
        }
        CaesarCipher a = new CaesarCipher(dkey);
        return a.decrypt(input);
    }
}
