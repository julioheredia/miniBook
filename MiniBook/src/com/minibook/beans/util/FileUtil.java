package com.minibook.beans.util;

import java.io.File;
import java.io.FileOutputStream;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

public class FileUtil {

  private static Logger log = Logger.getLogger(FileUtil.class);
  public static final String PATH = "/temp/";
  public static final String SPACE = " ";
  public static final String BARRA = "\\";
  public static final String IMG_TYPE = ".jpg";

  public static File createImageFile(byte[] bytes, String imageName) {
    try {
      String fileName = tempPath() + BARRA + imageName + IMG_TYPE;
      File file = new File(fileName);
      if (file.exists()) {
        return file;
      } else {
        FileOutputStream fileOuputStream = new FileOutputStream(file);
        fileOuputStream.write(bytes);
        fileOuputStream.close();
        return file;
      }

    } catch (Exception e) {
      log.error("", e);
      e.printStackTrace();
    }
    return null;
  }

  private static String tempPath() {
     ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
     return extContext.getRealPath(PATH);
//    return PATH;
  }

}
