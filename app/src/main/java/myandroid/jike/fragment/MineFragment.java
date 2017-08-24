package myandroid.jike.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import myandroid.jike.R;
import myandroid.jike.sqlite.DatabaseHelper;


public class MineFragment extends Fragment{

        private LinearLayout mLinearLayout;
        private List<String> attentionList = new ArrayList<>();
        private DatabaseHelper databaseHelper;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.mine,container,false);


        mLinearLayout = (LinearLayout) view.findViewById(R.id.id_mine_login);
            databaseHelper = new DatabaseHelper(view.getContext());
        return view;
    }


}
