package com.codeyaa.utils.tripartite.http.requests;

import net.dongliu.requests.body.InputStreamSupplier;
import net.dongliu.requests.body.Part;

import java.io.BufferedInputStream;
import java.io.InputStream;

public class RequestsUtil {
    public static Part<?> getPart(String fileName, InputStream inputStream) {
        InputStreamSupplier inputStreamSupplier = () -> new BufferedInputStream(inputStream);
        return Part.file("media", fileName, inputStreamSupplier);
    }
}
