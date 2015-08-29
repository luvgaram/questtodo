package com.questtodo.questtodo.home;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.questtodo.questtodo.R;
import com.questtodo.questtodo.utils.FragmentManagerStock;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryHeaderFragment extends Fragment implements View.OnClickListener{

    private View view;
    private LayoutInflater inflater;


    public HistoryHeaderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setLayout(inflater, container);
        setHero();

        return view;
    }

    private void setLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.layout_history_header, container, false);

        ImageButton ibSword = (ImageButton) view.findViewById(R.id.ib_sword);

        ibSword.setOnClickListener(this);

    }

    private void setHero() {
        ImageView ivHero = (ImageView) view.findViewById(R.id.iv_hero);
        ivHero.setBackgroundResource(R.drawable.hero_b);

        AnimationDrawable heroAnimation = (AnimationDrawable) ivHero.getBackground();
        heroAnimation.start();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ib_sword)
                transactionFragments();
    }

    private void transactionFragments() {
        FragmentManager transaction = FragmentManagerStock.getFragmentManager();
        transaction.popBackStack();

    }
}
