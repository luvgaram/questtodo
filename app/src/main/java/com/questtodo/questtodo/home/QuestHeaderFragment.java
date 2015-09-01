package com.questtodo.questtodo.home;


import android.app.FragmentTransaction;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.questtodo.questtodo.R;
import com.questtodo.questtodo.utils.FragmentManagerStock;
import com.questtodo.questtodo.utils.ScreenMesure;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestHeaderFragment extends Fragment implements View.OnClickListener{

    private View view;
    private LayoutInflater inflater;

    private QuestListFragment newFragment;

    public QuestHeaderFragment() {
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
        view = inflater.inflate(R.layout.layout_quest_header, container, false);

        ImageButton ibGreen = (ImageButton) view.findViewById(R.id.ib_green);
        ImageButton ibBlue = (ImageButton) view.findViewById(R.id.ib_blue);
        ImageButton ibBeige = (ImageButton) view.findViewById(R.id.ib_beige);
        ImageButton ibOrange = (ImageButton) view.findViewById(R.id.ib_orange);
        ImageButton ibGrey = (ImageButton) view.findViewById(R.id.ib_grey);

        ibGreen.setOnClickListener(this);
        ibBlue.setOnClickListener(this);
        ibBeige.setOnClickListener(this);
        ibOrange.setOnClickListener(this);
        ibGrey.setOnClickListener(this);

        ScreenMesure screenMesure = new ScreenMesure(getActivity());

        // sw600 미만 폰만 history 버튼 클릭 리스너 생성
        if (screenMesure.getScreenStats() < 600) {
            ImageButton ibHistory = (ImageButton) view.findViewById(R.id.ib_history);
            ibHistory.setOnClickListener(this);
        }

        // sw720 미만 폰만 all 버튼 클릭 리스너 생성
        if (screenMesure.getScreenStats() < 720) {
            ImageButton ibWhite = (ImageButton) view.findViewById(R.id.ib_white);
            ibWhite.setOnClickListener(this);
        }
    }

    private void setHero() {
        ImageView ivHero = (ImageView) view.findViewById(R.id.iv_hero);
        ivHero.setBackgroundResource(R.drawable.hero);

        AnimationDrawable heroAnimation = (AnimationDrawable) ivHero.getBackground();
        heroAnimation.start();
    }

    @Override
    public void onClick(View v) {

        int resource = R.id.quest_list_fragment;

        // sw720 이상일 때는 다른 레이아웃에 그림
        ScreenMesure screenMesure = new ScreenMesure(getActivity());
        if (screenMesure.getScreenStats() >= 720) resource = R.id.selected_list_fragment;

        switch (v.getId()) {
            case R.id.ib_history:
                transactionHistoryFragments();
                break;

            case R.id.ib_white:
                transactionFragment();
                break;

            case R.id.ib_green:
                transactionFragment(resource, 5);
                break;

            case R.id.ib_blue:
                transactionFragment(resource, 1);
                break;

            case R.id.ib_beige:
                transactionFragment(resource, 2);
                break;

            case R.id.ib_orange:
                transactionFragment(resource, 3);
                break;

            case R.id.ib_grey:
                transactionFragment(resource, 4);
                break;

            default:
                break;
        }
    }

    private void transactionFragment() {
        QuestListFragment newFragment = new QuestListFragment();

        FragmentTransaction transaction = FragmentManagerStock.getFragmentManager().beginTransaction();
        transaction.replace(R.id.quest_list_fragment, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void transactionFragment(int resource, int id) {
        Bundle questType = new Bundle();
        questType.putInt("type", id);

        newFragment = new QuestListFragment();
        newFragment.setArguments(questType);

        FragmentTransaction transaction = FragmentManagerStock.getFragmentManager().beginTransaction();
        transaction.replace(resource, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void transactionHistoryFragments() {
        HistoryHeaderFragment headerFragment = new HistoryHeaderFragment();
        HistoryListFragment listFragment = new HistoryListFragment();

        FragmentTransaction transaction = FragmentManagerStock.getFragmentManager().beginTransaction();
        transaction.replace(R.id.header_fragment, headerFragment);
        transaction.add(R.id.content_fragment, listFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
