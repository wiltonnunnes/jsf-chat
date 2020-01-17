/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bombapack.chat.dao;

import com.bombapack.chat.model.Message;
import java.util.List;

/**
 *
 * @author WillNunnes
 */
public interface MessageDAO {
    
    public void add(Message message);
    
    public Message get(Long id);
    
    public void remove(Long id);
    
    public void update(Message message);
    
    public List<Message> getAll();
}
