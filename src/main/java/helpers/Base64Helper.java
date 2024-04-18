package helpers;

import java.util.Base64;

public class Base64Helper {

    public static String encryptString(String text){
        return Base64.getEncoder().encodeToString(text.getBytes());
    }

    public static String decryptString(String text){
        return new String(Base64.getDecoder().decode(text.getBytes()));
    }
}
