package myandroid.jike.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jph.takephoto.app.TakePhotoFragment;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;

import java.util.ArrayList;

import myandroid.jike.R;
import myandroid.jike.activity.FaceResultActivity;
import myandroid.jike.utils.CustomHelper;

/**
 * 人脸识别
 */

public class FaceFragment extends TakePhotoFragment implements View.OnClickListener{
    private CustomHelper customHelper;
    Button take;
    Button pick;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_face,null);
        customHelper=CustomHelper.of(view);
        take = (Button) view.findViewById(R.id.btnPickByTake);
        pick = (Button) view.findViewById(R.id.btnPickBySelect);
        take.setOnClickListener(this);
        pick.setOnClickListener(this);
        return view;
    }


//    public void onClick(View view) {
//        customHelper.onClick(view,getTakePhoto());
//    }

    @Override
    public void takeCancel() {
        super.takeCancel();
    }


    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result,msg);
    }


    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        showImg(result.getImages());
    }


    private void showImg(ArrayList<TImage> images) {
        Intent intent=new Intent(getContext(),FaceResultActivity.class);
        intent.putExtra("images",images);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        customHelper.onClick(view,getTakePhoto());
    }
}