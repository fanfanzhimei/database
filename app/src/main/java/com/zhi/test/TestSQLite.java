package com.zhi.test;

import android.test.AndroidTestCase;
import android.util.Log;
import com.zhi.dao.SQLiteHelper;
import com.zhi.domain.Student;
import com.zhi.sevice.ServiceHelper;
import com.zhi.sevice.ServiceInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/14.
 */
public class TestSQLite extends AndroidTestCase {

    private final String TAG = this.getClass().getSimpleName();

    public void testSQL() {
        SQLiteHelper helper = new SQLiteHelper(this.getContext());
        helper.getWritableDatabase();
    }

    public void testAdd() {
        ServiceInterface helper = new ServiceHelper(this.getContext());
        List<Student> students = new ArrayList<Student>();
        students.add(new Student("陈陈", 1));
        students.add(new Student("招招", 2));
        students.add(new Student("范范", 3));
        students.add(new Student("罗罗", 4));
        students.add(new Student("张张", 5));
        students.add(new Student("丽丽", 6));
        students.add(new Student("雨雨", 7));
        students.add(new Student("郁郁", 8));
        students.add(new Student("露露", 9));
        students.add(new Student("贝贝", 10));
        for (Student student : students) {
            helper.add(student);
        }
    }

    public void testDelete() {
        ServiceInterface helper = new ServiceHelper(this.getContext());
        helper.delete(2);
    }

    public void testUpdate() {
        ServiceInterface helper = new ServiceHelper(this.getContext());
        Student student = new Student(5, "赵本山大叔", 100);
        helper.update(student);
    }

    public void testFind() {
        ServiceInterface helper = new ServiceHelper(this.getContext());
        Student student = helper.find(8);
        Log.e(TAG, student.toString());
    }

    public void testPaging() {
        ServiceInterface helper = new ServiceHelper(this.getContext());
        List<Student> students = helper.paging(2, 3);

        for (Student student : students) {
            Log.e(TAG, student.toString());
        }
    }

    public void testCount() {
        ServiceInterface helper = new ServiceHelper(this.getContext());
        long count = helper.getCount();
        Log.e(TAG, count + "");
    }
}