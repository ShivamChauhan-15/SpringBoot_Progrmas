package com.example.SpringInternalization.helper;

import java.util.HashMap;
import java.util.Map;

public class UtilMap {
    public static Map<String,String> langMap=new HashMap<>();
    public static void create(){
        langMap.put("lang","en");
    }
}
