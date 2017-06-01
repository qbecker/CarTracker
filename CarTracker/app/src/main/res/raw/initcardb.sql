DROP TABLE cars;
DROP TABLE repairs;
DROP TABLE trips;

CREATE TABLE cars(
 id TEXT PRIMARY KEY,
 name TEXT,
 description TEXT,
 make TEXT,
 model TEXT,
 totalMiles TEXT,
 totalCost TEXT,
 mpg TEXT
);

CREATE TABLE repairs(
   id TEXT,
   description TEXT,
   cost TEXT,
   date TEXT,
   uid TEXT PRIMARY KEY
);

CREATE TABLE trips(
    id TEXT,
    description TEXT,
    miles TEXT,
    cost TEXT,
    date TEXT,
    uid TEXT PRIMARY KEY
);


INSERT INTO cars VALUES('1','Blue Honda','Quintens Car','Honda','CR-V','250,000','0.0','0.0');
INSERT INTO repairs VALUES('1', 'Brakes', '120', '12/12/2001','123454');
INSERT INTO trips VALUES('1', 'around town', '120', '30','12/12/2001', '34758');