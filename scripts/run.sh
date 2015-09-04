#!/bin/bash
set -xe
if [ ! `command -v gulp` ]; then
    npm i -g gulp;
fi;
npm install;
gulp &
lein do run migrate, figwheel
