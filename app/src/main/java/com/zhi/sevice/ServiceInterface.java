package com.zhi.sevice;

import com.zhi.domain.Student;

import java.util.List;

/**
 * Created by Administrator on 2016/10/14.
 */
public interface ServiceInterface {

    void add(Student student);

    void delete(int id);

    void update(Student student);

    Student find(int studentId);

    List<Student> paging(int offset, int maxResult);

    long getCount();
}
