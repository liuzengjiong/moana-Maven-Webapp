package org.moana.services;

import java.util.List;
import java.util.Map;

import org.moana.bean.Ticket;


public interface TicketService {
    List<Ticket> getList(int page, int rows);
    
    int countTotal();
    
    List<Map<String,String>> getRemainNum(List<String> ids);
    
    Ticket getTicketById(String id);
}
