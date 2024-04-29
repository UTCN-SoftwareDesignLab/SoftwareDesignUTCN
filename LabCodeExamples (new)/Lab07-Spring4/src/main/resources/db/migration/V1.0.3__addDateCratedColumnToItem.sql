-- add dateCreated column to item table
ALTER TABLE item
    ADD COLUMN dateCreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP;