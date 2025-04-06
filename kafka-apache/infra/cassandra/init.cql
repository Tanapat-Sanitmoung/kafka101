-- Create a keyspace
CREATE KEYSPACE IF NOT EXISTS myAppKeySpace
WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

-- Use the keyspace
USE myAppKeySpace;

-- Create a customer table
CREATE TABLE IF NOT EXISTS customer (
    id TEXT PRIMARY KEY,
    name TEXT,
    email TEXT
);
