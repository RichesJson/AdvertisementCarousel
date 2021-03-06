package com.aidp.smartos.android.advertisementcarousel.scroll;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import java.lang.reflect.Field;

/**
 * Created by 福州亚信数电
 * com.aidp.smartos.android.arcgis.api.helper  smartos-android
 * 作者：ashmen
 * 创建时间：12 2014/12/12.
 */
public class AsiaInfoSrcoller extends Scroller {

    Context context;
    private int mDuration = 500;

    public AsiaInfoSrcoller(Context context) {
        super(context);
        this.context=context;
    }
    public AsiaInfoSrcoller(Context context, Interpolator interpolator) {
        super(context, interpolator);
        this.context=context;
    }
    /**
     *  设置滑动时间  ,如果用默认时间可不用这个类 ,反射技术实现
     * @param vp  ViewPager 对象
     * @param time  时间
     */

    public void setDuration(ViewPager vp,int time) {
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            this.setmDuration(time);
            field.set(vp, this);
        } catch (Exception e) {

        }
    }
    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        //System.out.println("startScroll1");
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    public void setmDuration(int time) {
        mDuration = time;
    }

    public int getmDuration() {
        return mDuration;
    }
}
