package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private Button btnReplace;
    private Button btnRemove;
    private FragmentManager manager;

    private Stack<Fragment> fragmentStack = new Stack<Fragment>();


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.top_frag, new myFragment());
//        fragmentTransaction.add(R.id.bottom_frag, new BottomFragment());
//        fragmentTransaction.commit();


        btnReplace = (Button) findViewById(R.id.btn_remove);
        btnRemove = (Button) findViewById(R.id.btn_remove);

        btnReplace.setOnClickListener((View.OnClickListener) this);
        btnRemove.setOnClickListener((View.OnClickListener) this);

        manager = this.getSupportFragmentManager();

    }

    public void onClick(View view) {
        myFragment fragment;
        FragmentTransaction transaction;

        switch (view.getId()) {
            case R.id.btn_replace:
                fragment = new myFragment();
                this.fragmentStack.push(fragment);
                transaction = manager.beginTransaction();
                transaction.add(R.id.layout_container, fragment);
                transaction.commit();
                break;
            case R.id.btn_remove:
                transaction = manager.beginTransaction();
                if(!this.fragmentStack.empty())
                    transaction.remove((this.fragmentStack.pop()));
                transaction.commit();
                break;
        }
    }
}