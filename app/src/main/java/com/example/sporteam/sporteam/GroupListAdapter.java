package com.example.sporteam.sporteam;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by redbend on 8/12/16.
 */
public class GroupListAdapter extends BaseAdapter implements View.OnClickListener {

    /***************** Vars *****************/
    private ArrayList mData;
    private Activity mActivity;
    public Resources mRes;
    //private String name;


    /***************** Constructor *****************/

    public GroupListAdapter(Activity activity, ArrayList data) {
        mData = data;
        mActivity = activity;
        //mRes = resLocal;
    }

    /***************** abstract methods must implementation *****************/
    public int getCount() {
       return mData.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


       return convertView;
    }

    @Override
    public void onClick(View v) {
        Log.v("CustomAdapter", "=====Row button clicked=====");
    }

}
