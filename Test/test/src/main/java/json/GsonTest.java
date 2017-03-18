package json;

import com.google.gson.Gson;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunjinfei on 2017/3/12.
 */
class Student {

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student() {
    }

    public List<Student> getList() {
        return null;
    }

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

public class GsonTest {

    @Test
    public void test1() throws NoSuchMethodException {


//        准备数据
        List students = new ArrayList<Student>(120) {
        };
        for (int i = 1; i <= 100; i++) {
            students.add(new Student(i, "tom_" + i));
        }
//        把list集合转化成json字符串
        String sStr = new Gson().toJson(students, students.getClass().getGenericSuperclass());
//        把json字符串转化成list集合
        List list = new Gson().fromJson(sStr, students.getClass().getGenericSuperclass());

        System.out.println(list.toArray().getClass().getComponentType());
        System.out.println(list);

    }
}
