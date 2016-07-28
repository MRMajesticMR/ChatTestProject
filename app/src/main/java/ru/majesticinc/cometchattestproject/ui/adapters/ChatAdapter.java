package ru.majesticinc.cometchattestproject.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sendbird.android.model.Message;
import com.sendbird.android.model.MessageModel;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import ru.majesticinc.cometchattestproject.R;

/**
 * Created by Majestic on 28.07.2016.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView userNameTxt;
        public TextView messageTxt;
        private TextView dateTxt;

        public ViewHolder(View itemView) {
            super(itemView);

            userNameTxt = (TextView) itemView.findViewById(R.id.char_list_row_txt_user_name);
            messageTxt = (TextView) itemView.findViewById(R.id.char_list_row_txt_message);
            dateTxt = (TextView) itemView.findViewById(R.id.char_list_row_txt_date);

        }
    }

    public Comparator<MessageModel> messagesComparator = new Comparator<MessageModel>() {

        @Override
        public int compare(MessageModel lhs, MessageModel rhs) {
            if(lhs.getTimestamp() > rhs.getTimestamp()) {
                return 1;

            } else if (lhs.getTimestamp() < rhs.getTimestamp()) {
                return -1;

            } else {
                return 0;

            }
        }
    };

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault());

    private List<MessageModel> messages = new LinkedList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_chat, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        MessageModel messageModel = messages.get(position);

        if(messageModel instanceof Message) {
            Message message = (Message) messageModel;

            holder.userNameTxt.setText(String.format("%s:", message.getSenderName()));
            holder.messageTxt.setText(message.getMessage());
            holder.dateTxt.setText(DATE_FORMAT.format(new Date(message.getTimestamp())));
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void addMessageModel(MessageModel messageModel) {
        messages.add(messageModel);
        Collections.sort(messages, messagesComparator);
        notifyDataSetChanged();
    }

    public void clearData() {
        messages.clear();
        notifyDataSetChanged();
    }

    public long getLatestMessageTimestamp() {
        long maxTimestamp = 0;

        for(MessageModel model : messages) {
            if(maxTimestamp < model.getTimestamp()) {
                maxTimestamp = model.getTimestamp();
            }
        }

        return maxTimestamp;
    }

}
