package com.iknow.imageselect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import com.iknow.imageselect.model.ImageInfo;
import com.iknow.imageselect.widget.PicItemCheckedView;
import com.iknow.imageselect.widget.TitleView;

/**
 * @Author: Jason.Chou
 * @Email: who_know_me@163.com
 * @Created: 2016年04月12日 5:02 PM
 * @Description:
 */
public class MultiSelectImageActivity extends AbsImageSelectActivity {

  // ===========================================================
  // Constants
  // ===========================================================

  // ===========================================================
  // Fields
  // ===========================================================
  private View mCameraIv;
  private TextView mNextStep;
  // ===========================================================
  // Constructors
  // ===========================================================

  public static void startActivityForResult(Activity context){
    context.startActivityForResult(new Intent(context,MultiSelectImageActivity.class),MULTI_PIC_SELECT_REQUEST);
  }

  // ===========================================================
  // Getter & Setter
  // ===========================================================

  // ===========================================================
  // Methods for/from SuperClass/Interfaces
  // ===========================================================

  @Override
  protected void initTitleView(TitleView titleView) {
  }

  @Override protected void initBottomView(View bottomView) {
  }

  @Override
  protected View doGetViewWork(int position,View convertView,ImageInfo imageInfo) {

    if (convertView == null) {
      convertView = new PicItemCheckedView(this);
    }

    try {
      PicItemCheckedView view = ((PicItemCheckedView) convertView);
      long picId = images.get(position).imgId;
      if (picId < 0) {
        throw new RuntimeException("the pic id is not num");
      }

      final ImageView iv = view.getImageView();
      iv.setScaleType(ImageView.ScaleType.FIT_XY);

      String path = imageInfo.imgPath;

      if (!TextUtils.isEmpty(imageInfo.thumbPath)) {
        path = imageInfo.thumbPath;
      }

      //ImageLoader.displayImage(ImageFilePathUtil.getImgUrl(path), iv,
      //    AsyncImageLoaderHelper.getCtripDisplayImageOptionWithOutDisc(),
      //    new GSImageLoadingListener() {
      //      @Override public void onLoadingComplete(String imageUri,
      //          View view,Bitmap loadedImage) {
      //        ImageView imageView = (ImageView) view;
      //        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
      //      }
      //    });

      if (hasCheckedImages.contains(images.get(position))) {
        view.setChecked(true);
      } else {
        view.setChecked(false);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return convertView;
  }

  @Override
  protected void onImageSelectItemClick(AdapterView<?> parent, View view, int position,long id) {
    if(view instanceof PicItemCheckedView){
      PicItemCheckedView item = (PicItemCheckedView)view;
      boolean isChecked = item.isChecked();
      item.setChecked(!isChecked);
      if(isChecked && hasCheckedImages.contains(images.get(position)))
        hasCheckedImages.remove(images.get(position));
      else if(!isChecked && !hasCheckedImages.contains(images.get(position))){
        hasCheckedImages.add(images.get(position));
      }

      if(hasCheckedImages.size() > 0 ) {
        mNextStep.setTextAppearance(mContext,R.style.blue_text_18_style);
        mNextStep.setEnabled(true);
      }else {
        mNextStep.setEnabled(false);
        mNextStep.setTextAppearance(mContext,R.style.gray_text_18_style);
      }

    }
  }

  @Override protected void onCameraActivityResult(String path) {
    Bundle bd = new Bundle();
    hasCheckedImages.clear();
    hasCheckedImages.add(ImageInfo.buildOneImage(path));
    bd.putSerializable("ImageData",hasCheckedImages);
    Intent intent = new Intent();
    intent.putExtras(bd);
    MultiSelectImageActivity.this.setResult(RESULT_OK, intent);
    MultiSelectImageActivity.this.finish();
  }

  // ===========================================================
  // Methods
  // ===========================================================

  // ===========================================================
  // Inner and Anonymous Classes
  // ===========================================================
}
