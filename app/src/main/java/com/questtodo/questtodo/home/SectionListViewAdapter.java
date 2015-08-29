package com.questtodo.questtodo.home;

/**
 * Created by eunjooim on 15. 8. 29..
 * Based on http://warmz.tistory.com/673?viewbar
 */
import java.util.LinkedHashMap;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import com.questtodo.questtodo.R;

public class SectionListViewAdapter extends BaseAdapter {

    public final static int TYPE_SECTION_HEADER = 0;

    public ArrayAdapter<String> headers;
    public Map<String, HistoryAdapter> sections = new LinkedHashMap<>();

    // 헤더 생성
    public SectionListViewAdapter(Context context) {
        headers = new ArrayAdapter<>(context, R.layout.layout_history_list_title);
    }

    // 헤더, 섹션 필드 추가 메소드
    public void addSection(String section, HistoryAdapter adapter) {
        this.headers.add(section);
        this.sections.put(section, adapter);
    }

    // 모든 아이템의 수 반환(+ 헤더 필드)
    @Override
    public int getCount() {
        int total = 0;
        for (HistoryAdapter adapter : this.sections.values())
            total += adapter.getCount() + 1;

        return total;
    }

    @Override
    public Object getItem(int position) {
        for (Object section : this.sections.keySet()) {
            HistoryAdapter adapter = sections.get(section);
            int size = adapter.getCount() + 1;

            // position이 해당 section 안에 있는지 체크
            if (position == 0) return section;
            if (position < size) return adapter.getItemId(position - 1);

            // position이 섹션의 크기를 넘어간다면 다음 섹션으로 점프
            position -= size;
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int sectionNum = 0;
        for (Object section : this.sections.keySet()) {
            HistoryAdapter adapter = sections.get(section);
            int size = adapter.getCount() + 1;

            // position이 해당 section 안에 있는지 체크
            if (position == 0) return headers.getView(sectionNum, convertView, parent);
            if (position < size) return adapter.getView(position - 1, convertView, parent);

            // position이 섹션의 크기를 넘어간다면 다음 섹션으로 점프
            position -= size;
            sectionNum++;
        }
        return null;
    }

    // 뷰 타입의 크기(section의 종류)를 반환
    public int getViewTypeCount() {
        int total = 1;
        for (HistoryAdapter adapter : this.sections.values())
            total += adapter.getViewTypeCount();

        return total;
    }

    // 아이템(필드)의 타입을 반환
    public int getItemViewType(int position) {
        int type = 1;
        for (Object section : this.sections.keySet()) {

            // 해당 섹션의 어댑터와 어댑터의 아이템 크기를 반환
            HistoryAdapter adapter = sections.get(section);
            int size = adapter.getCount() + 1;  // +1 : header

            // position이 해당 section 안에 있는지 체크
            if (position == 0) return TYPE_SECTION_HEADER;
            if (position < size) return type + adapter.getItemViewType(position - 1);

            // position이 섹션의 크기를 넘어간다면 다음 섹션으로 점프
            position -= size;
            type += adapter.getViewTypeCount();
        }
        return -1;
    }

    public boolean areAllItemsSectable() {
        return false;
    }

    public boolean isEnabled(int position) {
        return (getItemViewType(position) != TYPE_SECTION_HEADER);
    }
}