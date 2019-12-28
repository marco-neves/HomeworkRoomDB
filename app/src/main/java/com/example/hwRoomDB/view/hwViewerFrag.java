package com.example.hwRoomDB.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hwRoomDB.R;
import com.example.hwRoomDB.database.HWEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class hwViewerFrag extends Fragment {
    @BindView(R.id.complete_task_fragview)
    Button completeButton;
    @BindView(R.id.go_back_fragview)
    Button cancelBT;
    @BindView(R.id.homework_description_fragview)
    TextView descriptionTV;
    @BindView(R.id.tv_date_fragview)
    TextView dateTV;

    ViewHomeworkDelegate delegate;

    interface ViewHomeworkDelegate{
        void completed(HWEntity homework);
    }

    public hwViewerFrag(ViewHomeworkDelegate delegate) {
        this.delegate = delegate;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewhw_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        int key = bundle.getInt("key");
        String description = bundle.getString("description");
        double date = bundle.getDouble("date");
        boolean complete = bundle.getBoolean("complete");

        final HWEntity homework = new HWEntity(key,description,date,complete);

        if(complete){
            completeButton.setVisibility(View.INVISIBLE);
        }
        descriptionTV.setText(description);
        dateTV.setText(homework.dateToString());

        completeButton.setOnClickListener(v -> {
            homework.setHwDone(true);
            delegate.completed(homework);
            getActivity().getSupportFragmentManager().popBackStack();
        });
        cancelBT.setOnClickListener(v -> getActivity().getSupportFragmentManager().popBackStack());
    }
}
