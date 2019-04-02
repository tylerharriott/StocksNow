package Model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashMD5 {

    private MysqlDB mysqlDB = new MysqlDB();



    public String generatePassword(String pass) {

        String passwordToHash = pass;
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        return generatedPassword;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////


    public boolean verifyMatch(String name, String testPassword){
        boolean ret = false;

        String HashedPassword = mysqlDB.selectPasswordSQL(name);
        String genPassword = generatePassword(testPassword);

        if(HashedPassword.equalsIgnoreCase(genPassword)){
            ret = true;
        }


        return ret;
    }


    public String printMe(String name){
        String ret = "";

        name = name + "1111";
        ret =name;

        return ret;
    }

}