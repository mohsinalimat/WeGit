package com.quinn.githubknife.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quinn.githubknife.presenter.EventPresenterImpl;
import com.quinn.githubknife.ui.activity.EventAdapter;
import com.quinn.httpknife.github.Event;
import com.quinn.httpknife.github.GithubImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quinn on 7/16/15.
 */
public class ReceivedEventFragment extends BaseFragment {
    private EventAdapter adapter;

    public static ReceivedEventFragment getInstance(String user){
        ReceivedEventFragment receivedEventFragment = new ReceivedEventFragment();
        Bundle bundle = new Bundle();
        bundle.putString("user", user);
        receivedEventFragment.setArguments(bundle);
        return receivedEventFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new EventPresenterImpl(this.getActivity(),this);
        dataItems = new ArrayList<Event>();
        adapter = new EventAdapter(dataItems);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container,savedInstanceState);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void setItems(List<?> items) {
        super.setItems(items);
        for(Object repo:items){
            dataItems.add((Event) repo);
        }
        loading = false;
        if(items.size() < GithubImpl.DEFAULT_PAGE_SIZE)
            haveMore = false;
        adapter.notifyDataSetChanged();
    }
}