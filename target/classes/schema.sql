

CREATE TABLE IF NOT EXISTS userfilter (
    id             SERIAL PRIMARY KEY,
    useremail      VARCHAR(100) NOT NULL,
    businessunit    VARCHAR(100) NOT NULL,
    filterdata       TEXT NOT NULL,
    createddt  DATE NOT NULL,
    updateddt  DATE NOT NULL
);

