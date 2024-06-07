package dev.filesystemsim.demo.urlMappings;

public class UrlMapping {

    private static final String API_ENDPOINT = "/api";

    // Static file dirs
    public static final String CSS_DIR = "/css";
    public static final String JS_DIR = "/js";

    public static final String AUTH_CONTROLLER_URL = "/auth";
    public static final String DASHBOARD_CONTROLLER_URL = "/dashboard";
    public static final String FILE_CONTROLLER_URL = "/files";
    public static final String FILESYSTEM_CONTROLLER_URL = "/filesystem";
    public static final String USER_CONTROLLER_URL = "/users";
    
    public static final String USER_REST_CONTROLLER_URL = API_ENDPOINT + USER_CONTROLLER_URL;
    public static final String AUTH_REST_CONTROLLER_URL = API_ENDPOINT + AUTH_CONTROLLER_URL;
    public static final String FILESYSTEM_REST_CONTROLLER_URL = API_ENDPOINT + FILESYSTEM_CONTROLLER_URL;

    public static final String DEFAULT_SUCCESS_URL = DASHBOARD_CONTROLLER_URL;
}
