package g.takeru.renshu.kotlin

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by takeru on 2017/9/11.
 */

// origin java code
//public class User {
//    private String name;
//    private int age;
//
//    public User(String name, int age){
//        this.name = name;
//        this.age = age;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//}

@Parcelize
data class User(val name: String, val age: Int) : Parcelable
