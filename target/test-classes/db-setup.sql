
CREATE USER portfolio WITH PASSWORD 'portfolio99';

GRANT ALL PRIVILEGES ON DATABASE postgres to portfolio;
GRANT ALL PRIVILEGES ON SCHEMA public to portfolio;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO portfolio;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO portfolio;

SET session_replication_role = 'replica';

DROP TABLE portfolio;
DROP TABLE exchange;
DROP TABLE equity_asset;
DROP TABLE option_asset;
DROP TABLE bond_asset;
DROP TABLE future_asset;
DROP TABLE daily_asset_allocation;
DROP TABLE current_asset_allocation;
DROP TABLE daily_equity;
DROP TABLE daily_option;
DROP TABLE daily_future;
DROP TABLE daily_bond;
DROP TABLE current_equity;
DROP TABLE current_option;
DROP TABLE current_future;
DROP TABLE current_bond;


CREATE TABLE portfolio
(
  id        SERIAL PRIMARY KEY,
  name      VARCHAR(55),
  description VARCHAR(256)
);

CREATE INDEX portfolio_name_idx ON portfolio (name);


CREATE TABLE exchange
(
  id        SERIAL PRIMARY KEY,
  identifier  VARCHAR(35)
);

CREATE TABLE equity_asset
(
  id        SERIAL PRIMARY KEY,
  name      VARCHAR(75),
  ticker VARCHAR(8) NOT NULL,
  allocation_percentage    DECIMAL(4, 2),
  exchange_id BIGINT NOT NULL,
  FOREIGN KEY (exchange_id) REFERENCES exchange (id)
);

CREATE TABLE option_asset
(
  id        SERIAL PRIMARY KEY,
  name      VARCHAR(55),
  contract_name  VARCHAR(35),
  underlying_symbol VARCHAR(8) NOT NULL,
  expiration_date DATE NOT NULL,
  option_type   VARCHAR(4) NOT NULL,
  strike_price  DECIMAL(6,2) NOT NULL,
  allocation_percentage    DECIMAL(4, 2),
  exchange_id BIGINT NOT NULL,
  FOREIGN KEY (exchange_id) REFERENCES exchange (id)
);

CREATE TABLE bond_asset
(
  id        SERIAL PRIMARY KEY,
  cusip      VARCHAR(15),
  description VARCHAR(256),
  bond_type   VARCHAR(25),
  coupon      DECIMAL(6,2),
  maturity_date  DATE,
  rating      VARCHAR(15),
  allocation_percentage    DECIMAL(4, 2),
  exchange_id BIGINT NOT NULL,
  FOREIGN KEY (exchange_id) REFERENCES exchange (id)
);

CREATE TABLE future_asset
(
  id        SERIAL PRIMARY KEY,
  category      VARCHAR(15),
  product VARCHAR(55),
  symbol VARCHAR(6),
  expiration_date  DATE,
  allocation_percentage    DECIMAL(4, 2),
  exchange_id BIGINT NOT NULL,
  FOREIGN KEY (exchange_id) REFERENCES exchange (id)
);


-- The combination of date and portfolio_id must be unique

CREATE TABLE daily_asset_allocation
(
  id            SERIAL PRIMARY KEY,
  date          DATE,
  portfolio_id  BIGINT NOT NULL,
  FOREIGN KEY (portfolio_id) REFERENCES portfolio (id)
);

CREATE INDEX daily_asset_allocation_idx ON daily_asset_allocation (date, portfolio_id);

CREATE TABLE current_asset_allocation
(
  id            SERIAL PRIMARY KEY,
  portfolio_id  BIGINT NOT NULL,
  FOREIGN KEY (portfolio_id) REFERENCES portfolio (id)
);


-- Join Tables for daily_asset_allocation


-- Join table for daily_asset_allocation and equity
CREATE TABLE daily_equity
(
  daily_asset_allocation_id BIGINT,
  equity_id         BIGINT,
  PRIMARY KEY (daily_asset_allocation_id, equity_id)
);

-- Join table for daily_asset_allocation and option
CREATE TABLE daily_option
(
  daily_asset_allocation_id BIGINT,
  option_id         BIGINT,
  PRIMARY KEY (daily_asset_allocation_id, option_id)
);

-- Join table for daily_asset_allocation and future
CREATE TABLE daily_future
(
  daily_asset_allocation_id BIGINT,
  future_id         BIGINT,
  PRIMARY KEY (daily_asset_allocation_id, future_id)
);

-- Join table for daily_asset_allocation and bond
CREATE TABLE daily_bond
(
  daily_asset_allocation_id BIGINT,
  bond_id         BIGINT,
  PRIMARY KEY (daily_asset_allocation_id, bond_id)
);


-- Join Tables for current_asset_allocation


-- Join table for current_asset_allocation and equity
CREATE TABLE current_equity
(
  current_asset_allocation_id BIGINT,
  equity_id         BIGINT,
  PRIMARY KEY (current_asset_allocation_id, equity_id)
);

-- Join table for current_asset_allocation and option
CREATE TABLE current_option
(
  current_asset_allocation_id BIGINT,
  option_id         BIGINT,
  PRIMARY KEY (current_asset_allocation_id, option_id)
);

-- Join table for current_asset_allocation and future
CREATE TABLE current_future
(
    current_asset_allocation_id BIGINT,
    future_id                   BIGINT,
    PRIMARY KEY (current_asset_allocation_id, future_id)
);



-- Join table for current_asset_allocation and bond
CREATE TABLE current_bond
(
  current_asset_allocation_id BIGINT,
  bond_id         BIGINT,
  PRIMARY KEY (current_asset_allocation_id, bond_id)
);
commit;
SET session_replication_role = 'origin';
