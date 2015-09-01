package com.questtodo.questtodo.home;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.questtodo.questtodo.R;
import com.questtodo.questtodo.db.QuestTransaction;
import com.questtodo.questtodo.model.Quest;
import com.questtodo.questtodo.utils.FragmentManagerStock;
import com.questtodo.questtodo.utils.ResourceId;
import com.questtodo.questtodo.utils.ScreenMesure;

import java.util.List;

/**
 * Created by eunjooim on 15. 8. 23..
 */
public class QuestAdapter extends RecyclerView.Adapter<QuestAdapter.ViewHolder> {


    private List<Quest> questList;
    private int itemLayout;
    public Context context;
    private ImageView ivMonster;

    public QuestAdapter(List<Quest> items, int itemLayout, Context context){

        this.questList = items;
        this.itemLayout = itemLayout;
        this.context = context;
    }

    // 레이아웃을 만들어서 ViewHolder에 저장

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(itemLayout, viewGroup, false);
        return new ViewHolder(view);
    }


    // listView getView 를 대체해서 넘겨 받은 데이터를 화면에 출력
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        Quest questItem = questList.get(position);
        ResourceId resourceId = new ResourceId();

        int questTile = resourceId.getTileResource(questItem.getType());
        int questMonster = resourceId.getMonsterResource(questItem.getMonster());

        viewHolder.rlBackground.setBackgroundResource(questTile);
        viewHolder.tvTitle.setText(questItem.getTitle());
        viewHolder.ivMonster.setImageResource(questMonster);
        viewHolder.itemView.setTag(questItem);

    }


    @Override
    public int getItemCount() {
        return questList.size();
    }

    // 뷰 재활용을 위한 viewHolder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public RelativeLayout rlBackground;
        public TextView tvTitle;
        public ImageView ivMonster;

        public ViewHolder(View itemView) {
            super(itemView);

            rlBackground = (RelativeLayout) itemView.findViewById(R.id.rl_background);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            ivMonster = (ImageView) itemView.findViewById(R.id.iv_monster);

            ivMonster.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.equals(ivMonster)) {
                removeQuest(getAdapterPosition());
                refreshHistory();
            }
        }

        private void refreshHistory() {
            ScreenMesure screenMesure = new ScreenMesure(context);

            if (screenMesure.getScreenStats() >= 600) {

                Fragment listFragment = new HistoryListFragment();

                FragmentTransaction transaction = FragmentManagerStock.getFragmentManager().beginTransaction();
                transaction.replace(R.id.history_list_fragment, listFragment);
                transaction.commit();
            }
        }


    }

    public void removeQuest(int position) {
        Quest quest = questList.get(position);
        int deleteId = quest.getQuest_id();

        QuestTransaction questTransaction = new QuestTransaction(context);
        questTransaction.completeQuest(deleteId);
        questTransaction.closeTransaction();

        questList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }
}
