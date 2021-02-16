package com.stock.sales.dao;

import com.stock.IDao;

import java.util.List;

public interface ICommandLineDao extends IDao<CommandLine> {
    List<CommandLine> getAll(long deliverySheetId);
}
