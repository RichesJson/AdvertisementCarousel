package com.aidp.smartos.android.advertisementcarousel.gallery;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aidp.smartos.android.advertisementcarousel.R;
import com.aidp.smartos.android.advertisementcarousel.adapter.AdvertiseCarouselAdapter;
import com.aidp.smartos.android.advertisementcarousel.scroll.AsiaInfoSrcoller;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 福州亚信数电
 * com.aidp.smartos.android.arcgis.api.helper  smartos-android
 * 作者：ashmen
 * @deprecated  AdvertiseCarouselBean 广告轮播图
 * 创建时间：12 2014/12/12.
 */
public class AdvertiseCarouselGallery extends ViewPager{

    private Activity activity;

    private AdvertiseCarouselAdapter adapter;

    private int scrollTime;

    private Timer timer;
    private int oldIndex = 0;
    private int curIndex = 0;
    /**
     * @serialField  contentText 显示备注信息
     */
    private TextView contentText;

    private LinearLayout linearLayout;

    private TextView onLayerTextView;

    public AdvertiseCarouselGallery(Context context,AttributeSet attrs){
        super(context,attrs);
    }

    public void instanceLayout(Activity activity,AdvertiseCarouselAdapter adapter,int scrollTime,TextView onLayerTextView){
        this.activity=activity;
        this.adapter=adapter;
        this.scrollTime=scrollTime;
        this.onLayerTextView=onLayerTextView;
        this.setAdapter(adapter);
    }


    public void instance(Activity activity,AdvertiseCarouselAdapter adapter,int scrollTime,TextView contentText){
        this.activity=activity;
        this.adapter=adapter;
        this.scrollTime=scrollTime;
        this.setAdapter(adapter);
        this.contentText=contentText;
    }
    /**
     * @deprecated  显示轮播图
     * @param ovalLayout
     * @param textViewId
     * @param resource
     * @param color
     * @param changeResource
     * @param changeColor
     */
    public void display(LinearLayout ovalLayout,int textViewId,final int resource,   int color, int changeResource, int changeColor) {
        // 设置圆点
        initOvalLayout(ovalLayout,resource, color,
                changeResource,changeColor);
        if (scrollTime != 0 && this.adapter.getList().size() > 1) {
            // 设置滑动动画时间  ,如果用默认动画时间可不用 ,反射技术实现
            new AsiaInfoSrcoller(activity).setDuration(this, 700);
        }
        if (adapter.getList().size() > 1) {
            this.setCurrentItem((Integer.MAX_VALUE / 2)
                    - (Integer.MAX_VALUE / 2) % adapter.getList().size());// 设置选中为中间/图片为和第0张一样
        }
    }


    /**
     * @deprecated  开始播放轮播图
     * @param ovalLayout
     * @param resource
     * @param color
     * @param changeResource
     * @param changeColor
     */
    public void start(LinearLayout ovalLayout,final int resource,   int color, int changeResource,   int changeColor) {

        //设置轮播图的文字提示信息
        initOvalLayoutText();
        // 设置圆点
        initOvalLayout(ovalLayout,resource, color,
                changeResource,changeColor);
        if (scrollTime != 0 && this.adapter.getList().size() > 1) {
            //设置滑动动画时间  ,如果用默认动画时间可不用 ,反射技术实现
            new AsiaInfoSrcoller(activity).setDuration(this, 700);
            startTimer();
            // 触摸时停止滚动
            this.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        startTimer();
                    } else {
                        stopTimer();
                    }
                    return false;
                }
            });
        }
        if (adapter.getList().size() > 1) {
            this.setCurrentItem((Integer.MAX_VALUE / 2)
                    - (Integer.MAX_VALUE / 2) % adapter.getList().size());// 设置选中为中间/图片为和第0张一样
        }
    }

    /**
     * 取得当明选中下标
     * @return
     */
    public int getCurIndex() {
        return curIndex;
    }
    /**
     * 停止滚动
     */
    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    /**
     * 开始滚动
     */
    public void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        AdvertiseCarouselGallery.this.setCurrentItem(AdvertiseCarouselGallery.this
                                .getCurrentItem() + 1);
                    }
                });
            }
        }, scrollTime, scrollTime);
    }

    /**
     *@deprecated  initOvalLayout 初始化滚动图层
     * @param ovalLayout 滚动图底部的图层
     * @param resource  图形未变更的时候使用的资源
     * @param color    图形未变更的时候字体的样式
     * @param changeResource  图形变更时使用的资源
     * @param changeColor   图形变更的时候使用的字体样式
     */
    private void initOvalLayout(final LinearLayout ovalLayout,final int resource,  final int color,  final int changeResource, final int changeColor) {
        if (ovalLayout != null) {
            for (int i = 0; i < adapter.getList().size(); i++) {
                //当前overLayer添加多个View
                TextView textView=new TextView(activity);
                textView.setGravity(Gravity.CENTER);
                textView.setLayoutParams(onLayerTextView.getLayoutParams());
                textView.setTextSize(onLayerTextView.getTextSize());
                if(i==0){
                    textView.setTextColor(activity.getResources().getColor(changeColor));
                    textView.setBackgroundResource(changeResource);
                }else {
                    textView.setTextColor(activity.getResources().getColor(color));
                    textView.setBackgroundResource(resource);
                }
                textView.setText(adapter.getList().get(i).getImgeNo());
                ovalLayout.addView(textView);
            }
            System.out.println("initOvalLayout进来了吗");
//            //默认第一个高亮显示

            this.setOnPageChangeListener(new OnPageChangeListener() {
                public void onPageSelected(int i) {
                    try {
                        System.out.println("i:=" + i);
                        curIndex = i % adapter.getList().size() + 1;
                        System.out.println("i:=" + i + ".curIndex:=" + curIndex + "oldIndex:=" + oldIndex);

                        TextView oldIndexTextView = (TextView) ovalLayout.getChildAt(oldIndex);
                        oldIndexTextView.setBackgroundResource(resource);
                        oldIndexTextView.setTextColor(activity.getResources().getColor(color));

                        //圆点选中
                        TextView curIndexTextView = (TextView) ovalLayout.getChildAt(curIndex);
                        curIndexTextView.setBackgroundResource(changeResource);
                        curIndexTextView.setTextColor(activity.getResources().getColor(changeColor));
                        oldIndex = curIndex;
                    }catch (Exception e){
                        System.out.println("数据丢失发生异常");
                    }
                }

                public void onPageScrolled(int arg0, float arg1, int arg2) {

                }

                public void onPageScrollStateChanged(int arg0) {
                }
            });
        }
    }

    /**
     * @deprecated  是否显示轮播图下面一行的文字
     */
    private void initOvalLayoutText() {

        if (contentText != null) {

            if(curIndex==0) {
                contentText.setText(adapter.getList().get(0).getNoteContent());
            }
            this.setOnPageChangeListener(new OnPageChangeListener() {
                public void onPageSelected(int i) {
                    curIndex = i % adapter.getList().size();
                    contentText.setText(adapter.getList().get(curIndex).getNoteContent());
                    oldIndex = curIndex;
                }

                public void onPageScrolled(int arg0, float arg1, int arg2) {
                }

                public void onPageScrollStateChanged(int arg0) {
                }
            });
        }
    }


}
