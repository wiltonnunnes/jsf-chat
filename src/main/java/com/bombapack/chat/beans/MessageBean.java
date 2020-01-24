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
import javax.faces.application.Application;
import javax.inject.Named;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.context.PartialViewContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author WillNunnes
 */
@ManagedBean(name = "messageBean")
@SessionScoped
public class MessageBean {
    
    private MessageDAO messageDAO;
    
    private Connection connection;
    
    private Message message;
    
    private String contentText;
    
    private DataModel<Message> model;
    
    private boolean editing;
    
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
    
    public DataModel getMessages() {
        model = new ListDataModel(messageDAO.getAll());
        return model;
    }
    
    public void removeMessage() {
        Message message = model.getRowData();
        messageDAO.remove(message.getId());
    }
    
    public String updateMessage() {
        messageDAO.update(message);
        return "index";
    }
    
    public String editMessage() {
        message = model.getRowData();
        return "editar";
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

    public boolean isEditing() {
        return editing;
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
    }

    public void test() {
        Message message = new Message();
        message.setId(1L);
        message.setContentText("Testando");
        this.message = message;
    }
}
