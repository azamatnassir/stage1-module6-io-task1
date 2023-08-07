package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileReader {

    public Profile getDataFromFile(File file) {
        StringBuilder str = new StringBuilder();
        try (FileInputStream inputStream = new FileInputStream(file)) {
            int c;
            while ((c = inputStream.read()) != -1){
                str.append((char) c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(str);

        Map<String, String> map = new HashMap<>();
        String[] keyVals = str.toString().split("[\\r\\n]+");
        for(String keyVal:keyVals)
        {
            String[] parts = keyVal.split(": ");
            map.put(parts[0],parts[1]);
        }

        return new Profile(map.get("Name"), Integer.parseInt(map.get("Age")), map.get("Email"), Long.valueOf(map.get("Phone")));
    }
}
