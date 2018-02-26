package org.tinlone.demo.rxjavasample.config;

import org.tinlone.demo.rxjavasample.activity.view.ViewATestActivity;
import org.tinlone.demo.rxjavasample.activity.view.ViewBTestActivity;
import org.tinlone.demo.rxjavasample.activity.view.ViewCTestActivity;

import java.util.Arrays;
import java.util.List;

/**
 * @author Administrator on 2018/2/26 0026.
 */

public class ActivitiesListConfig {

    private static final String[] TITLES = {"自定义控件学习1", "自定义控件学习2", "自定义控件学习3"};

    private static final Class [] ACTIVITIES = {ViewATestActivity.class,ViewBTestActivity.class,ViewCTestActivity.class};

    public static List<String> getTitles(){
        return Arrays.asList(TITLES);
    }

    public static List<Class> getAvtivities(){
        return Arrays.asList(ACTIVITIES);
    }

}
