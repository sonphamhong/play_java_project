# Add another column to m_users

# --- !Ups
ALTER TABLE m_users ADD login_id varchar(255);
ALTER TABLE m_users ADD password varchar(255);

# --- !Downs
ALTER TABLE m_users DROP login_id;
ALTER TABLE m_users DROP password;
