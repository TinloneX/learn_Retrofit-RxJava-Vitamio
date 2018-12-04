package org.tinlone.demo.rxjavasample.activity.view;

import android.app.Activity;
import android.view.View;

import org.tinlone.demo.rxjavasample.widget.BezierPathView;
import org.tinlone.demo.rxjavasample.widget.BezierWaveView;
import org.tinlone.demo.rxjavasample.widget.CanvasChangeView;
import org.tinlone.demo.rxjavasample.widget.DrawText;
import org.tinlone.demo.rxjavasample.widget.PathView;
import org.tinlone.demo.rxjavasample.widget.RegionView;
import org.tinlone.demo.rxjavasample.widget.TestView1;
import org.tinlone.demo.rxjavasample.widget.WhatsPaintA;
import org.tinlone.demo.rxjavasample.widget.WhatsPaintB;
import org.tinlone.demo.rxjavasample.widget.WhatsPaintC;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator on 2018/2/26 0026.
 */

public class ViewListFactory {

    public static final List<String> VIEW_TITLES = new ArrayList<String>() {
        {
            add("自定义控件学习1");
            add("自定义控件学习2");
            add("自定义控件学习3");
            add("自定义控件学习4");
            add("自定义控件学习5");
            add("自定义控件学习6-1");
            add("自定义控件学习6-2");
            add("自定义控件学习7-1");
            add("自定义控件学习7-2");
            add("自定义控件学习7-3");
        }
    };
    public static final List<String> VIEW_NAMES = new ArrayList<String>() {
        {
            add("\n概述及基本几何图形绘制");
            add("\n路径及文字");
            add("\n区域（Range）");
            add("\ncanvas变换与操作");
            add("\ndrawText 基本操作");
            add("\n贝塞尔曲线 - 轨迹");
            add("\n贝塞尔曲线 - 波浪");
            add("\n画笔属性详解-画笔属性");
            add("\n画笔属性详解-path离散");
            add("\n画笔属性详解-path印章");
        }
    };

    public static View get(Activity context, int index){
        switch (index) {
            case 0:
                return new TestView1(context);
            case 1:
                return new PathView(context);
            case 2:
                return new RegionView(context);
            case 3:
                final boolean[] flag = {false};
                CanvasChangeView view = new CanvasChangeView(context);
                view.setOnClickListener(v -> view.translate(flag[0] = !flag[0])
                        .rotate(flag[0])
                        .scale(flag[0])
                        .skew(flag[0])
                        .draw1());
                return view;
            case 4:
                return new DrawText(context);
            case 5:
                return new BezierPathView(context);
            case 6:
                return new BezierWaveView(context);
            case 7:
                return new WhatsPaintA(context);
            case 8:
                return new WhatsPaintB(context);
            case 9:
                return new WhatsPaintC(context);
            default:
                return null;
        }
    }

}
