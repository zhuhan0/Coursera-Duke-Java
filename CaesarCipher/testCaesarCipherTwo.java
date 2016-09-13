
/**
 * Write a description of testCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class testCaesarCipherTwo {
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
    
    public String halfOfString(String message, int start){
        StringBuilder half = new StringBuilder();
        for (int i = start; i < message.length(); i += 2){
            char a = message.charAt(i);
            half.append(a);
        }
        return half.toString();
    }
    
    public void simpleTests(){
        FileResource a = new FileResource();
        String b = a.asString();
        CaesarCipherTwo c = new CaesarCipherTwo(17, 3);
        String encrypted = c.encrypt(b);
        System.out.println(encrypted);
        String decrypted = c.decrypt(encrypted);
        System.out.println(breakCaesarCipher(b));
    }
    
    public int getKey(String s){
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4){
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    }
    
    public String breakCaesarCipher(String input){
        int dkey1 = getKey(halfOfString(input, 0));
        int dkey2 = getKey(halfOfString(input, 1));
        System.out.println(dkey1 + "\t" + dkey2);
        CaesarCipherTwo a = new CaesarCipherTwo(dkey1, dkey2);
        return a.decrypt(input);
    }
}
