/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bombapack.chat.dao;

import com.bombapack.chat.model.Message;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author WillNunnes
 */
public class MessageDAOImpl implements MessageDAO {
    
    private EntityManager entityManager;
    
    private Connection connection;
    
    public MessageDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Message message) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO message (contentText) VALUES (?)");
            preparedStatement.setString(1, message.getContentText());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
        }
        
    }

    @Override
    public Message get(Long id) {
        return entityManager.find(Message.class, id);
    }

    @Override
    public void remove(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM message WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    @Override
    public void update(Message message) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE message SET contentText = ? WHERE id = ?");
            preparedStatement.setString(1, message.getContentText());
            preparedStatement.setLong(2, message.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    @Override
    public List<Message> getAll() {
        List<Message> messages = new ArrayList<Message>();
        try {
            Statement stmt = null;
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM message;");
            while (rs.next()) {
                Message message = new Message();
                message.setId(rs.getLong("id"));
                message.setContentText(rs.getString("contentText"));
                message.setEditing(false);
                messages.add(message);
            }
        } catch (SQLException ex) {
        }
        return messages;
    }
    
}
