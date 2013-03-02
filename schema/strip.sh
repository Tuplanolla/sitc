#! /bin/sh

if [ -z "$1" ]
then
	echo "clean.sh filename"
	exit 1
fi

cpp -P -fpreprocessed "$1" | grep -v "^\s*$" > "$1.tmp"
mv -f "$1.tmp" "$1"
