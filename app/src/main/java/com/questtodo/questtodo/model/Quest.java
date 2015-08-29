package com.questtodo.questtodo.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by eunjooim on 15. 8. 23..
 */
public class Quest extends RealmObject{

    @PrimaryKey
    private int quest_id;

    private int type;
    private int status;
    private int progress;
    private String title;
    private Date time;
    private Date complete;
    private int monster;

    public int getQuest_id() {
        return quest_id;
    }

    public void setQuest_id(int quest_id) {
        this.quest_id = quest_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }


    public Date getComplete() {
        return complete;
    }

    public void setComplete(Date complete) {
        this.complete = complete;
    }


    public int getMonster() {
        return monster;
    }

    public void setMonster(int monster) {
        this.monster = monster;
    }
}
