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

import java.util.stream.Stream;

import javaStudy_chapter10.Order;
import javaStudy_chapter10.Stock;
import javaStudy_chapter10.Trade;

public class NestedFunctionOrderBuilder {

  public static Order order(String customer, Trade... trades) {
    Order order = new Order(); // 해당 고객의 주무 만들기
    order.setCustomer(customer);
    Stream.of(trades).forEach(order::addTrade); // 주문에 모든 거래 추가
    return order;
  }

  public static Trade buy(int quantity, Stock stock, double price) {
	  //주식 매수 거래 만들기
    return buildTrade(quantity, stock, price, Trade.Type.BUY);
  }

  public static Trade sell(int quantity, Stock stock, double price) {
	  // 주식 매도 거래 만들기
	  return buildTrade(quantity, stock, price, Trade.Type.SELL);
  }

  private static Trade buildTrade(int quantity, Stock stock, double price, Trade.Type buy) {
    Trade trade = new Trade();
    trade.setQuantity(quantity);
    trade.setType(buy);
    trade.setStock(stock);
    trade.setPrice(price);
    return trade;
  }

  public static double at(double price) {
	  // 거래된 주식의 단가를 정의하는 더미 메서드
    return price;
  }

  public static Stock stock(String symbol, String market) {
	  // 거래된 주식 만들기
    Stock stock = new Stock();
    stock.setSymbol(symbol);
    stock.setMarket(market);
    return stock;
  }

  public static String on(String market) {
	 // 주식이 거래된 시장을 정의하는 더미 메서드 정의
    return market;
  }

}
