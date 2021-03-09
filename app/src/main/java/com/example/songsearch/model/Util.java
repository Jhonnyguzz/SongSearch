package com.example.songsearch.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

    public static InputStream io;

    public static void setIo(InputStream io2) {
        io = io2;
    }

    public static List<String> getSongList() throws IOException {
        List<String> ls = new ArrayList<>();
        if (io != null) {
            BufferedReader br = new BufferedReader(new InputStreamReader(io));
            String line;
            while ((line=br.readLine()) != null) {
                ls.add(line);
            }
            return ls;
        }
        return null;

    }

    //TODO
    //Refactor
    public static String toRegExpWithLowerAndUpper(String regExp)
    {
        char aux[] = regExp.toCharArray();
        StringBuilder sb = new StringBuilder();
        String tmp = "";

        for (int i = 0; i < regExp.length(); i++)
        {
            tmp = "";
            if(aux[i]=='A' || aux[i]=='a' || (int)aux[i]==193 || (int)aux[i]==225)
            {
                tmp = "("+"A"+"|"+(char)193+"|a"+"|"+(char)225+")";
                sb.append(tmp);
            }
            else if(aux[i]=='E' || aux[i]=='e' || (int)aux[i]==201 || (int)aux[i]==233)
            {
                tmp = "("+"E"+"|"+(char)201+"|e"+"|"+(char)233+")";
                sb.append(tmp);
            }
            else if(aux[i]=='I' || aux[i]=='i' || (int)aux[i]==205 || (int)aux[i]==237)
            {
                tmp = "("+"I"+"|"+(char)205+"|i"+"|"+(char)237+")";
                sb.append(tmp);
            }
            else if(aux[i]=='O' || aux[i]=='o' || (int)aux[i]==211 || (int)aux[i]==243)
            {
                tmp = "("+"O"+"|"+(char)211+"|o"+"|"+(char)243+")";
                sb.append(tmp);
            }
            else if(aux[i]=='U' || aux[i]=='u' || (int)aux[i]==218 || (int)aux[i]==250)
            {
                tmp = "("+"U"+"|"+(char)218+"|u"+"|"+(char)250+")";
                sb.append(tmp);
            }
            else if((int)aux[i]>=65 && (int)aux[i]<=90)
            {
                tmp = "("+aux[i]+"|"+(char)(aux[i]+32)+")";
                sb.append(tmp);
            }
            else if((int)aux[i]>=97 && (int)aux[i]<=122)
            {
                tmp = "("+(char)(aux[i]-32)+"|"+aux[i]+")";
                sb.append(tmp);
            }
            else if(aux[i]=='\u00F1' || aux[i]=='\u00D1')
            {
                tmp = "(\u00F1|\u00D1)";
                sb.append(tmp);
            }
            else
            {
                sb.append(aux[i]);
            }
        }
        return ".*" + sb.toString() + ".*";
    }

    @Deprecated
    public static void getSongsRawFile() throws IOException {
        Path p = Paths.get("Folder Route");
        List<String> songs = Files.walk(p,1)
                .map(f -> f.getFileName().toString())
                .filter(s -> s.endsWith("mp3"))
                .map(s -> s.substring(0, s.length()-4))
                .sorted()
                .collect(Collectors.toList());

        Files.write(Paths.get("C:\\Users\\user\\Desktop\\songs.txt"), songs);
    }
}
