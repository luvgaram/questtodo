package com.questtodo.questtodo.home;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.os.Bundle;

import com.questtodo.questtodo.R;
import com.questtodo.questtodo.utils.FragmentManagerStock;

public class HomeView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home_main);
        addFragment();
    }

    private void addFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentManagerStock.initiateFragmentManager(fragmentManager);

        Fragment headerFragment = new QuestHeaderFragment();
        Fragment addingFragment = new QuestAddingFragment();
        Fragment testFragment = new QuestListFragment();
        Fragment listFragment = new QuestListFragment();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.header_fragment, headerFragment);

        transaction.add(R.id.quest_adding_fragment, addingFragment);
        transaction.add(R.id.quest_list_fragment, listFragment);
        transaction.add(R.id.content_fragment, testFragment);

        transaction.commit();
    }

}
