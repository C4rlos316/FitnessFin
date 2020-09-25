package com.appfitnessapp.app.fitnessapp.Usuario.Chat;

import android.content.Context;

import com.appfitnessapp.app.fitnessapp.Arrays.Chat;
import com.appfitnessapp.app.fitnessapp.Arrays.Chats;
import com.appfitnessapp.app.fitnessapp.Usuario.Chat.ChatContractUsuario;
import com.appfitnessapp.app.fitnessapp.Usuario.Chat.ChatInteractorUsuario;

public class ChatPresenterUsuario implements ChatContractUsuario.Presenter, ChatContractUsuario.OnSendMessageListener,
        ChatContractUsuario.OnGetMessagesListener {

    private ChatContractUsuario.View mView;
    private ChatInteractorUsuario mChatInteractor;

    public ChatPresenterUsuario(ChatContractUsuario.View view) {
        this.mView = view;
        mChatInteractor = new ChatInteractorUsuario(this, this);
    }

    @Override
    public void sendMessage(Context context, Chats chat, String receiverFirebaseToken, String id_usuario, String id_admin) {
        mChatInteractor.sendMessageToFirebaseUser(context, chat, receiverFirebaseToken, id_usuario,id_admin);
    }

    @Override
    public void getMessage(String uid_vendedor, String uid_usuario) {
        mChatInteractor.getMessageFromFirebaseUser(uid_vendedor, uid_usuario);
    }

    @Override
    public void onSendMessageSuccess() {
        mView.onSendMessageSuccess();
    }

    @Override
    public void onSendMessageFailure(String message) {
        mView.onSendMessageFailure(message);
    }

    @Override
    public void onGetMessagesSuccess(Chat chat) {
        mView.onGetMessagesSuccess(chat);
    }

    @Override
    public void onGetMessagesFailure(String message) {
//        mView.onGetMessagesFailure(message);
    }
}
