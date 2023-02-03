CREATE DATABASE ComputerShop;
USE ComputerShop;

CREATE TABLE Customer(
                         CusID VARCHAR(10) NOT NULL,
                         Name VARCHAR(30),
                         Address VARCHAR(30) NOT NULL,
                         Contact INT,
                         CONSTRAINT PRIMARY KEY (CusID)
);
DESC Customer;

CREATE TABLE Employ(
                       EMID VARCHAR(6) NOT NULL,
                       Name VARCHAR(30)NOT NULL,
                       Address VARCHAR(30)NOT NULL,
                       Contact INT NOT NULL,
                       CONSTRAINT PRIMARY KEY (EMID)
);
DESC Employ;

CREATE TABLE Supplier(
                         SupID VARCHAR(10) NOT NULL,
                         Name VARCHAR(25),
                         Address VARCHAR(30) NOT NULL,
                         Brand VARCHAR(30),
                         Unit_Price DECIMAL(10,2) ,
                         QTY INT,
                         CONSTRAINT PRIMARY KEY (SupID)
);

DESC Supplier;

CREATE TABLE Income(
                       InID VARCHAR(10) NOT NULL,
                       SerID VARCHAR(10) NOT NULL,
                       ServiceBil VARCHAR(35),
                       OrderBil VARCHAR(30) NOT NULL,
                       Descripion VARCHAR(30),
                       CONSTRAINT PRIMARY KEY (InID),
                       CONSTRAINT FOREIGN KEY(SerID) REFERENCES Service(SerID)
                           ON UPDATE CASCADE ON DELETE CASCADE
);

DESC Income;



CREATE TABLE Orders(
                       OrderID VARCHAR(6) NOT NULL,
                       CusID VARCHAR(24) NOT NULL,
                       Descripion VARCHAR(30),
                       Date DATE,
                       CONSTRAINT PRIMARY KEY (OrderID),
                       CONSTRAINT FOREIGN KEY(CusID) REFERENCES Customer(CusID)
                           ON UPDATE CASCADE ON DELETE CASCADE
);

DESC Orders;

CREATE TABLE Product(
                        PrdID VARCHAR(6) NOT NULL,
                        Name VARCHAR(24),
                        UnitPrice DECIMAL(8,2),
                        Descripion VARCHAR (32),
                        QTY  INT (10) NOT NULL,
                        CONSTRAINT PRIMARY KEY (PrdID)
);
DESC Product;

CREATE TABLE Service(
                        SerID VARCHAR(10) NOT NULL,
                        EMID VARCHAR(10) NOT NULL,
                        Descripion VARCHAR(50) ,
                        Price DECIMAL(10,2),
                        CONSTRAINT PRIMARY KEY (SerID),
                        CONSTRAINT FOREIGN KEY(EMID) REFERENCES Employ(EMID)
                            ON UPDATE CASCADE ON DELETE CASCADE
);

DESC Service;

CREATE TABLE SupplierDetail(
                               SupOrderID VARCHAR(6) NOT NULL,
                               PrdID VARCHAR(6)  NOT NULL,
                               QTY INT(15) NOT NULL ,
                               Descripion VARCHAR(30),
                               UnitPrice DECIMAL(6,2) NOT NULL,

                               CONSTRAINT PRIMARY KEY (PrdID,SupOrderID),
                               CONSTRAINT FOREIGN KEY (PrdID) REFERENCES Product(PrdID)
                                   ON UPDATE CASCADE ON DELETE CASCADE,
                               CONSTRAINT FOREIGN KEY (SupOrderID) REFERENCES SupOrders(SupOrderID)
                                   ON UPDATE CASCADE ON DELETE CASCADE
);
DESC SupplierDetail

CREATE TABLE OrderDetail(
                            OrderID VARCHAR(6) NOT NULL,
                            PrdID VARCHAR(6) NOT NULL,
                            QTY INT(15) NOT NULL,
                            Price DECIMAL(6,2) NOT NULL,
                            CONSTRAINT PRIMARY KEY (OrderID,prdID),
                            CONSTRAINT FOREIGN KEY (OrderID) REFERENCES Orders(OrderID)
                                ON UPDATE CASCADE ON DELETE CASCADE,
                            CONSTRAINT FOREIGN KEY (PrdID) REFERENCES product(PrdID)
                                ON UPDATE CASCADE ON DELETE CASCADE
);

DESC OrderDetail;

CREATE TABLE SupOrders(
                          SupOrderID VARCHAR(6) NOT NULL,
                          SupID VARCHAR(24) NOT NULL,
                          Date DATE,
                          CONSTRAINT PRIMARY KEY (SupOrderID),
                          CONSTRAINT FOREIGN KEY(SupID) REFERENCES Supplier(SupID)
                              ON UPDATE CASCADE ON DELETE CASCADE
);

DESC SupOrders;


INSERT INTO SupplierDetail VALUES('SOP001','P001','ARES',3233);