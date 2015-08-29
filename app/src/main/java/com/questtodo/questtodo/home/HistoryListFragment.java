package com.questtodo.questtodo.home;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.questtodo.questtodo.R;
import com.questtodo.questtodo.db.QuestTransaction;
import com.questtodo.questtodo.model.Quest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryListFragment extends Fragment {

    private View view;
    private LayoutInflater inflater;
    private RecyclerView recyclerView;
    private ArrayList<Quest> questList;
    private QuestTransaction questTransaction;
    private ListView historylist;

    public HistoryListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setLayout(inflater, container);
        setHistoryList();

        return view;
    }

    private void setLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.layout_history_list, container, false);

        historylist = (ListView) view.findViewById(R.id.lv_historylist);
    }


    private void setHistoryList() {

        SectionListViewAdapter adapter = new SectionListViewAdapter(getActivity());
        historylist.setAdapter(adapter);

        questTransaction = new QuestTransaction(getActivity());
        ArrayList<String> dateArray = questTransaction.readAllHistory();

        for (String date : dateArray) {
            questList = questTransaction.readDailyHistory(date);
            adapter.addSection(
                    date,
                    new HistoryAdapter(getActivity(), R.layout.layout_history_list_item, questList)
            );
        }

        historylist.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        questTransaction.closeTransaction();
    }
}
