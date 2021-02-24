package src.main.java;

import java.io.File;

public class FileManagement {
    private static final File TEMP_DIRECTORY = new File(System.getProperty("java.io.tmpdir"));
    File newDirectory = new File(TEMP_DIRECTORY, "new_directory");

}
