#!/usr/bin/env bash

set -eou pipefail

ver=16
DB_NAME="nextjstut"
DB_USER="zapster"
DB_PASS="psql4zapster"
SUPERUSER="postgres"
SUPERUSER_PASSWORD="Letme!n3"

if psql -V > /dev/null 2>&1; then
    echo "PostgreSQL is installed."    
else
    echo "PostgreSQL is not installed."
    echo "Proceeding with installation..."

    brew install postgresql@$ver &
	wait $!
	brew services restart postgresql@$ver &
	wait $!
	# echo 'export PATH="/opt/homebrew/opt/postgresql@16/bin:$PATH"' >> ~/.zshrc
	echo 

	if psql -V > /dev/null 2>&1; then
	    echo "PostgreSQL is now installed."
	fi    

fi

echo "Trying to create the superuser..."
createuser -s $SUPERUSER 2>/dev/null || true
if psql -U postgres -tAc "SELECT 1 FROM pg_roles WHERE rolname='$SUPERUSER'" | grep -q 1; then
    echo "Superuser '$SUPERUSER' already exists or was created successfully."
    psql -U postgres -c "ALTER USER $SUPERUSER WITH PASSWORD '$SUPERUSER_PASSWORD';"
else
    echo "Failed to create the superuser."
fi

echo "Setting up the user: $DB_USER"
if psql -U $SUPERUSER -tAc "SELECT 1 FROM pg_roles WHERE rolname='$DB_USER'" | grep -q 1; then
    echo "User '$DB_USER' already exists."
else
    # Create the user with superuser privileges
    psql -U $SUPERUSER -c "CREATE USER $DB_USER WITH PASSWORD '$DB_PASS';"
	if [ $? -eq 0 ]; then
	    echo "User '$DB_USER' created successfully with the specified password."
	else
	    echo "Failed to create user '$DB_USER'."
	fi
fi


echo "Creating the database: $DB_NAME"
if psql -lqt | cut -d \| -f 1 | grep -qw $DB_NAME; then
    echo "Database '$DB_NAME' already exists."
else
	createdb -U $SUPERUSER $DB_NAME
	if [ $? -eq 0 ]; then
	    echo "Database '$DB_NAME' created successfully."
	else
	    echo "Failed to create database '$DB_NAME'."
	fi
fi

psql -U $SUPERUSER -c "GRANT ALL PRIVILEGES ON DATABASE $DB_NAME TO $DB_USER;"
if [ $? -eq 0 ]; then
    echo "All privileges on database '$DB_NAME' granted to user '$DB_USER' successfully."
else
    echo "Failed to grant privileges on database '$DB_NAME' to user '$DB_USER'."
fi

psql -U $SUPERUSER -d $DB_NAME -c "GRANT ALL ON SCHEMA public TO $DB_USER;"
if [ $? -eq 0 ]; then
    echo "All privileges on schema public granted to user '$DB_USER' successfully."
else
    echo "Failed to grant all privileges on schema public to user '$DB_USER'."
fi

psql -U $SUPERUSER -d $DB_NAME -c "GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO $DB_USER;"
if [ $? -eq 0 ]; then
    echo "All privileges on all tables in database '$DB_NAME' granted to user '$DB_USER' successfully."
else
    echo "Failed to grant all privileges on all tables in database '$DB_NAME' to user '$DB_USER'."
fi

psql -U $SUPERUSER -d $DB_NAME -c "GRANT USAGE ON SCHEMA public TO $DB_USER;"
if [ $? -eq 0 ]; then
    echo "Usage on schema public' granted to user '$DB_USER' successfully."
else
    echo "Failed to grant usage on schema public to user '$DB_USER'."
fi


