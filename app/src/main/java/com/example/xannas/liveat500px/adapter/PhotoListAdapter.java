package com.example.xannas.liveat500px.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.xannas.liveat500px.R;
import com.example.xannas.liveat500px.dao.PhotoItemCollectionDao;
import com.example.xannas.liveat500px.dao.PhotoItemDao;
import com.example.xannas.liveat500px.datatype.MutableInteger;
import com.example.xannas.liveat500px.manager.PhotoListManager;
import com.example.xannas.liveat500px.view.PhotoListItem;

/**
 * Created by xannas on 1/16/2017 AD.
 */

public class PhotoListAdapter extends BaseAdapter {

    MutableInteger lastPositionInteger;

    PhotoItemCollectionDao dao;

    public PhotoListAdapter(MutableInteger lastPositionInteger) {
        this.lastPositionInteger = lastPositionInteger;
    }

    public void setDao(PhotoItemCollectionDao dao) {
        this.dao = dao;
    }

    @Override
    public int getCount() {
        if (dao == null)
            return 1;
        if (dao.getData() == null)
            return 1;
        return dao.getData().size() + 1;
    }

    @Override
    public Object getItem(int i) {
        return dao.getData().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position == getCount()-1 ? 1 : 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (i == getCount() - 1) {
            // Progress Bar
            ProgressBar item;
            if (view != null)
                item = (ProgressBar) view;
            else
                item = new ProgressBar(viewGroup.getContext());
            return item;

        }
        PhotoListItem item;
        if (view != null)
            item = (PhotoListItem) view;
        else
            item = new PhotoListItem(viewGroup.getContext());

        PhotoItemDao dao = (PhotoItemDao) getItem(i);
        item.setNameText(dao.getCaption());
        item.setDescriptionText(dao.getUsername() + "\n" + dao.getCamera());
        item.setImageUrl(dao.getImageUrl());

        if (i > lastPositionInteger.getValue()) {
            Animation anim = AnimationUtils.loadAnimation(viewGroup.getContext(),
                    R.anim.up_from_bottom);
            item.startAnimation(anim);
            lastPositionInteger.setValue(i);
        }

        return item;

    }

    public void increaseLastPosition(int amount) {
        lastPositionInteger.setValue(lastPositionInteger.getValue()+amount);
    }
}
