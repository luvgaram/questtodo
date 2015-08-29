package com.questtodo.questtodo.home;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.questtodo.questtodo.R;
import com.questtodo.questtodo.db.QuestTransaction;
import com.questtodo.questtodo.model.Quest;
import com.questtodo.questtodo.utils.FragmentManagerStock;

import java.util.Date;
import java.util.Random;

import io.realm.Realm;


public class QuestAddingFragment extends Fragment implements View.OnClickListener{

    private View view;
    private LayoutInflater inflater;
    private QuestTransaction questTransaction;
    private EditText etTitle;
    private int type;
    private static int MONSTERNO = 13;

    public QuestAddingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setLayout(inflater, container);

        return view;
    }

    private void setLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.layout_quest_adding, container, false);
        type = 0;

        ImageButton ibGreen = (ImageButton) view.findViewById(R.id.ib_green);
        ImageButton ibBlue = (ImageButton) view.findViewById(R.id.ib_blue);
        ImageButton ibBeige = (ImageButton) view.findViewById(R.id.ib_beige);
        ImageButton ibOrange = (ImageButton) view.findViewById(R.id.ib_orange);
        ImageButton ibGrey = (ImageButton) view.findViewById(R.id.ib_grey);
        ImageButton ibBack = (ImageButton) view.findViewById(R.id.ib_back);

        ibGreen.setOnClickListener(this);
        ibBlue.setOnClickListener(this);
        ibBeige.setOnClickListener(this);
        ibOrange.setOnClickListener(this);
        ibGrey.setOnClickListener(this);
        ibBack.setOnClickListener(this);

        etTitle = (EditText) view.findViewById(R.id.et_title);
        etTitle.setOnKeyListener(new EditMessageOnKeyListener());
    }

    private int setMonsterNo() {
        Random random = new Random();
        return random.nextInt(MONSTERNO);
    }


    class EditMessageOnKeyListener implements View.OnKeyListener {

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {

            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                Date now = new Date();

                questTransaction = new QuestTransaction(getActivity());
                questTransaction.writeQuest(type, etTitle.getText().toString(), now, now, setMonsterNo());

                Fragment addingFragment = new QuestAddingFragment();
                Fragment listFragment = new QuestListFragment();

                FragmentTransaction transaction = FragmentManagerStock.getFragmentManager().beginTransaction();
                transaction.replace(R.id.quest_adding_fragment, addingFragment);
                transaction.replace(R.id.quest_list_fragment, listFragment);
                transaction.commit();


                return true;
            }

            return false;
        }
    }

    @Override
    public void onClick(View v) {

        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.rl_background);
        switch (v.getId()) {
            case R.id.ib_green:
                type = 0;
                relativeLayout.setBackgroundColor(Color.rgb(152, 203, 60));
                break;

            case R.id.ib_blue:
                type = 1;
                relativeLayout.setBackgroundColor(Color.rgb(150, 229, 238));
                break;

            case R.id.ib_beige:
                type = 2;
                relativeLayout.setBackgroundColor(Color.rgb(240, 234, 211));
                break;

            case R.id.ib_orange:
                type = 3;
                relativeLayout.setBackgroundColor(Color.rgb(236, 134, 58));
                break;

            case R.id.ib_grey:
                type = 4;
                relativeLayout.setBackgroundColor(Color.rgb(198, 211, 212));
                break;

            case R.id.ib_back:
                etTitle.setText("");
                type = 0;
                relativeLayout.setBackgroundColor(Color.rgb(152, 203, 60));
                break;

            default:
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
