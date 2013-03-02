#! /bin/sh

xjc -npa -no-header -p org.sitdb.model.db -d . .
find -name "*.java" -exec sh ./strip.sh {} \;
