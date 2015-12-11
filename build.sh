#!/bin/bash
set -xe
cd "${BASH_SOURCE%/*}" || exit;
. $HOME/.bashrc;
npm install;
node node_modules/gulp/bin/gulp.js build;
export CLIENT_ID="something";
export CLIENT_SECRET="something";
export REDIRECT_URI="something";
export DATABASE_URL="something";
export PG_USER="something";
export PG_PASSWORD="something";
lein uberjar;
