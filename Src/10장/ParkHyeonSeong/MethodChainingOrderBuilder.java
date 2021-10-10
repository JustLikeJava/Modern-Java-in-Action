/*
 * Copyright 2005 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package javaStudy_chapter10;

import javaStudy_chapter10.Order;
import javaStudy_chapter10.Stock;
import javaStudy_chapter10.Trade;

public class MethodChainingOrderBuilder {

  public final Order order = new Order(); // 빌더로 감싼 주문

  private MethodChainingOrderBuilder(String customer) { 
    order.setCustomer(customer);
  }

  public static MethodChainingOrderBuilder forCustomer(String customer) {
    return new MethodChainingOrderBuilder(customer); // 고객의 주문을 만드는 정적 팩토리 메서드
  }

  public Order end() {
    return order; // 주문 만들기를 종료하고 반환
  }

  public TradeBuilder buy(int quantity) {
	  // 주식을 사는 TradeBuilder 만들기
    return new TradeBuilder(this, Trade.Type.BUY, quantity);
  }

  public TradeBuilder sell(int quantity) {
	// 주식을 파는 TradeBuilder 만들기
    return new TradeBuilder(this, Trade.Type.SELL, quantity);
  }

  private MethodChainingOrderBuilder addTrade(Trade trade) {
    order.addTrade(trade); // 주문에 주식 추가
    return this; // 유연하게 추가 주문을 만들어 추가할 수 있도록 주문 빌더 자체를 반환
  }

  
  // 빌더를 계속 이어가기 위해 Stock 클래스의 인스턴스를 만드는 TradeBuilder의 공개 메서드를 이용
  public static class TradeBuilder {

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

  
  //한 개의 공개 메서드 TradeBuilderWithStock은 거래되는 주식의 단위 가격을 설정한 다음 원래 주문 빌더를 반환.
  public static class TradeBuilderWithStock {

    private final MethodChainingOrderBuilder builder;
    private final Trade trade;

    public TradeBuilderWithStock(MethodChainingOrderBuilder builder, Trade trade) {
      this.builder = builder;
      this.trade = trade;
    }

    public MethodChainingOrderBuilder at(double price) {
      trade.setPrice(price);
      return builder.addTrade(trade);
    }

  }

  
  // StockBuilder는 주식의 시장을 지정하고, 거래에 주식을 추가하고, 최종 빌더를 반환하는 on() 메서드 한 개를 정의
  public static class StockBuilder {

    private final MethodChainingOrderBuilder builder;
    private final Trade trade;
    private final Stock stock = new Stock();

    private StockBuilder(MethodChainingOrderBuilder builder, Trade trade, String symbol) {
      this.builder = builder;
      this.trade = trade;
      stock.setSymbol(symbol);
    }

    public TradeBuilderWithStock on(String market) {
      stock.setMarket(market);
      trade.setStock(stock);
      return new TradeBuilderWithStock(builder, trade);
    }

  }

}
