package com.kkhome.excel;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.swing.*;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
//        try {
//            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
//            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
//            UIManager.put("RootPane.setupButtonVisible", false);
//        } catch (Exception e) {
//            //TODO exception
//        }
        new SpringApplicationBuilder(Application.class).headless(false).run(args);
    }
}
