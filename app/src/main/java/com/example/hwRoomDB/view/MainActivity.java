package com.example.hwRoomDB.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hwRoomDB.R;
import com.example.hwRoomDB.adapter.RVAdapter;
import com.example.hwRoomDB.database.HWEntity;
import com.example.hwRoomDB.presenter.Contract;
import com.example.hwRoomDB.presenter.HomePresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Contract.HomeworkView,
        RVAdapter.HomeworkDelegate,
        com.example.hwRoomDB.view.newHwFrag.WarningDelegate,
        hwViewerFrag.ViewHomeworkDelegate {

    private RecyclerView rv_homeworks;
    private TextView tv_noHomework;
    private ImageView newHomework;
    private Contract.HomeworkPresenter presenter;

    newHwFrag newHwFrag = new newHwFrag(this);
    hwViewerFrag fragViewer = new hwViewerFrag(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new HomePresenter(this);

        rv_homeworks = findViewById(R.id.rv_main);
        tv_noHomework = findViewById(R.id.info_textview);
        newHomework = findViewById(R.id.newhw_textview);

        presenter.getHomeworks();

        Bundle bundle = new Bundle();
        newHwFrag.setArguments(bundle);

        newHomework.setOnClickListener(v -> getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.myFrameLayout, newHwFrag)
                .addToBackStack(newHwFrag.getTag())
                .commit());
    }

    @Override
    public void displayHomeworks(List<HWEntity> homeworks) {
        RVAdapter rvAdapter = new RVAdapter(homeworks,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        rv_homeworks.setLayoutManager(layoutManager);
        rv_homeworks.setAdapter(rvAdapter);
    }

    @Override
    public void homeworksEmpty(String text) {
        tv_noHomework.setText(getString(R.string.info_text,text));
    }

    @Override
    public void displayError(String error) {
        tv_noHomework.setText(getString(R.string.info_text,error));
    }

    @Override
    public void doSomething(HWEntity homework) {
        Bundle bundle = new Bundle();
        bundle.putInt("key", homework.getHwID());
        bundle.putString("description",homework.getHwDescription());
        bundle.putDouble("date",homework.getHwDay());
        bundle.putBoolean("complete",homework.isHwDone());
        fragViewer.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.myFrameLayout, fragViewer)
                .addToBackStack(fragViewer.getTag())
                .commit();
    }

    @Override
    public Context getContext() {
        return this.getContext();
    }

    @Override
    public void addHomework(HWEntity hw) {
        presenter.insertHomework(hw);
        presenter.getHomeworks();
    }

    @Override
    public void completed(HWEntity homework) {
        presenter.modifyHomework(homework);
        presenter.getHomeworks();
    }
}
