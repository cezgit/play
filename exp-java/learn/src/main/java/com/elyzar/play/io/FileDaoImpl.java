package com.elyzar.play.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileDaoImpl implements FileDao {

    private String name;

    public FileDaoImpl(String name) {
        this.name = name;
    }

    @Override
    public void writeData(String data)  {
        System.out.println(String.format("writing data %s to file %s", data, name));
        // FileUtils.writeFile(new File(name), data);
        try (PrintWriter out = new PrintWriter(name, "UTF-8")) {
            out.write(data);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String readData() {
        System.out.println(String.format("reading data from file %s", name));
        String data = null;
        try {
            data = new String(Files.readAllBytes(Paths.get(name)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
