# Portfolios
INSERT INTO portfolio (id, name, description) values (1, "Portfolio 1", "A description of Portfolio 1");
INSERT INTO portfolio (id, name, description) values (2, "Portfolio 2", "A description of Portfolio 2");

# Exchanges
INSERT INTO exchange(id, identifier) values (1, "NASDAQ");
INSERT INTO exchange(id, identifier) values (2, "NYSE");
INSERT INTO exchange(id, identifier) values (3, "AMEX");
INSERT INTO exchange(id, identifier) values (4, "CBOE");
INSERT INTO exchange(id, identifier) values (5, "CME");
INSERT INTO exchange(id, identifier) values (6, "ICE");
INSERT INTO exchange(id, identifier) values (7, "OTC");

# Equities
INSERT INTO equity_asset(id, name, ticker, exchange_id)
 values (1, "Apple Inc.", "AAPL", 1);
INSERT INTO equity_asset(id, name, ticker, exchange_id)
 values (2, "ebay Inc.", "EBAY", 1);
INSERT INTO equity_asset(id, name, ticker, exchange_id)
 values (3, "China Eastern Airlines Corp. Ltd.", "CEA", 2);
INSERT INTO equity_asset(id, name, ticker, exchange_id)
 values (4, "LRR Energy", "LRE", 2);

# Options
INSERT INTO option_asset(id, name, contract_name, underlying_symbol, expiration_date, option_type, strike_price, exchange_id)
 values (1, "Apple Inc.", "AAPL150529C00122000", "AAPL", "2015-5-29", "CALL", 122.00, 4);
INSERT INTO option_asset(id, name, contract_name, underlying_symbol, expiration_date, option_type, strike_price, exchange_id)
 values (2, "Apple Inc.", "AAPL150529P00118000", "AAPL", "2015-5-29", "CALL", 118.00, 4);
INSERT INTO option_asset(id, name, contract_name, underlying_symbol, expiration_date, option_type, strike_price, exchange_id)
 values (3, "International Business Machines Corp.", "IBM150501P00149000", "IBM", "2015-5-1", "PUT", 149.00, 4);

# Bonds
INSERT INTO bond_asset(id, cusip, description, bond_type, coupon, maturity_date, rating, exchange_id)
 values(1, "369604BC6", "General Electric Co Nt 5.25%17", "CORPORATE", 5.25, "2015-12-06", "A1/AA+/-", 7);
INSERT INTO bond_asset(id, cusip, description, bond_type, coupon, maturity_date, rating, exchange_id)
 values(2, "38141E2B4", "Goldman Sachs Group, Inc.", "CORPORATE", 5.5, "2037-05-15", "Baa1/A-/A", 7);
INSERT INTO bond_asset(id, cusip, description, bond_type, coupon, maturity_date, rating, exchange_id)
 values(3, "001957AW9", "At&T Corp Nt 6%09", "CORPORATE", 6.5, "2029-03-15", "WR/BBB+/A-", 7);

# Futures
INSERT INTO future_asset(id, category, product, symbol, expiration_date, exchange_id)
 values(1, "Currencies", "Australian Dollar Futures", "6A", "2017-03-17", 5);
INSERT INTO future_asset(id, category, product, symbol, expiration_date, exchange_id)
 values(2, "Energy", "Brent Crude", "B", "2015-03-21", 6);

