package com.employee.pagetan.employeemodel;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.employee.pagetan.sdk.AndroidService;
import com.employee.pagetan.sdk.base.QueryState;
import com.employee.pagetan.sdk.base.entity.User;
import com.employee.pagetan.sdk.base.entity.vm.QueryStateChangeListener;
import com.employee.pagetan.sdk.base.entity.vm.UserVM;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private TextView infoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) this.findViewById(R.id.textView);
        infoTextView = (TextView) this.findViewById(R.id.infoTextView);

        textView.setOnClickListener(this);

    }





    int count = 0;
    @Override
    public void onClick(View v) {
        count ++;
        queryUser(count);
        count ++;
        queryUser(count);
        count ++;
        queryUser(count);
    }


    private void queryUser(final int uid){

        UserVM userVM = AndroidService.getInstance().getUseVM(uid);
        String text = '\n'+'\n'+infoTextView.getText().toString()+'\n';
        infoTextView.setText(text + "click to query ");
        userVM.subscribe(new QueryStateChangeListener<User>() {
            @Override
            public void onStateChanged(int queryState, @Nullable final User value, @Nullable final Exception e) {
                String text = infoTextView.getText().toString()+'\n';
                switch (queryState) {
                    case QueryState.LOADING:
                        infoTextView.setText(text+"loading user:" + uid);
                        break;
                    case QueryState.LOAD_SUCCESS:
                        infoTextView.setText(text+value.toString() + uid);
                        break;
                    case QueryState.LOAD_FAILURE:
                        infoTextView.setText(text+e.getMessage() + uid);
                        break;
                }
            }
        });
        AndroidService.getInstance().queryUserInfo(uid);
    }
}

