#!/bin/bash
set -xe
cd "${BASH_SOURCE%/*}" || exit;
npm install;
node node_modules/gulp/bin/gulp.js &
lein do run migrate, figwheel
