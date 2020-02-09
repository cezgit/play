package com.elyzar.play.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CompressionDecorator extends DataSourceDecorator {

    public CompressionDecorator(FileDao ds) {
        super(ds);
    }

    @Override
    public void writeData(String data) {
        byte[] compressedData = compress(data);
        String dataToWrite = Base64.getEncoder().encodeToString(compressedData);
        super.writeData(dataToWrite);
    }

    @Override
    public String readData() {
        String data = super.readData();
        return decompress(Base64.getDecoder().decode(data));
    }

    private byte[] compress(String stringData) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(stringData.length());
        try {
            GZIPOutputStream gzip = new GZIPOutputStream(bos);
            gzip.write(stringData.getBytes());
            gzip.close();
            byte[] compressed = bos.toByteArray();
            bos.close();
            return compressed;
        } catch (IOException e) {
            throw new RuntimeException("failed compressing data", e);
        }
    }

    private String decompress(final byte[] compressed) {
        ByteArrayInputStream bis = new ByteArrayInputStream(compressed);
        byte[] bytes;
        try {
            GZIPInputStream gis = new GZIPInputStream(bis);
            bytes = gis.readAllBytes();
            return new String(bytes, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException("failed decompressing data", e);
        }
    }
}
