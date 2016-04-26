package com.iknow.imageselect.model;

import java.io.Serializable;

/**
 * @Author: J.Chou
 * @Email: who_know_me@163.com
 * @Created: 2016年04月06日 3:17 PM
 * @Description:
 */

public class ImageInfo implements Serializable {

  public long imgId;
  /** 图片显示的名称 */
  public String imgName = "";
  /** 作者名称 */
  public String nickName = "";
  /** 图片描述信息 */
  public String description = "";

  public String imgFolderPath;//图片所在文件夹的路径

  public String createTime;

  public String thumbPath;//缩略图路径

  public String imgPath;//图片全路径,包含图片文件名的路径信息

  public int rotate;

  public String lat;

  public String lon;

  public static ImageInfo obtain(String path){
     ImageInfo imageInfo = new ImageInfo();
     imageInfo.imgPath = path;
    return imageInfo;
  }

  public static ImageInfo buildOneImage(String path){

    return null;
  }
}
