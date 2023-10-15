package ndeta.mobil.utils;

import java.time.Year;

public class AccountUtils {
    public static  final String ACCOUNT_EXIST_CODE="001";
    public static final String ACCOUNT_EXIST_MESSAGE="Account exist";
    public static final String ACCOUNT_CREATION_SUCCESS="002";
    public static final String ACCOUNT_CREATION_MESSAGE="Account created";
    public  static  String GenerateAccountNumber(){
        /*
         * creating account number by concatenating current year with random
         * 6 digits
         * */
        Year currentYear=Year.now();
        int min=100000;
        int max=999999;
        //Inherit a random number between min and max
        int randNumber=(int) Math.floor (Math.random()*(max-min+1)+min);
        //Convert current year and random number to string,
        // then concatenate them together
        String year=String.valueOf(currentYear);
        String randomNumber=String.valueOf(randNumber);
        StringBuilder accountNumber=new StringBuilder();
        return accountNumber.append(year).append(randomNumber).toString();
    }
}
