package com.semanticsquare.thrillio.util;

import java.io.*;
import java.util.List;

/*
* BufferedReader for character stream
* */

public class IOUtil {
    public static void read(List<String> data, String filename){
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"))){
            String line;

            while((line = br.readLine()) != null){
                data.add(line);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String read(InputStream in) {
        StringBuilder text = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"))){
            String line;
            while((line = br.readLine()) != null){
                text.append(line).append("\n");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text.toString();
    }


    public static void write(String webpageData, long id) {
        String out_put_name = "/home/duongdinh/Hoc_Java/social_bookmarking_app/pages_downloaded/" + String.valueOf(id) + ".html";

        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out_put_name), "UTF-8"))){
            writer.write(webpageData);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Cannot find path ! :( ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
