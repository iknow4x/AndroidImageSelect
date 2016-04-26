package com.iknow.imageselect.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.iknow.imageselect.R;
import com.iknow.imageselect.model.AlbumInfo;
import com.iknow.imageselect.model.ImageInfo;
import java.util.LinkedList;

/**
 * @Author: Jason.Chou
 * @Email: who_know_me@163.com
 * @Created: 2016年04月12日 3:26 PM
 * @Description:
 */
public class AlbumListAdapter extends BaseAdapter{
  private LinkedList<AlbumInfo> adapterListData;
  private Context context;

  public AlbumListAdapter(Context context,LinkedList<AlbumInfo> dataList) {
    super();
    this.context = context;
    this.adapterListData = dataList;
  }

  @Override public int getCount() {
    return adapterListData.size();
  }

  @Override public Object getItem(int id) {
    return adapterListData.get(id);
  }

  @Override public long getItemId(int id) {
    return id;
  }

  @Override public View getView(int position, View convertView, ViewGroup viewGroup) {
    ViewHolder h;
    AlbumInfo albumInfo = (AlbumInfo) getItem(position);
    if(null == convertView){
      convertView = LayoutInflater.from(context).inflate(R.layout.ablum_list_view_item, null);
      h = new ViewHolder();
      h.albumCover = (ImageView) convertView.findViewById(R.id.album_cover);
      h.albumName = (TextView) convertView.findViewById(R.id.album_name);
      h.albumNumber = (TextView) convertView.findViewById(R.id.album_count);
      convertView.setTag(h);
    }else{
      h = (ViewHolder) convertView.getTag();
    }

    ImageInfo imageInfo = albumInfo.images.get(0);
    String path = imageInfo.imgPath;
    if(!TextUtils.isEmpty(imageInfo.thumbPath)){
      path = imageInfo.thumbPath;
    }

    //ImageLoaderHelper.displayImage("file:///" + path, h.albumCover,
    //    AsyncImageLoaderHelper.getCtripDisplayImageOptionWithOutDisc(),
    //    new GSImageLoadingListener() {
    //      @Override public void onLoadingComplete(String imageUri,
    //          View view,Bitmap loadedImage) {
    //        ImageView imageView = (ImageView) view;
    //        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    //      }
    //    });
    h.albumName.setText(albumInfo.imgName);
    h.albumNumber.setText(""+albumInfo.images.size());

    return convertView;
  }

  static class ViewHolder{
    ImageView albumCover;
    TextView albumName,albumNumber;
  }
}

