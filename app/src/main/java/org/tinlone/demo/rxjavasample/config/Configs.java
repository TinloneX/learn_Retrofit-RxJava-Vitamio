package org.tinlone.demo.rxjavasample.config;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * @author Administrator on 2018/2/26 0026.
 */

public class Configs {

    public static final List<String> VIEW_TITLES = new ArrayList<String>() {
        {
            add("自定义控件学习1");
            add("自定义控件学习2");
            add("自定义控件学习3");
            add("自定义控件学习4");
        }
    };
    public static final List<String> VIEW_NAMES = new ArrayList<String>() {
        {
            add("\n概述及基本几何图形绘制");
            add("\n路径及文字");
            add("\n区域（Range）");
            add("\ncanvas变换与操作");
        }
    };

}
