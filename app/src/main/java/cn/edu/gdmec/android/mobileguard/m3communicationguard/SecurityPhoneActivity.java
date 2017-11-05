package cn.edu.gdmec.android.mobileguard.m3communicationguard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.mobileguard.R;
import cn.edu.gdmec.android.mobileguard.m3communicationguard.adapter.BlackContactAdpater;
import cn.edu.gdmec.android.mobileguard.m3communicationguard.db.dao.BlackNumberDao;
import cn.edu.gdmec.android.mobileguard.m3communicationguard.entity.BlackContactInfo;

public class SecurityPhoneActivity extends AppCompatActivity implements View.OnClickListener{
/** 有黑名单时，显示的帧布局*/
private FrameLayout mHaveBlackNumber;
    /** 没有黑名单时，显示的帧布局*/
    private FrameLayout mNoBlackNumber;
    private BlackNumberDao dao;
    private ListView mListView;
    private int pagenumber = 0;
    private int pagesize = 4;
    private int totalNumber;
    private List<BlackContactInfo> pageBlackNumeber = new ArrayList<BlackContactInfo>();
    private BlackContactAdpater adpater;

    private void fillData(){
        dao = new BlackNumberDao(SecurityPhoneActivity.this);
        totalNumber = dao.getTotalNumber();
        if(totalNumber == 0){
            //数据库中没有黑名单数据
            mHaveBlackNumber.setVisibility(View.GONE);
            mNoBlackNumber.setVisibility(View.VISIBLE);
        }else if (totalNumber > 0){
            //数据库中含有黑名单数据
            mHaveBlackNumber.setVisibility(View.VISIBLE);
            mNoBlackNumber.setVisibility(View.GONE);
            pagenumber = 0;
            if(pageBlackNumeber.size() > 0){
                pageBlackNumeber.clear();
            }
            pageBlackNumeber.addAll(dao.getPageBlackNumber(pagenumber,pagesize));
            if(adpater == null){
                adpater = new BlackContactAdpater(pageBlackNumeber,SecurityPhoneActivity.this);
                adpater.setCallBack(new BlackContactAdpater.BlackConactCallBack(){
                    @Override
                    public void DataSizeChanged(){
                        fillData();
                    }
                });
                mListView.setAdapter(adpater);
            }else {
                adpater.notifyDataSetChanged();
            }
        }
    }

    private void initView(){
        findViewById(R.id.rl_titlebar).setBackgroundColor(
                getResources().getColor(R.color.bright_purple));
        ImageView mLeftImgv = (ImageView) findViewById(R.id.imgv_leftbtn);
        ((TextView) findViewById(R.id.tv_title)).setText("通讯卫士");
        mLeftImgv.setOnClickListener(this);
        mLeftImgv.setImageResource(R.drawable.back);
        mHaveBlackNumber = (FrameLayout) findViewById(R.id.fl_haveblacknumber);
        mNoBlackNumber = (FrameLayout) findViewById(R.id.fl_noblacknumber);
        findViewById(R.id.btn_addblacknumber).setOnClickListener(this);
        mListView = (ListView) findViewById(R.id.lv_blacknumbers);
        mListView.setOnClickListener(new AbsListView.OnScrollListener(){
            @Override
            public void  onScrollStateChanged(AbsListView absListView,int i){
                switch(i){
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING;
                        int lastVisiblePosition = mListView.getLastVisiblePosition();
                        if(lastVisiblePosition == pageBlackNumeber.size() -1){
                            pagenumber++;
                            if(pagenumber * pagesize >= totalNumber){
                                Toast.makeText(SecurityPhoneActivity.this,"没有更多的数据了",Toast.LENGTH_LONG).show();
                            }else {
                                pageBlackNumeber.addAll(dao.getPageBlackNumber(
                                        pagenumber,pagesize
                                ));
                                adpater.notifyDataSetChanged();
                            }
                        }
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView absListView,int i,int i1,int i2){

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_phone);
        initView();
        fillData();
    }
    @Override
    public  void onClick(View view){
        switch(view.getId()){
            case R.i
        }
    }
}
