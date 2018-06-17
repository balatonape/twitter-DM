package com.tdm.mbtwitterdm.ui.adapters;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdm.mbtwitterdm.data.Event;
import com.tdm.mbtwitterdm.databinding.MessageItemViewLeftBinding;
import com.tdm.mbtwitterdm.ui.viewmodel.MessageItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.BindingHolder> {

    private List<Event> eventList;
    private View emptyView;
    private String avatarUrl;

    public MessagesAdapter(List<Event> eventList, View emptyView, String avatarUrl) {
        this.eventList = new ArrayList<>(eventList);
        this.emptyView = emptyView;
        this.avatarUrl = avatarUrl;
        this.emptyView.setVisibility(eventList.size() == 0 ? View.VISIBLE : View.GONE);
    }

    @NonNull
    @Override
    public BindingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MessageItemViewLeftBinding binding = MessageItemViewLeftBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BindingHolder(binding);
    }

    @Override
    public void onBindViewHolder(MessagesAdapter.BindingHolder holder, final int position) {
        Event event = eventList.get(position);
        ((MessageItemViewLeftBinding) holder.getBinding()).setMsg(new MessageItemViewModel(event, avatarUrl));
    }

    @Override
    public int getItemCount() {
        return eventList != null ? eventList.size() : 0;
    }

    public void updateEventList(Event event) {
        eventList.add(event);
        notifyDataSetChanged();
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private MessageItemViewLeftBinding binding;

        BindingHolder(MessageItemViewLeftBinding rowView) {
            super(rowView.getRoot());
            binding = rowView;
        }

        ViewDataBinding getBinding() {
            return binding;
        }
    }
}
