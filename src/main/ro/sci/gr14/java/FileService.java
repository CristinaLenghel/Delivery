package main.ro.sci.gr14.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.function.Function;

public class FileService<T> {
    public LinkedList<T> readFromFile(String inFile, Function<String, T> mapper) throws Exception {
        LinkedList<T> list=new LinkedList<>();

        BufferedReader br=new BufferedReader(new FileReader(new File(inFile)));
        String linie="";

        while ((linie=br.readLine())!=null){
            if (!linie.equals("")) list.add(mapper.apply(linie));
        }
        return list;
    }
}
