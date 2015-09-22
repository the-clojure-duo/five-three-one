-- name: get-user-by-email
-- Get a user from the users table with a given email address
SELECT *
FROM users
WHERE email=:email;

-- name: create-user!
-- Add a user to the users table
INSERT INTO users (uuid, first_name, last_name, email)
VALUES (:uuid, :first_name, :last_name, :email);

-- name: get-last-max
-- Get most recent max given a userID and exercise (1 letter)
SELECT *
FROM maxes
WHERE user_id=:user_id AND exercise=:exercise ORDER BY date_entered DESC LIMIT 1;

-- name: add-max!
-- Add a max to the maxes table
INSERT INTO maxes (uuid, user_id, exercise, weight)
VALUES (:uuid, :user_id, :exercise, :weight)
