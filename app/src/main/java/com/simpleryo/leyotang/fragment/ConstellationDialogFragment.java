package com.simpleryo.leyotang.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.base.BaseDialogFragment;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.view.MultiLineRadioGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * @author huanglei
 * @ClassNname：SexDialogFragment.java
 * @Describe 星座选择框
 * @time 2018/3/22 15:47
 */

public class ConstellationDialogFragment extends BaseDialogFragment {
    @ViewInject(R.id.radio_btn_aries)
    RadioButton radio_btn_aries;
    @ViewInject(R.id.radio_btn_tourus)
    RadioButton radio_btn_tourus;
    @ViewInject(R.id.radio_btn_gemini)
    RadioButton radio_btn_gemini;
    @ViewInject(R.id.radio_btn_cancer)
    RadioButton radio_btn_cancer;
    @ViewInject(R.id.radio_btn_leo)
    RadioButton radio_btn_leo;
    @ViewInject(R.id.radio_btn_virgo)
    RadioButton radio_btn_virgo;
    @ViewInject(R.id.radio_btn_libra)
    RadioButton radio_btn_libra;
    @ViewInject(R.id.radio_btn_scorpio)
    RadioButton radio_btn_scorpio;
    @ViewInject(R.id.radio_btn_sagittarius)
    RadioButton radio_btn_sagittarius;
    @ViewInject(R.id.radio_btn_capricorn)
    RadioButton radio_btn_capricorn;
    @ViewInject(R.id.radio_btn_pisces)
    RadioButton radio_btn_pisces;
    @ViewInject(R.id.radio_btn_aguarius)
    RadioButton radio_btn_aguarius;
    @ViewInject(R.id.constellation_radiogroup)
    MultiLineRadioGroup constellation_radiogroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mMainView == null) {
            mMainView = inflater
                    .inflate(R.layout.dialog_constellation, container, false);
            //对话框底部对齐, 这句话不能写onCreate里(包括父类的)，在onCreate里执行getDialog()获取的值为null
            getDialog().getWindow().setGravity(Gravity.CENTER);
            x.view().inject(this, mMainView);
            EventBus.getDefault().register(this);
            starSign = getArguments().getString("starSign");
            if (starSign != null) {
                if (starSign.equalsIgnoreCase("白羊座")) {
                    radio_btn_aries.setChecked(true);
                }
                if (starSign.equalsIgnoreCase("金牛座")) {
                    radio_btn_tourus.setChecked(true);
                }
                if (starSign.equalsIgnoreCase("双子座")) {
                    radio_btn_gemini.setChecked(true);
                }
                if (starSign.equalsIgnoreCase("巨蟹座")) {
                    radio_btn_cancer.setChecked(true);
                }
                if (starSign.equalsIgnoreCase("狮子座")) {
                    radio_btn_leo.setChecked(true);
                }
                if (starSign.equalsIgnoreCase("处女座")) {
                    radio_btn_virgo.setChecked(true);
                }
                if (starSign.equalsIgnoreCase("天秤座")) {
                    radio_btn_libra.setChecked(true);
                }
                if (starSign.equalsIgnoreCase("天蝎座")) {
                    radio_btn_scorpio.setChecked(true);
                }
                if (starSign.equalsIgnoreCase("射手座")) {
                    radio_btn_sagittarius.setChecked(true);
                }
                if (starSign.equalsIgnoreCase("摩羯座")) {
                    radio_btn_capricorn.setChecked(true);
                }
                if (starSign.equalsIgnoreCase("水瓶座")) {
                    radio_btn_pisces.setChecked(true);
                }
                if (starSign.equalsIgnoreCase("双鱼座")) {
                    radio_btn_aguarius.setChecked(true);
                }
            }
            constellation_radiogroup.setOnCheckedChangeListener(new MultiLineRadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(MultiLineRadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.radio_btn_aries:
                            starSign = "白羊座";
                            break;
                        case R.id.radio_btn_tourus:
                            starSign = "金牛座";
                            break;
                        case R.id.radio_btn_gemini:
                            starSign = "双子座";
                            break;
                        case R.id.radio_btn_cancer:
                            starSign = "巨蟹座";
                            break;
                        case R.id.radio_btn_leo:
                            starSign = "狮子座";
                            break;
                        case R.id.radio_btn_virgo:
                            starSign = "处女座";
                            break;
                        case R.id.radio_btn_libra:
                            starSign = "天秤座";
                            break;
                        case R.id.radio_btn_scorpio:
                            starSign = "天蝎座";
                            break;
                        case R.id.radio_btn_sagittarius:
                            starSign = "射手座";
                            break;
                        case R.id.radio_btn_capricorn:
                            starSign = "摩羯座";
                            break;
                        case R.id.radio_btn_pisces:
                            starSign = "水瓶座";
                            break;
                        case R.id.radio_btn_aguarius:
                            starSign = "双鱼座";
                            break;
                        default:
                            break;
                    }
                }
            });
        }
        ViewGroup parent = (ViewGroup) mMainView.getParent();
        if (parent != null) {
            parent.removeView(mMainView);
        }
        return mMainView;
    }

    String starSign;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateSex(BusEntity bus) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Event(value = {R.id.iv_close, R.id.tv_sure}, type = View.OnClickListener.class)
    private void Click(View v) {
        switch (v.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.tv_sure:
                EventBus.getDefault().post(new BusEntity(88, starSign));
                dismiss();
                break;
            default:
                break;
        }
    }
}
