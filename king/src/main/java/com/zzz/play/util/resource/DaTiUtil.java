package com.zzz.play.util.resource;

import java.io.*;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2016/12/3 0003.
 */
public class DaTiUtil {

    public static final String FILE_NAME = "res/dati.txt";
    public static final String ERROR_NAME = "res/datiError.txt";
    public static Map<String, String> hashMap = new ConcurrentHashMap<>();
    public static Map<String, LinkedList<String>> errorMap = new ConcurrentHashMap<>();

    static {
        try {
            initDati();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initDati() throws IOException {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                buildMap(line.trim());
                line = br.readLine();
            }

        } else {
            file.createNewFile();
        }
    }

    private static void initError() throws IOException {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                buildError(line.trim());
                line = br.readLine();
            }

        } else {
            file.createNewFile();
        }
    }

    private static void buildError(String line) {
        String[] lines = line.split("=");
        if (lines.length == 2) {
            LinkedList<String> list = new LinkedList<>();
            for (String s : lines[0].split(",")) {
                if (s != null && !s.trim().equals("")) {
                    list.add(s);
                }
            }
            errorMap.put(lines[0], list);
        }
    }


    private static void buildMap(String line) {
        String[] lines = line.split("=");
        if (lines.length == 2) {
            hashMap.put(lines[0], lines[1]);
        }

    }

    public static void putMap(Map<String, String> map) {
        synchronized (DaTiUtil.class) {
            hashMap.putAll(map);
            try {
                FileOutputStream fos = new FileOutputStream(FILE_NAME);
                PrintStream printStream = new PrintStream(fos);
                for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                    printStream.println(entry.getKey() + "=" + entry.getValue());
                }
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void addError(String key, String val) {
        LinkedList<String> strings = errorMap.get(key);
        if (strings == null) {
            strings = new LinkedList<>();
            errorMap.put(key, strings);
        }
        strings.add(val);
    }
}



















