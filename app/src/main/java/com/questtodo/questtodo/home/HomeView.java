package com.questtodo.questtodo.home;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.os.Bundle;

import com.questtodo.questtodo.R;
import com.questtodo.questtodo.utils.FragmentManagerStock;
import com.questtodo.questtodo.utils.ScreenMesure;

public class HomeView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home_main);
        addFragment();
    }

    private void addFragment() {
        ScreenMesure screenMesure = new ScreenMesure(getApplicationContext());
        int sw = screenMesure.getScreenStats();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentManagerStock.initiateFragmentManager(fragmentManager);

        Fragment headerFragment = new QuestHeaderFragment();
        Fragment addingFragment = new QuestAddingFragment();
        Fragment listFragment = new QuestListFragment();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.header_fragment, headerFragment);
        transaction.add(R.id.quest_adding_fragment, addingFragment);
        transaction.add(R.id.quest_list_fragment, listFragment);

        if (sw >= 600) {
            Fragment historyHeaderFragment = new HistoryHeaderFragment();
            Fragment historyListFragment = new HistoryListFragment();

            transaction.add(R.id.history_header_fragment, historyHeaderFragment);
            transaction.add(R.id.history_list_fragment, historyListFragment);
        }

        if (sw >= 720) {
            Bundle questType = new Bundle();
            questType.putInt("type", 5);
            Fragment selectedListFragment = new QuestListFragment();
            selectedListFragment.setArguments(questType);

            transaction.add(R.id.selected_list_fragment, selectedListFragment);
        }
        transaction.commit();
    }

}
