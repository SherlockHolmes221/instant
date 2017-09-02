package myandroid.jike.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import myandroid.jike.AppConfig;
import myandroid.jike.R;


public class MineFragment extends Fragment{

        private Switch mNightSwitch;
        private boolean isNight = false;
        private LinearLayout layout;
        private TextView textView1;
        private TextView textView2;
        private TextView textView3;
        private TextView textView4;
        private TextView textView5;
        private TextView textView6;
        private TextView textView7;
        private TextView textView8;
        private TextView textView9;

        private CardView cardView1;
        private CardView cardView2;
        private CardView cardView3;
        private CardView cardView4;
        private CardView cardView5;
        private CardView cardView6;

    @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine,container,false);

                layout = (LinearLayout) view.findViewById(R.id.id_mine_layout);
                textView1 = (TextView) view.findViewById(R.id.id_mine_text1);
                textView2 = (TextView) view.findViewById(R.id.id_mine_text2);
                textView3 = (TextView) view.findViewById(R.id.id_mine_text3);
                textView4 = (TextView) view.findViewById(R.id.id_mine_text4);
                textView5 = (TextView) view.findViewById(R.id.id_mine_text5);
                textView6 = (TextView) view.findViewById(R.id.id_mine_text6);
                textView7 = (TextView) view.findViewById(R.id.id_mine_text7);
                textView8 = (TextView) view.findViewById(R.id.id_mine_text8);
                textView9 = (TextView) view.findViewById(R.id.id_mine_text9);

            cardView1  = (CardView) view.findViewById(R.id.id_mine_CardView1);
            cardView2  = (CardView) view.findViewById(R.id.id_mine_CardView2);
            cardView3  = (CardView) view.findViewById(R.id.id_mine_CardView3);
            cardView4  = (CardView) view.findViewById(R.id.id_mine_CardView4);
            cardView5  = (CardView) view.findViewById(R.id.id_mine_CardView5);
           cardView6  = (CardView) view.findViewById(R.id.id_mine_CardView6);



                mNightSwitch = (Switch) view.findViewById(R.id.id_mine_night_switch);
                mNightSwitch.setChecked(isNight);
                mNightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            isNight = !isNight;
                            changeSkinMode(isNight);
                            AppConfig appConfig = new AppConfig(getContext());
                            appConfig.setNightModeSwitch(isNight);
                        }
                });
        changeSkinMode(isNight);
        return view;
    }

        private void changeSkinMode(boolean isNight) {
                if(isNight){
                   layout.setBackgroundColor(getResources().getColor(R.color.bg_night));
                        textView1.setTextColor(getResources().getColor(R.color.text_night));
                        textView2.setTextColor(getResources().getColor(R.color.text_night));
                        textView3.setTextColor(getResources().getColor(R.color.text_night));
                        textView4.setTextColor(getResources().getColor(R.color.text_night));
                        textView5.setTextColor(getResources().getColor(R.color.text_night));
                        textView6.setTextColor(getResources().getColor(R.color.text_night));
                        textView7.setTextColor(getResources().getColor(R.color.text_night));
                        textView8.setTextColor(getResources().getColor(R.color.text_night));
                        textView9.setTextColor(getResources().getColor(R.color.text_night));

                    cardView1.setBackgroundColor(getResources().getColor(R.color.bg_night));
                    cardView2.setBackgroundColor(getResources().getColor(R.color.bg_night));
                    cardView3.setBackgroundColor(getResources().getColor(R.color.bg_night));
                    cardView4.setBackgroundColor(getResources().getColor(R.color.bg_night));
                    cardView5.setBackgroundColor(getResources().getColor(R.color.bg_night));
                    cardView6.setBackgroundColor(getResources().getColor(R.color.bg_night));

                        textView1.setBackgroundColor(getResources().getColor(R.color.bg_night));
                        textView2.setBackgroundColor(getResources().getColor(R.color.bg_night));
                        textView3.setBackgroundColor(getResources().getColor(R.color.bg_night));
                        textView4.setBackgroundColor(getResources().getColor(R.color.bg_night));
                        textView5.setBackgroundColor(getResources().getColor(R.color.bg_night));
                        textView6.setBackgroundColor(getResources().getColor(R.color.bg_night));
                        textView7.setBackgroundColor(getResources().getColor(R.color.bg_night));
                        textView8.setBackgroundColor(getResources().getColor(R.color.bg_night));
                        textView9.setBackgroundColor(getResources().getColor(R.color.bg_night));

                    //Toast.makeText(getContext(),"晚上好！",Toast.LENGTH_SHORT).show();

                }

                else{
                    layout.setBackgroundColor(getResources().getColor(R.color.background));
                    textView1.setTextColor(getResources().getColor(R.color.primary_text));
                    textView2.setTextColor(getResources().getColor(R.color.primary_text));
                    textView3.setTextColor(getResources().getColor(R.color.primary_text));
                    textView4.setTextColor(getResources().getColor(R.color.primary_text));
                    textView5.setTextColor(getResources().getColor(R.color.primary_text));
                    textView6.setTextColor(getResources().getColor(R.color.primary_text));
                    textView7.setTextColor(getResources().getColor(R.color.primary_text));
                    textView8.setTextColor(getResources().getColor(R.color.primary_text));
                    textView9.setTextColor(getResources().getColor(R.color.primary_text));

                    cardView1.setBackgroundColor(getResources().getColor(R.color.background));
                    cardView2.setBackgroundColor(getResources().getColor(R.color.background));
                    cardView3.setBackgroundColor(getResources().getColor(R.color.background));
                    cardView4.setBackgroundColor(getResources().getColor(R.color.background));
                    cardView5.setBackgroundColor(getResources().getColor(R.color.background));
                    cardView6.setBackgroundColor(getResources().getColor(R.color.background));

                    textView1.setBackgroundColor(getResources().getColor(R.color.background));
                    textView2.setBackgroundColor(getResources().getColor(R.color.background));
                    textView3.setBackgroundColor(getResources().getColor(R.color.background));
                    textView4.setBackgroundColor(getResources().getColor(R.color.background));
                    textView5.setBackgroundColor(getResources().getColor(R.color.background));
                    textView6.setBackgroundColor(getResources().getColor(R.color.background));
                    textView7.setBackgroundColor(getResources().getColor(R.color.background));
                    textView8.setBackgroundColor(getResources().getColor(R.color.background));
                    textView9.setBackgroundColor(getResources().getColor(R.color.background));

                }
                }
        }



