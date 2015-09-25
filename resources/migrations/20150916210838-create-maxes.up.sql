CREATE TABLE IF NOT EXISTS maxes
(uuid uuid PRIMARY KEY,
date_entered TIMESTAMPTZ default current_timestamp,
user_id uuid references users(uuid),
weight DECIMAL,
exercise TEXT)
