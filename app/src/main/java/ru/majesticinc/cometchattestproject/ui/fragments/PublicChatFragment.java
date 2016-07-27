package ru.majesticinc.cometchattestproject.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sendbird.android.MessageListQuery;
import com.sendbird.android.SendBird;
import com.sendbird.android.SendBirdEventHandler;
import com.sendbird.android.model.BroadcastMessage;
import com.sendbird.android.model.Channel;
import com.sendbird.android.model.FileLink;
import com.sendbird.android.model.Message;
import com.sendbird.android.model.MessageModel;
import com.sendbird.android.model.MessagingChannel;
import com.sendbird.android.model.ReadStatus;
import com.sendbird.android.model.SystemMessage;
import com.sendbird.android.model.TypeStatus;

import java.util.List;

import ru.majesticinc.cometchattestproject.R;
import ru.majesticinc.cometchattestproject.utils.Settings;

public class PublicChatFragment extends Fragment implements SendBirdEventHandler, View.OnClickListener {

    private EditText messageEdt;
    private TextView chatTxt;

    public PublicChatFragment() {
        // Required empty public constructor
    }

    public static PublicChatFragment newInstance() {
        PublicChatFragment fragment = new PublicChatFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SendBird.join(Settings.SEND_BIRD_PUBLIC_CHANEL_ID);
        SendBird.setEventHandler(PublicChatFragment.this);
        SendBird.queryMessageList(SendBird.getChannelUrl()).prev(Long.MAX_VALUE, 50, new MessageListQuery.MessageListQueryResult() {
            @Override
            public void onResult(List<MessageModel> messageModels) {
                long maxTimestamp = 0;

                for(MessageModel model : messageModels) {
                    if(model instanceof Message) {
                        Message message = (Message) model;

                        chatTxt.append(message.getSenderName() + " (" + message.getTimestamp() + ")" + ": " + message.getMessage() + "\r\n");
                    }

                    if(maxTimestamp < model.getTimestamp()) {
                        maxTimestamp = model.getTimestamp();
                    }
                }

                SendBird.connect(maxTimestamp);
            }

            @Override
            public void onError(Exception e) {
                Log.e("ERROR", e.toString());
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SendBird.disconnect();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_public_chat, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        messageEdt = (EditText) view.findViewById(R.id.public_chat_edt_message);
        chatTxt = (TextView) view.findViewById(R.id.public_chat_fragment_txt_chat_log);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onConnect(Channel channel) {
        Toast.makeText(getActivity(), "Подключились к каналу", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(int i) {
        Toast.makeText(getActivity(), "Ошибка: " + i, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onChannelLeft(Channel channel) {

    }

    @Override
    public void onMessageReceived(Message message) {
        chatTxt.append(message.getSenderName() + " (" + message.getTimestamp() + ")" + ": " + message.getMessage() + "\r\n");
    }

    @Override
    public void onMutedMessageReceived(Message message) {

    }

    @Override
    public void onSystemMessageReceived(SystemMessage systemMessage) {

    }

    @Override
    public void onBroadcastMessageReceived(BroadcastMessage broadcastMessage) {

    }

    @Override
    public void onFileReceived(FileLink fileLink) {

    }

    @Override
    public void onMutedFileReceived(FileLink fileLink) {

    }

    @Override
    public void onReadReceived(ReadStatus readStatus) {

    }

    @Override
    public void onTypeStartReceived(TypeStatus typeStatus) {

    }

    @Override
    public void onTypeEndReceived(TypeStatus typeStatus) {

    }

    @Override
    public void onAllDataReceived(SendBird.SendBirdDataType sendBirdDataType, int i) {

    }

    @Override
    public void onMessageDelivery(boolean sent, String message, String data, String id) {
        if(!sent) {
            messageEdt.setText(message);
        }
    }

    @Override
    public void onMessagingStarted(MessagingChannel messagingChannel) {

    }

    @Override
    public void onMessagingUpdated(MessagingChannel messagingChannel) {

    }

    @Override
    public void onMessagingEnded(MessagingChannel messagingChannel) {

    }

    @Override
    public void onAllMessagingEnded() {

    }

    @Override
    public void onMessagingHidden(MessagingChannel messagingChannel) {

    }

    @Override
    public void onAllMessagingHidden() {

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.fab:
                String message = messageEdt.getText().toString();

                if(!message.isEmpty()) {
                    messageEdt.setText("");
                    SendBird.send(message);
                }
                break;
        }
    }
}
