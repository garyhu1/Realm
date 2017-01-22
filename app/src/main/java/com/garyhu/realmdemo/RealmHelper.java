package com.garyhu.realmdemo;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * 作者： garyhu.
 * 时间： 2016/11/28.
 */

public class RealmHelper {

    public static final String DB_NAME = "garyhu.realm";
    private Realm mRealm;
    private static RealmHelper instance;

    private RealmHelper() {
    }

    public static synchronized RealmHelper getInstance() {
        if (instance == null) {
            synchronized (RealmHelper.class) {
                if (instance == null)
                    instance = new RealmHelper();
            }
        }
        return instance;
    }


    protected Realm getRealm() {
        if (mRealm == null || mRealm.isClosed())
            mRealm = Realm.getDefaultInstance();
        return mRealm;
    }
    //--------------------------------------------------收藏相关----------------------------------------------------

    /**
     * 增加 收藏记录
     *
     * @param bean
     */
    public void insertStudent(Student bean) {
        getRealm().beginTransaction();
        getRealm().copyToRealm(bean);
        getRealm().commitTransaction();
    }

    /**
     * 删除 收藏记录
     *
     * @param id
     */
    public void deleteStudent(String id) {
        Student data = getRealm().where(Student.class).equalTo("id", id).findFirst();
        getRealm().beginTransaction();
        data.deleteFromRealm();
        getRealm().commitTransaction();
    }

    /**
     * 清空收藏
     */
    public void deleteAllCollection() {
        getRealm().beginTransaction();
        getRealm().delete(Student.class);
        getRealm().commitTransaction();
    }

    /**
     * 查询 收藏记录
     *
     * @param id
     * @return
     */
    public boolean queryCollectionId(String id) {
        RealmResults<Student> results = getRealm().where(Student.class).findAll();
        for (Student item : results) {
            if (item.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 收藏列表
     *
     * @return
     */
    public List<Student> getStudentList() {
        //使用findAllSort ,先findAll再result.sort排序
        RealmResults<Student> results = getRealm().where(Student.class).findAllSorted("time", Sort.DESCENDING);
        return getRealm().copyFromRealm(results);
    }
}
