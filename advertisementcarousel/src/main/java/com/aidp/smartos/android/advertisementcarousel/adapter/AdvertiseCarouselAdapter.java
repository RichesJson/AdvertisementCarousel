package com.aidp.smartos.android.advertisementcarousel.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.aidp.smartos.android.advertisementcarousel.R;
import com.aidp.smartos.android.advertisementcarousel.bean.AdvertiseCarouselBean;

import java.util.List;

/**
 * Created by 福州亚信数电
 * com.aidp.smartos.android.arcgis.api.helper  smartos-android
 * 作者：ashmen
 * @deprecated  AdvertiseCarouselBean 广告属性适配器
 * 创建时间：12 2014/12/12.
 */
public class AdvertiseCarouselAdapter extends PagerAdapter {

    private List<AdvertiseCarouselBean> list;
    private Activity context;

    public AdvertiseCarouselAdapter(Activity context,List<AdvertiseCarouselBean> list){
        this.context=context;
        this.list=list;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (list.size() == 1) {// 一张图片时不用流动
            return list.size();
        }
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    public Object instantiateItem(View v, final int i) {
        if (((ViewPager) v).getChildCount() == list.size()) {
            ((ViewPager) v) .removeView(list.get(i % list.size()).getImageView());
        }
        try {
            ((ViewPager) v).addView(list.get(i % list.size()).getImageView(), 0);
            list.get(i % list.size()).getImageView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = new Intent(context, Class.forName(list.get(i % list.size()).getActivity()));
                        context.startActivity(intent);
                        context.finish();
                        context.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                    } catch (Exception e) {
                        System.out.println("com.aidp.smartos.android.advertisementcarousel.AdvertiseCarouselAdapter 发生异常：" + e.getLocalizedMessage());
//                    e.printStackTrace();
                    }
                }
            });
        }catch (Exception e){
            System.out.println("The specified child already has a parent. You must call removeView() on the child's parent first");
        }
        return list.get(i % list.size()).getImageView();
    }

    public List<AdvertiseCarouselBean> getList() {
        return list;
    }

    public void setList(List<AdvertiseCarouselBean> list) {
        this.list = list;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {

    }

    @Override
    public void startUpdate(View container) {
        super.startUpdate(container);
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void startUpdate(ViewGroup container) {

    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {

    }
}
