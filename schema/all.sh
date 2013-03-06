#! /bin/sh

xjc -no-header -p org.sitdb.model.xml -d . .
find -name "*.java" -exec sh ./strip.sh {} \;
