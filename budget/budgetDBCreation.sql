DROP DATABASE IF EXISTS financesTest;
CREATE DATABASE financesTest;
USE financesTest;

CREATE TABLE budgets(
	category VARCHAR(20) PRIMARY KEY,
    budgeted Decimal(8,2) NOT NULL,
    spent DECIMAL (8,2),
    variation DECIMAL (8,2)
);

CREATE TABLE transactions(
	id int PRIMARY KEY AUTO_INCREMENT,
    location VARCHAR(45),
    amount DECIMAL(8,2) NOT NULL,
    dateOccured DATE,
    category VARCHAR(20) NOT NULL,
    FOREIGN KEY (category) REFERENCES budgets(category)
);

CREATE TABLE income(
	id int PRIMARY KEY AUTO_INCREMENT,
    incomeSource VARCHAR(45),
    amount DECIMAL(8,2) NOT NULL,
    dateOccured DATE
);
