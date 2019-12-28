package com.example.hwRoomDB.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hwRoomDB.R;
import com.example.hwRoomDB.adapter.RVAdapterFrag;
import com.example.hwRoomDB.database.HWEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class newHwFrag extends Fragment implements RVAdapterFrag.FragmentDelegate {

    @BindView(R.id.bt_cancel_frag)
    Button cancel;
    @BindView(R.id.bt_add_frag)
    Button confirm;
    @BindView(R.id.rv_week_frag)
    RecyclerView weekRV;
    @BindView(R.id.rv_day_frag)
    RecyclerView dayRV;
    @BindView(R.id.homework_description_frag)
    EditText homework_description;

    int weekNum = -1111;
    double dayNum = -1111;

    WarningDelegate warningDelegate;

    interface WarningDelegate {
        void addHomework(HWEntity hw);
    }

    newHwFrag(WarningDelegate warningDelegate){
        this.warningDelegate = warningDelegate;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this,view);
        String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday"};
        String[] weeks = new String[] {"1","2","3","4","5","6","7","8"};

        dayRV.setLayoutManager(new LinearLayoutManager(this.getContext()));
        weekRV.setLayoutManager(new LinearLayoutManager(this.getContext()));
        weekRV.setAdapter(new RVAdapterFrag(weeks,this));
        dayRV.setAdapter(new RVAdapterFrag(days,this));

        confirm.setOnClickListener(v -> {
            if(weekNum<0 || dayNum<0 || homework_description.getText().toString().equals("")){
                Toast.makeText(getActivity(), "Pls ...", Toast.LENGTH_SHORT).show();
            }else{
                HWEntity hw = new HWEntity(homework_description.getText().toString(), weekNum+dayNum/10.0, false);
                warningDelegate.addHomework(hw);
                getActivity().getSupportFragmentManager().popBackStack();
                homework_description.setText("");
            }
        });
        cancel.setOnClickListener(v -> getActivity().getSupportFragmentManager().popBackStack());
    }

    @Override
    public void getPosition(String value, int position) {
        try{
            Integer.parseInt(value);
            weekNum = position;
        }catch(NumberFormatException e){
            dayNum = position;
        }
    }
}
