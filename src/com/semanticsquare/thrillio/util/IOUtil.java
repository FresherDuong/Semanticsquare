package com.semanticsquare.thrillio.util;

import java.io.*;

/*
* BufferedReader for character stream
* */

public class IOUtil {
    public static void read(String[] data, String filename){
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(filename), "UTF-8"))){
            String line;
            int count = 0;
            while((line = br.readLine()) != null){
                data[count] = line;
                count++;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
