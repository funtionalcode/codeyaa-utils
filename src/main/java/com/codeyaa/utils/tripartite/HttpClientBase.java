package com.codeyaa.utils.tripartite;

public class HttpClientBase {

    public static final Integer SUCCESS_STATUS_CODE = 200;
    public static final Integer ERROR_STATUS_CODE = 500;

    public static interface Methons {
        public static final String GET = "GET";
        public static final String PUT = "PUT";
        public static final String DELETE = "DELETE";
    }

    public static interface HeaderParams {
        public static final String AUTHORIZATION = "Authorization";
        public static final String DATE = "date";
        public static final String X_LIST_LIMIT = "x-list-limit";
        public static final String ACCEPT = "Accept";
        public static final String CONTENT_SECRET = "Content-Secret";
        public static final String CONTENT_LENGTH = "Content-Length";
        public static final String CONTENT_TYPE = "Content-Type";
        public static final String METADATA = "x-upyun-meta-x";
    }

    public static interface ContentTypes {
        public static final String JSON = "application/json";
        public static final String STREAM = "application/octet-stream";
        public static final String IMAGE = "image/png";
        public static final String FORM_MIME = "application/x-www-form-urlencoded";
    }
}
