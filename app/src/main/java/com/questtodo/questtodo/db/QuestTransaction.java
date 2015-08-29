package com.questtodo.questtodo.db;

import android.content.Context;

import com.questtodo.questtodo.model.Quest;
import com.questtodo.questtodo.utils.TimeUtils;

import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by eunjooim on 15. 8. 23..
 */
public class QuestTransaction {

    private Context context;
    private Realm realm;
    private TimeUtils timeUtils;

    public QuestTransaction(Context context) {
        this.context = context;
        realm = Realm.getInstance(context);
        timeUtils = new TimeUtils();
    }

    public void closeTransaction() {
        realm.close();
    }

    public ArrayList<Quest> readQuest() {

        ArrayList<Quest> resultArray = new ArrayList<>();
        RealmQuery<Quest> query = realm.where(Quest.class);

        // 지워지지 않았고 진행 상태인 퀘스트를 모두 부름
        query.equalTo("status", 1)
                .equalTo("progress", 1);

        RealmResults<Quest> results = query.findAll();
        results.sort("time", RealmResults.SORT_ORDER_DESCENDING);

        for (Quest q : results) {
            resultArray.add(q);
        }

        return resultArray;
    }

    public ArrayList<Quest> readQuest(int type) {

        ArrayList<Quest> resultArray = new ArrayList<>();
        RealmQuery<Quest> query = realm.where(Quest.class);

        // 지워지지 않았고 진행 상태이며 같은 타입의 퀘스트를 모두 부름
        query.equalTo("status", 1)
                .equalTo("progress", 1)
                .equalTo("type", type);

        RealmResults<Quest> results = query.findAll();
        results.sort("time", RealmResults.SORT_ORDER_DESCENDING);

        for (Quest q : results) {
            resultArray.add(q);
        }

        return resultArray;
    }

    public ArrayList<Quest> readDailyHistory(String date) {

        if (date == null) return null;

        ArrayList<Quest> resultArray = new ArrayList<>();
        RealmQuery<Quest> query = realm.where(Quest.class);

        Date targetDate = timeUtils.getDateFromStringDate(date);
        Date nextDate = new Date(targetDate.getTime() + (1000 * 60 * 60 * 24));

        // 지워지지 않았고 완료 상태이며 해당 날짜 내의 퀘스트(히스토리)만 부름
        query.equalTo("status", 1)
                .equalTo("progress", 0)
                .greaterThanOrEqualTo("complete", targetDate)
                .lessThan("complete", nextDate);

        RealmResults<Quest> results = query.findAll();
        results.sort("complete", RealmResults.SORT_ORDER_DESCENDING);

        for (Quest q : results) {
            resultArray.add(q);
        }

        return resultArray;
    }

    public ArrayList<String> readAllHistory() {

        ArrayList<String> dateArray = new ArrayList<>();
        RealmQuery<Quest> query = realm.where(Quest.class);

        // 지워지지 않았고 완료 상태인 퀘스트(히스토리)만 부름
        query.equalTo("status", 1)
                .equalTo("progress", 0);

        RealmResults<Quest> results = query.findAll();
        results.sort("complete", RealmResults.SORT_ORDER_DESCENDING);

        for (Quest q : results) {
            Date date = q.getTime();
            String stringDate = timeUtils.getStringDate(date);

            if (!dateArray.contains(stringDate)) {
                dateArray.add(stringDate);
            }
        }

        return dateArray;
    }

    public Quest writeQuest(final int type, final String title, final Date time, final Date complete, final int monster) {

        final Quest[] quest = new Quest[1];

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                // int type, int status, int progress, String title, Date time, Date complete, int monster
                quest[0] = realm.createObject(Quest.class);

                quest[0].setQuest_id(getNextKey());
                quest[0].setType(type);
                quest[0].setStatus(1);
                quest[0].setProgress(1);
                quest[0].setTitle(title);
                quest[0].setTime(time);
                quest[0].setComplete(complete);
                quest[0].setMonster(monster);

            }
        });

        return quest[0];
    }

    public int getNextKey() {
        return (int) realm.where(Quest.class).maximumInt("quest_id") + 1;
    }

    public void completeQuest(int completedId) {
        realm.beginTransaction();

        Quest deleteQuest = realm.where(Quest.class).equalTo("quest_id", completedId).findFirst();
        deleteQuest.setProgress(0);
        deleteQuest.setComplete(new Date());

        realm.commitTransaction();
    }

    public void deleteQuest(int deleteId) {
        realm.beginTransaction();

        Quest deleteQuest = realm.where(Quest.class).equalTo("quest_id", deleteId).findFirst();
        deleteQuest.setStatus(0);

        realm.commitTransaction();
    }
}
