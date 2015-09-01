package com.questtodo.questtodo.home;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.questtodo.questtodo.R;
import com.questtodo.questtodo.db.QuestTransaction;
import com.questtodo.questtodo.model.Quest;
import com.questtodo.questtodo.utils.FragmentManagerStock;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestListFragment extends Fragment {

    private View view;
    private LayoutInflater inflater;
    private RecyclerView recyclerView;
    private List<Quest> questList;
    private QuestTransaction questTransaction;

    public QuestListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setLayout(inflater, container);
        setQuestList();

        return view;
    }

    // setArgument에서 넘긴 Bundle 중 key "type"을 받음
    public int getShowIndex() {
        Bundle questType = getArguments();

        if (questType == null) return 0;
        else return questType.getInt("type");
    }

    private void setLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.layout_quest_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.questlist);
    }

    private void setQuestList() {

        questTransaction = new QuestTransaction(getActivity());

        int type = getShowIndex();

        if (type == 0) questList = questTransaction.readQuest();
        else questList = questTransaction.readQuest(type);

        recyclerView.setAdapter(new QuestAdapter(questList, R.layout.layout_quest, getActivity()));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        questTransaction.closeTransaction();
    }
}
