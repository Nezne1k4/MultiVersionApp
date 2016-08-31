package com.github.captain_miao.multiversionapp;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.captain_miao.multiversionapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    ActivityMainBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        ApplicationInfo appInfo = null;
        try {
            appInfo = this.getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            if(appInfo.metaData != null) {
                mBinding.setPackageName(appInfo.metaData.getString("PACKAGE_NAME"));
                mBinding.setCustomMetaData(appInfo.metaData.getString("CUSTOM_META_DATA"));
            } else {
                mBinding.setCustomMetaData("");
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        mBinding.setRunMode(getString(R.string.runMode));
    }
}
