
-- Exchanges
INSERT INTO exchange(identifier) values ('NASDAQ');
INSERT INTO exchange(identifier) values ('NYSE');
INSERT INTO exchange(identifier) values ('AMEX');
INSERT INTO exchange(identifier) values ('CBOE');
INSERT INTO exchange(identifier) values ('CME');
INSERT INTO exchange(identifier) values ('ICE');
INSERT INTO exchange(identifier) values ('OTC');

-- Equities
INSERT INTO equity_asset(name, ticker, exchange_id, allocation_percentage)
 values ('Apple Inc.', 'AAPL', 1, 12.25);
INSERT INTO equity_asset(name, ticker, exchange_id, allocation_percentage)
 values ('ebay Inc.', 'EBAY', 1, 25.00);
INSERT INTO equity_asset(name, ticker, exchange_id, allocation_percentage)
 values ('China Eastern Airlines Corp. Ltd.', 'CEA', 2, 4.00);
INSERT INTO equity_asset(name, ticker, exchange_id, allocation_percentage)
 values ('LRR Energy', 'LRE', 2, 8.25);

-- Options
INSERT INTO option_asset(name, contract_name, underlying_symbol, expiration_date, option_type, strike_price, exchange_id, allocation_percentage)
 values ('Apple Inc.', 'AAPL150529C00122000', 'AAPL', '2015-5-29', 'CALL', 122.00, 4, 3.00);
INSERT INTO option_asset(name, contract_name, underlying_symbol, expiration_date, option_type, strike_price, exchange_id, allocation_percentage)
 values ('Apple Inc.', 'AAPL150529P00118000', 'AAPL', '2015-5-29', 'PUT', 118.00, 4, 5.75);
INSERT INTO option_asset(name, contract_name, underlying_symbol, expiration_date, option_type, strike_price, exchange_id, allocation_percentage)
 values ('International Business Machines Corp.', 'IBM150501P00149000', 'IBM', '2015-5-1', 'PUT', 149.00, 4, 9.25);

-- Bonds
INSERT INTO bond_asset(cusip, description, bond_type, coupon, maturity_date, rating, exchange_id, allocation_percentage)
 values('369604BC6', 'General Electric Co Nt 5.25%17', 'CORPORATE', 5.25, '2015-12-06', 'A1/AA+/-', 7, 4.00);
INSERT INTO bond_asset(cusip, description, bond_type, coupon, maturity_date, rating, exchange_id, allocation_percentage)
 values('38141E2B4', 'Goldman Sachs Group, Inc.', 'CORPORATE', 5.5, '2037-05-15', 'Baa1/A-/A', 7, 6.25);
INSERT INTO bond_asset(cusip, description, bond_type, coupon, maturity_date, rating, exchange_id, allocation_percentage)
 values('001957AW9', 'At&T Corp Nt 6%09', 'CORPORATE', 6.5, '2029-03-15', 'WR/BBB+/A-', 7, 8.25);

-- Futures
INSERT INTO future_asset(category, product, symbol, expiration_date, exchange_id, allocation_percentage)
 values('Currencies', 'Australian Dollar Futures', '6A', '2017-03-17', 5, 2.00);
INSERT INTO future_asset(category, product, symbol, expiration_date, exchange_id, allocation_percentage)
 values('Energy', 'Brent Crude', 'B', '2015-03-21', 6, 9.00);

-- Portfolio
INSERT INTO portfolio (name, description) values ('Portfolio 1', 'A description of Portfolio 1');

-- Portfolio current allocations
INSERT INTO current_asset_allocation(id, portfolio_id) values(1, 1);

INSERT INTO current_equity(current_asset_allocation_id, equity_id) values(1, 1);
INSERT INTO current_equity(current_asset_allocation_id, equity_id) values(1, 2);
INSERT INTO current_equity(current_asset_allocation_id, equity_id) values(1, 3);

INSERT INTO current_bond(current_asset_allocation_id, bond_id) values(1, 1);
INSERT INTO current_bond(current_asset_allocation_id, bond_id) values(1, 3);

INSERT INTO current_option(current_asset_allocation_id, option_id) values(1, 1);
INSERT INTO current_option(current_asset_allocation_id, option_id) values(1, 2);

INSERT INTO current_future(current_asset_allocation_id, future_id) values(1, 1);
INSERT INTO current_future(current_asset_allocation_id, future_id) values(1, 2);

-- Portfolio daily allocations
INSERT INTO daily_asset_allocation(id, date, portfolio_id) values(1, '2015-4-15', 1);
INSERT INTO daily_equity(daily_asset_allocation_id, equity_id) values(1, 1);
INSERT INTO daily_bond(daily_asset_allocation_id, bond_id) values(1, 1);

INSERT INTO daily_asset_allocation(id, date, portfolio_id) values(2, '2015-4-16', 1);
INSERT INTO daily_equity(daily_asset_allocation_id, equity_id) values(2, 1);
INSERT INTO daily_equity(daily_asset_allocation_id, equity_id) values(2, 2);
INSERT INTO daily_bond(daily_asset_allocation_id, bond_id) values(2, 1);
INSERT INTO daily_option(daily_asset_allocation_id, option_id) values(2, 1);

INSERT INTO daily_asset_allocation(id, date, portfolio_id) values(3, '2015-4-17', 1);
INSERT INTO daily_equity(daily_asset_allocation_id, equity_id) values(3, 1);
INSERT INTO daily_equity(daily_asset_allocation_id, equity_id) values(3, 2);

INSERT INTO daily_asset_allocation(id, date, portfolio_id) values(4, '2015-4-18', 1);
INSERT INTO daily_equity(daily_asset_allocation_id, equity_id) values(4, 1);
INSERT INTO daily_equity(daily_asset_allocation_id, equity_id) values(4, 2);

INSERT INTO daily_asset_allocation(id, date, portfolio_id) values(5, '2015-4-19', 1);
INSERT INTO daily_equity(daily_asset_allocation_id, equity_id) values(5, 1);
INSERT INTO daily_equity(daily_asset_allocation_id, equity_id) values(5, 2);
INSERT INTO daily_equity(daily_asset_allocation_id, equity_id) values(5, 3);
INSERT INTO daily_equity(daily_asset_allocation_id, equity_id) values(5, 4);
INSERT INTO daily_bond(daily_asset_allocation_id, bond_id) values(5, 1);
INSERT INTO daily_bond(daily_asset_allocation_id, bond_id) values(5, 3);
INSERT INTO daily_option(daily_asset_allocation_id, option_id) values(5, 1);
INSERT INTO daily_option(daily_asset_allocation_id, option_id) values(5, 2);
INSERT INTO daily_future(daily_asset_allocation_id, future_id) values(5, 1);
INSERT INTO daily_future(daily_asset_allocation_id, future_id) values(5, 2);

