package org.moana.services.impl;


import java.util.List;
import java.util.Map;

import org.moana.bean.Ticket;
import org.moana.dao.TicketMapper;
import org.moana.services.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TicketServiceImpl implements TicketService {
    private static final Logger LOG = LoggerFactory.getLogger(TicketServiceImpl.class);
    @Autowired
    private TicketMapper ticketMapper;

    /**
     * 获取列表
     *
     */
    @Override
    public List<Ticket> getList(int page, int rows) {
        int index = (page-1)*rows;
        return ticketMapper.selectListByPages(index, rows);
    }

	@Override
	public int countTotal() {
		return ticketMapper.countTotal();
	}

	@Override
	public List<Map<String, String>> getRemainNum(List<String> ids) {
		
		return ticketMapper.selectByIds(ids);
	}

	@Override
	public Ticket getTicketById(String id) {
		// TODO Auto-generated method stub
		return ticketMapper.selectByPrimaryKey(id);
	}
    
}
