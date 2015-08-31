-- name: get-user-by-email
-- Get a user from the users table with a given email address
SELECT *
FROM users
WHERE email=:email;

-- name: create-user!
-- Add a user to the users table
INSERT INTO users (uuid, first_name, last_name, email)
VALUES (:uuid, :first_name, :last_name, :email);
