package g.takeru.renshu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import g.takeru.renshu.kotlin.User;
import g.takeru.renshu.kotlin.UserListAdapter;
import kotlin.Unit;

public class CallKotlinActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // when using java to call kotlin high-order function
        List<User> userList = new ArrayList<>();
        new UserListAdapter(userList, v -> {
           // do Something
           return Unit.INSTANCE;
        });

//        Fragment fragment = MyFragment.newInstance(0);
    }
}
