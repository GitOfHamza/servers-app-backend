package com.network.server.model;

import java.util.Random;

public class GeneratorID {
    private static int __ID_LEN = 10;
    private Random random;

    private static String _upper_case = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String _lower_case = _upper_case.toLowerCase();
    private static String _numbers = "0123456789";
    private static String _special_chars = ":!?./*%$=+@_\\|-#&";

    private static String __DB = _upper_case + _lower_case + _numbers + _special_chars;



    public static String getID() {
        String _CHAR_ADD = "";
        for (int count = 0 ; count <__ID_LEN ; count++) {
            _CHAR_ADD += __DB.charAt(new Random().nextInt(__DB.length()));
        }
        return _CHAR_ADD;
    }

}
