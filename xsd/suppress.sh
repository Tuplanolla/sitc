#! /bin/sh

if [ -z "$1" ]
then
	echo "suppress.sh filename"
	exit 1
fi

sed "s/^\(\s*\)\(\(\(public\|protected\|private\|abstract\|static\|final\|strictfp\)\s\+\)*class\)/\1@SuppressWarnings(\"all\")\n\1\2/g" "$1" >"$1.tmp"
mv -f "$1.tmp" "$1"
