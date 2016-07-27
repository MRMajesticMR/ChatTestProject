package ru.majesticinc.cometchattestproject.ui.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sendbird.android.SendBird;
import com.sendbird.android.SendBirdException;
import com.sendbird.android.UserListQuery;
import com.sendbird.android.model.User;

import java.util.List;

import ru.majesticinc.cometchattestproject.R;
import ru.majesticinc.cometchattestproject.utils.FormatUtils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mailEdt;
    private Button enterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mailEdt = (EditText) findViewById(R.id.login_activity_edt_mail);
        enterBtn = (Button) findViewById(R.id.login_activity_btn_enter);

        enterBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_activity_btn_enter:
                String login = mailEdt.getText().toString();
                if(!login.isEmpty()) {
                    SendBird.login(SendBird.LoginOption.build(login).setUserName(FormatUtils.extractUsernameFromLogin(login)));
                    startActivity(new Intent(this, WorkingActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, "Введите имя пользователя", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
