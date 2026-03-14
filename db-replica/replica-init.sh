#!/bin/bash
set -e

DATA_DIR="/var/lib/postgresql/data"

if [ ! -f "$DATA_DIR/standby.signal" ]; then
  echo "Replica not initialized, running base backup..."

  until pg_isready -h postgres-primary -p 5432; do
    sleep 2
  done

  rm -rf "$DATA_DIR"/*

  export PGPASSWORD=repl_pass

  pg_basebackup \
    -h postgres-primary \
    -p 5432 \
    -D "$DATA_DIR" \
    -U repl_user \
    -Fp -Xs -P -R

  echo "Fixing permissions..."
  chown -R postgres:postgres "$DATA_DIR"
  chmod 0700 "$DATA_DIR"

  echo "Base backup completed"
fi

exec gosu postgres postgres