package com.garyhu.realmdemo;

import android.app.Activity;
import android.app.Application;

import java.util.HashSet;
import java.util.Set;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;

/**
 * 作者： garyhu.
 * 时间： 2016/11/28.
 */

public class MyApp extends Application {

    private static MyApp instance;
    private Set<Activity> allActivities;

    public static synchronized MyApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //初始化realm
        initRealm();
    }


    private void initRealm() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name(RealmHelper.DB_NAME)
                .schemaVersion(2)
                .rxFactory(new RealmObservableFactory())
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
