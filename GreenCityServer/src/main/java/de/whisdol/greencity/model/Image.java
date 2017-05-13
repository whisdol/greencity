package de.whisdol.greencity.model;

import de.whisdol.greencity.GreencityserverApplication;
import de.whisdol.greencity.dao.ImageDAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

/**
 * Created by cedri on 29.04.2017.
 */
public class Image {
    private long id;
    private String type;
    private String fileDir;
    private String name;
    private Base64 file;

    public Image() {
    }

    public Image(long id, String type, String fileDir, String name) {
        this.id = id;
        this.type = type;
        this.fileDir = fileDir;
        setName(name);
    }

    public Image(String type, String fileDir, String name) {
        this.type = type;
        this.fileDir = fileDir;
        setName(name);
    }

    public Image(long id, String type, String fileDir, String name, Base64 file) {
        this.id = id;
        this.type = type;
        this.fileDir = fileDir;
        setName(name);
        this.file = file;
    }

    public Image(String type, String fileDir, String name, Base64 file) {
        this.type = type;
        this.fileDir = fileDir;
        setName(name);
        this.file = file;
    }

    public Image(long imageId) {
        this.id = imageId;
        ImageDAO imageDao = (ImageDAO) GreencityserverApplication.context.getBean("imageDAO");
        Image dbImage = imageDao.selectImageById(imageId);
        this.type = dbImage.type;
        this.fileDir = dbImage.fileDir;
        this.name = dbImage.name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileDir() {
        return fileDir;
    }

    public void setFileDir(String fileDir) {
        this.fileDir = fileDir;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddhhmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        this.name = timestamp + name;
    }
}
