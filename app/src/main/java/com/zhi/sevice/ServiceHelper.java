package com.zhi.sevice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zhi.dao.SQLiteHelper;
import com.zhi.domain.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/14.
 * <p/>
 * 注释掉的部分：也可以达到同样效果，但Android有提供了更好的方式来对数据库增删改查
 */
public class ServiceHelper implements ServiceInterface {
    private SQLiteHelper sqLiteHelper;

    public ServiceHelper(Context context) {
        sqLiteHelper = new SQLiteHelper(context);
    }

    /**
     * 往数据库中新增一条信息
     *
     * @param student 新增的用户信息
     */
    public void add(Student student) {
        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", student.getName());
        values.put("grade", student.getGrade());
        db.insert("student", null, values);

//        String sql = "insert into student(name, grade) values(?, ?)";
//        db.execSQL(sql, new Object[]{student.getName(), student.getGrade()+""});
        db.close();
    }

    /**
     * 按照传入的id号，删除数据库中的一条信息
     *
     * @param id
     */
    public void delete(int id) {
        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();

        db.delete("student", "id = ?", new String[]{id + ""});

//        String sql = "delete from student where id = ?";
//        db.execSQL(sql, new Object[]{id+""});
        db.close();
    }

    /**
     * 按照传入的用户id更改用户的其他信息
     *
     * @param student
     */
    public void update(Student student) {
        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", student.getName());
        values.put("grade", student.getGrade());
        db.update("student", values, "id=?", new String[]{student.getId() + ""});

//        String sql = "update student set name = ?, grade = ? where id = ?";
//        db.execSQL(sql, new Object[]{student.getName(), student.getGrade()+"", student.getId()+""});
        db.close();
    }

    /**
     * 根据传入的id号查找用户信息
     *
     * @param studentId
     * @return
     */
    public Student find(int studentId) {
        SQLiteDatabase db = sqLiteHelper.getReadableDatabase();

        Cursor cursor = db.query("student", null, "id = ?", new String[]{studentId + ""}, null, null, null, null);

//        String sql = "select * from student where id = ?";
//        Cursor cursor = db.rawQuery(sql, new String[]{studentId + ""});

        cursor.moveToFirst();
        int id = cursor.getInt(cursor.getColumnIndex("id"));
        String name = cursor.getString(cursor.getColumnIndex("name"));
        int grade = cursor.getInt(cursor.getColumnIndex("grade"));
        cursor.close();
        return new Student(id, name, grade);
    }

    /**
     * 跳过offset条数据，查看接下来的maxResult条信息
     *
     * @param offset
     * @param maxResult
     * @return
     */
    public List<Student> paging(int offset, int maxResult) {
        List<Student> students = new ArrayList<Student>();
        SQLiteDatabase db = sqLiteHelper.getReadableDatabase();

        Cursor cursor = db.query("student", null, null, null, null, null, "id asc", offset + "," + maxResult);
//        String sql = "select * from student order by id asc limit ?,?";
//        Cursor cursor = db.rawQuery(sql, new String[]{offset + "", maxResult + ""});


        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int grade = cursor.getInt(cursor.getColumnIndex("grade"));
            students.add(new Student(id, name, grade));
        }
        cursor.close();
        return students;
    }

    /**
     * 获取数据库中数据的总记录
     *
     * @return
     */
    public long getCount() {
        SQLiteDatabase db = sqLiteHelper.getReadableDatabase();

        Cursor cursor = db.query("student", new String[]{"count(*)"}, null, null, null, null, null);

//        String sql = "select count(*) from student";
//        Cursor cursor = db.rawQuery(sql, null);

        cursor.moveToFirst();
        long count = cursor.getLong(0);
        cursor.close();
        return count;
    }
}