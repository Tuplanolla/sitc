#! /bin/sh

xjc -no-header -p org.sitdb.model.db -d . .
find -name "*.java" -exec sh ./strip.sh {} \;
