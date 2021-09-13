package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

//xml静态创建 & Java动态创建（使用更多）

//静态
//1.创建一个自己的Fragment类，继承Fragment并重写部分方法
//2.在activity_main中使用fragment标签，并给它添加name：包名
//3.给自定义的fragment类设置layout
//4.重写onCreateView，绑定关联的layout

public class myFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.top_fragment, container, false);
        return view;
    }
}

//动态
//1.在activity_main中使用帧布局来占位
//2.在MainActivity的onCreate中使用java动态添加Fragment到activity中：FragmentManager & FragmentTransaction
//3.通过FragmentTransaction的add方法来添加layout
//4.最后使用FragmentTransaction的commit方法来提交事务
