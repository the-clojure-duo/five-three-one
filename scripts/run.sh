#!/bin/bash
set -xe
npm install;
node node_modules/gulp/bin/gulp.js &
lein do run migrate, figwheel
