/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bombapack.chat.beans;

import com.bombapack.chat.dao.ConnectionFactory;
import com.bombapack.chat.dao.MessageDAO;
import com.bombapack.chat.dao.MessageDAOImpl;
import com.bombapack.chat.model.Message;
import java.sql.Connection;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.inject.Named;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author WillNunnes
 */
@ManagedBean(name = "messageBean")
@ApplicationScoped
public class MessageBean {
    
    private MessageDAO messageDAO;
    
    private List<Message> messages;
    
    private Connection connection;
    
    private Message message;
    
    private String contentText;
    
    public MessageBean() {
        connection = ConnectionFactory.createConnection();
        messageDAO = new MessageDAOImpl(connection);
    }
    
    public void addMessage() {
        Message message = new Message();
        message.setContentText(contentText);
        contentText = "";
        messageDAO.add(message);
    }
    
    public List<Message> getMessages() {
        messages = messageDAO.getAll();
        Collections.reverse(messages);
        return messages;
    }
    
    public void removeMessage(Message message) {
        messageDAO.remove(message.getId());
    }
    
    public void updateMessage(Message message) {
        messageDAO.update(message);
    }
    
    public void editMessage(javax.faces.event.AjaxBehaviorEvent e) {
        UIComponent editButton = e.getComponent();
        //message.setEditing(true);
        FacesContext context = FacesContext.getCurrentInstance();
        Collection<String> renderIds = context.getPartialViewContext().getRenderIds();
        
        String id = editButton.getId().replace(":form1", "").replace("editButton", "form");
        UIComponent form = editButton.findComponent(id);
        /*
        UIComponent deleteButton = editButton.findComponent("button");
        
        renderIds.remove(editButton.getId());
        renderIds.remove(deleteButton.getId());
        */
        
        renderIds.add(form.getId());
    }
    
    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }
    
}
