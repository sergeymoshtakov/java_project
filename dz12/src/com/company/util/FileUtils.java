package com.company.util;

import java.io.File;

public class FileUtils {
    public static boolean isFileEmpty(File file) {
        if (file.exists() && file.isFile()) {
            return file.length() == 0;
        }
        return false;
    }
}
