package g.takeru.renshu;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import g.takeru.renshu.kotlin.User;
import g.takeru.renshu.kotlin.UserListAdapter;
import kotlin.Unit;

public class CallKotlinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // when using java to call kotlin high-order function
        List<User> userList = new ArrayList<>();
        new UserListAdapter(this, userList, v -> {
           // do Something
           return Unit.INSTANCE;
        });

//        Fragment fragment = MyFragment.newInstance(0);
    }
}
