package com.next.view.react;

import android.graphics.Color;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

/**
 * Created by Administrator on 2017/3/13.
 */

public class PorterDufferViewManager extends SimpleViewManager<PorterDufferModeView> {
    @Override
    public String getName() {
        return "MPorterDufferView";
    }
    @Override
    protected PorterDufferModeView createViewInstance(ThemedReactContext reactContext) {
        return new PorterDufferModeView(reactContext);
    }

    @ReactProp( name = "rectSize")
    public void setRectSize(PorterDufferModeView view,Integer rectSize){
        view.setRectSize(rectSize);
    }
    @ReactProp( name = "circle_size")
    public void setCircleSize(PorterDufferModeView view,Integer circleSize){
        view.setCircleRadio(circleSize);
    }
    @ReactProp( name = "rect_color")
    public void setRectColor(PorterDufferModeView view,String color){
        view.setRectColor(Color.parseColor(color));
    }
    @ReactProp( name = "circle_color")
    public void setCircleColor(PorterDufferModeView view,String color){
        view.setCircleColor(Color.parseColor(color));
    }
    @ReactProp( name = "mode")
    public void setMode(PorterDufferModeView view,Integer mode){
        view.setMode(mode);
    }
}
