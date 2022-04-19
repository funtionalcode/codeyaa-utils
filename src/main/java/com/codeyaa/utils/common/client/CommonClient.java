package com.codeyaa.utils.common.client;

import com.google.gson.Gson;
import net.dongliu.requests.Requests;
import net.dongliu.requests.Session;

import java.io.File;

public class CommonClient {
    public static final Session REQUESTS = Requests.session();
    public static final Gson GSON = new Gson();

    public static File downloadImage(String url, String location, String filename) {
        String filePath = String.format("%s%s", location, filename);
        File file = new File(filePath);
        REQUESTS.get(url).send().writeToFile(file);
        return file.isFile() ? file : null;
    }
}
