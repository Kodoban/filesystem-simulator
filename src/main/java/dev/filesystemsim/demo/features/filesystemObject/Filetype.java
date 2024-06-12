package dev.filesystemsim.demo.features.filesystemObject;

public enum Filetype {
    FILE, 
    DIRECTORY, 
    HOME_DIRECTORY;

    // @JsonCreator
    // public static Filetype convert(String filetype) throws Exception {
    //     try {
    //         return Filetype.valueOf(filetype.toUpperCase());
    //     } catch (IllegalArgumentException ex) {
    //         // Handle invalid genre value
    //         throw new Exception("Unknown filetype: \'" + filetype + "\'");
    //     }
    // }

}
