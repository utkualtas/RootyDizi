package me.rootylabs.rootydizi.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import me.rootylabs.rootydizi.R;
import me.rootylabs.rootydizi.utils.SomeUtils;
import me.rootylabs.rootydizi.utils.SpaceItemDecoration;

public class HorizontalRecyclerGroup extends LinearLayout {

    /* Default Settings */
    private int mRecyclerItemSpace_DEFAULT = 16;
    private int mTitleSize_DEFAULT = 20;
    private int mDescSize_DEFAULT = 12;
    private int mTitleColor_DEFAULT = Color.parseColor("#EEEEEE");
    private int mDescColor_DEFAULT = Color.parseColor("#AAAAAA");
    private String mTitleText_DEFAULT = "Some Title";
    private String mDescText_DEEFAULT = "Here is the some description";

    /* Init Settings */
    private Context context;
    private SomeUtils someUtils;
    private int mRecyclerItemSpace;
    private int mTitleSize;
    private int mDescSize;
    private int mTitleColor;
    private int mDescColor;
    private String mTitleText;
    private String mDescText;

    /* Views */
    private RecyclerView mRecyclerView;
    private TextView mTitleTextView;
    private TextView mDescTextView;

    private RecyclerView.Adapter mRecyclerAdapter;

    @Inject
    public HorizontalRecyclerGroup(Context context) {
        super(context);
        this.context = context;
        mTitleText = mTitleText_DEFAULT;
        mTitleColor = mTitleColor_DEFAULT;
        mDescText =  mDescText_DEEFAULT;
        mDescColor = mDescColor_DEFAULT;
        mRecyclerItemSpace = mRecyclerItemSpace_DEFAULT;
        init();
    }

    public HorizontalRecyclerGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.HorizontalRecyclerGroup,
                0, 0);
        try {
            mTitleText = a.getString(R.styleable.HorizontalRecyclerGroup_titleText);
            if(mTitleText == null) mTitleText = mTitleText_DEFAULT;
            mTitleColor = a.getColor(R.styleable.HorizontalRecyclerGroup_titleColor, mTitleColor_DEFAULT);
            mDescText = a.getString(R.styleable.HorizontalRecyclerGroup_descText);
            if(mDescText == null) mDescText = mDescText_DEEFAULT;
            mDescColor = a.getColor(R.styleable.HorizontalRecyclerGroup_descColor, mDescColor_DEFAULT);
            mRecyclerItemSpace = a.getInt(R.styleable.HorizontalRecyclerGroup_recyclerItemSpace, mRecyclerItemSpace_DEFAULT);
        } finally {
            a.recycle();
        }



        init();
    }

    private void init(){
        this.setGravity(Gravity.CENTER);
        someUtils = new SomeUtils();
        this.setOrientation(LinearLayout.VERTICAL);
        initTitle();
        initDesc();
        initRecyclerView();
    }


    private void initTitle(){
        mTitleTextView = new TextView(context);
        mTitleTextView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        mTitleTextView.setTypeface(Typeface.DEFAULT_BOLD);
        mTitleTextView.setTextColor(mTitleColor);
        mTitleTextView.setTextSize( mTitleSize_DEFAULT);
        mTitleTextView.setText(mTitleText);
        addView(mTitleTextView);
    }

    private void initDesc(){
        mDescTextView = new TextView(context);
        mDescTextView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        mDescTextView.setTextColor(mDescColor);
        mDescTextView.setTextSize(mDescSize_DEFAULT);
        mDescTextView.setText(mDescText);
        addView(mDescTextView);
    }

    private void initRecyclerView(){
        mRecyclerView = new RecyclerView(context);
        mRecyclerView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        mRecyclerView.setPadding(0, someUtils.convertDpToPx(context, 16), 0, 0);
        addView(mRecyclerView);
    }

    public void setRecyclerView(@Nonnull RecyclerView.Adapter<?> adapter){
        mRecyclerAdapter = adapter;
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(someUtils.convertDpToPx(context, mRecyclerItemSpace)));
        mRecyclerView.setAdapter(adapter);
    }

    public int getRecyclerItemSpace() {
        return mRecyclerItemSpace;
    }

    public void setRecyclerItemSpace(int mRecyclerItemSpace) {
        this.mRecyclerItemSpace = mRecyclerItemSpace;
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(someUtils.convertDpToPx(context, mRecyclerItemSpace)));
        invalidate();
        requestLayout();
    }

    public int getTitleSize() {
        return mTitleSize;
    }

    public void setTitleSize(int mTitleSize) {
        this.mTitleSize = mTitleSize;
        mTitleTextView.setTextSize(mTitleSize);
        invalidate();
        requestLayout();
    }

    public int getDescSize() {
        return mDescSize;
    }

    public void setDescSize(int mDescSize) {
        this.mDescSize = mDescSize;
        mDescTextView.setTextSize(mDescSize);
        invalidate();
        requestLayout();
    }

    public int getTitleColor() {
        return mTitleColor;
    }

    public void setTitleColor(int mTitleColor) {
        this.mTitleColor = mTitleColor;
        mTitleTextView.setTextColor(mTitleColor);
        invalidate();
        requestLayout();
    }

    public int getDescColor() {
        return mDescColor;
    }

    public void setDescColor(int mDescColor) {
        this.mDescColor = mDescColor;
        mDescTextView.setTextColor(mDescColor);
        invalidate();
        requestLayout();
    }

    public String getTitleText() {
        return mTitleText;
    }

    public void setTitleText(String mTitleText) {
        this.mTitleText = mTitleText;
        mTitleTextView.setText(mTitleText);
        invalidate();
        requestLayout();
    }

    public String getDescText() {
        return mDescText;
    }

    public void setDescText(String mDescText) {
        this.mDescText = mDescText;
        mDescTextView.setText(mDescText);
        invalidate();
        requestLayout();
    }
}
