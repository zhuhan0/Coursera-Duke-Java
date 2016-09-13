import edu.duke.*;

public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipher(int key){
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }
    
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if (Character.isUpperCase(currChar) == true){
                int idx = alphabet.toUpperCase().indexOf(currChar);
                if (idx != -1){
                    char newChar = shiftedAlphabet.toUpperCase().charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            }
            else{
                int idx = alphabet.indexOf(currChar);
                if (idx != -1){
                    char newChar = shiftedAlphabet.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }
    
    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }
    
    public void testCaesar() {
        System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
        /*int key = 15;
        System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", key));
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);*/
    }
    
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encrypted = new StringBuilder(input);
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String upper1 = upper.substring(key1) + upper.substring(0,key1);
        String upper2 = upper.substring(key2) + upper.substring(0,key2);
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String lower1 = lower.substring(key1) + lower.substring(0, key1);
        String lower2 = lower.substring(key2) + lower.substring(0, key2);
        for(int i = 0; i < encrypted.length(); i += 2) {
            char currChar = encrypted.charAt(i);
            if (Character.isUpperCase(currChar) == true){
                int idx = upper.indexOf(currChar);
                if (idx != -1){
                    char newChar = upper1.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            }
            else{
                int idx = lower.indexOf(currChar);
                if (idx != -1){
                    char newChar = lower1.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        for(int i = 1; i < encrypted.length(); i += 2) {
            char currChar = encrypted.charAt(i);
            if (Character.isUpperCase(currChar) == true){
                int idx = upper.indexOf(currChar);
                if (idx != -1){
                    char newChar = upper2.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            }
            else{
                int idx = lower.indexOf(currChar);
                if (idx != -1){
                    char newChar = lower2.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }
}