package com.aidp.smartos.android.advertisementcarousel.bean;

import android.app.Activity;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by 福州亚信数电
 * com.aidp.smartos.android.arcgis.api.helper  smartos-android
 * 作者：ashmen
 * @deprecated  AdvertiseCarouselBean 广告属性类
 * 创建时间：12 2014/12/12.
 */
public class AdvertiseCarouselBean implements Serializable {
    /**
     * @serialField imgeNo 图片编号
     */
    private String imgeNo;
    /**
     * @serialField imgeNo 图片ID
     */
    private String id;
    /**
     * @serialField activity 要跳转的activity的值
     */
    private String activity;
    /**
     * @serialField noteContent 图片备注信息
     */
    private String noteContent;

    private ImageView imageView;

    public String getImgeNo() {
        return imgeNo;
    }

    public void setImgeNo(String imgeNo) {
        this.imgeNo = imgeNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
