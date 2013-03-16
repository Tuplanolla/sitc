#! /bin/sh

xjc -no-header -p org.sitdb.model.xml -d . .
echo "stripping a schema..."
find -name "*.java" -exec sh strip.sh {} \;
find -name "*.java" -exec sh suppress.sh {} \;
