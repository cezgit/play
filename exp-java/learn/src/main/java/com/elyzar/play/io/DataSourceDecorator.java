package com.elyzar.play.io;

public class DataSourceDecorator implements FileDao {

    private FileDao dao;

    public DataSourceDecorator(FileDao dao) {
        this.dao = dao;
    }

    @Override
    public void writeData(String data) {
        dao.writeData(data);
    }

    @Override
    public String readData() {
        return dao.readData();
    }
}
