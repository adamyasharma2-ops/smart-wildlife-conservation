-- WildGuard Database Setup Script
-- Run this as postgres superuser against the WildGuard database
-- psql -U postgres -d WildGuard -f setup-db.sql

-- Create schemas if not exist
CREATE SCHEMA IF NOT EXISTS wildlife;
CREATE SCHEMA IF NOT EXISTS protected_areas;
CREATE SCHEMA IF NOT EXISTS patrol;
CREATE SCHEMA IF NOT EXISTS incidents;

-- Grant privileges
GRANT ALL PRIVILEGES ON SCHEMA wildlife TO postgres;
GRANT ALL PRIVILEGES ON SCHEMA protected_areas TO postgres;
GRANT ALL PRIVILEGES ON SCHEMA patrol TO postgres;
GRANT ALL PRIVILEGES ON SCHEMA incidents TO postgres;

-- Set search path for verification
SET search_path TO wildlife, protected_areas, patrol, incidents, public;

SELECT 'Database setup complete. Schemas: wildlife, protected_areas, patrol, incidents' AS status;
