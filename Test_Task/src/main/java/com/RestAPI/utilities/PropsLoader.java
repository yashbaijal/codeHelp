package com.RestAPI.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropsLoader {

    static File file = new File(System.getProperty("user.dir").concat(File.separator).concat("/src/main/resources").concat(File.separator).concat("config.properties"));

    public static String loadProprerties(String property)  {
        FileInputStream fis=null;
        Properties prop=null;

        try
        {
            fis=new FileInputStream(file);
            prop=new Properties();
            prop.load(fis);
        }
        catch(FileNotFoundException fe)
        {
            fe.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return prop.getProperty(property);
    }
}
