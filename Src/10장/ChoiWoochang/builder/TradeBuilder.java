package com.company.builder;

import com.company.market.Trade;

public class TradeBuilder {

    private final MethodChainingOrderBuilder builder;
    public final Trade trade = new Trade();


    private TradeBuilder(MethodChainingOrderBuilder builder, Trade.Type type, int quantity) {
        this.builder = builder;
        trade.setType(type);
        trade.setQuantity(quantity);
    }

    public StockBuilder stock(String symbol) {
        return new StockBuilder(builder, trade, symbol);
    }
}
