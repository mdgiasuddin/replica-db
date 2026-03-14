#!/bin/bash
set -e

echo "Configuring replication access..."

echo "host replication repl_user 0.0.0.0/0 md5" >> "$PGDATA/pg_hba.conf"