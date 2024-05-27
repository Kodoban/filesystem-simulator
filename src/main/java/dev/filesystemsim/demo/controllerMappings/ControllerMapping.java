package dev.filesystemsim.demo.controllerMappings;

public class ControllerMapping {

    private static final String API_ENDPOINT = "/api";

    public static final String DEFAULT_SUCCESS_URL = "/dashboard";

    public static final String AUTH_CONTROLLER_URL = "/auth";
    public static final String FILE_CONTROLLER_URL = "/files";
    public static final String USER_CONTROLLER_URL = "/users";
    
    public static final String USER_REST_CONTROLLER_URL = API_ENDPOINT + USER_CONTROLLER_URL;
    public static final String AUTH_REST_CONTROLLER_URL = API_ENDPOINT + AUTH_CONTROLLER_URL;
}
