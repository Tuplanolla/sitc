# SITC

SITC stands for String Instrument Tuning Calculator.
Its purpose is to calculate the optimal transitions in sequences of tunings to lessen the effort required to carry out the transitions and minimize the stress subjected to the instrument in question.
It can be also used to store such sequences, individual tunings and tuning-related information of stringed instruments.
The name is not very descriptive since it's built around an acronym.

## Schedule

This project was supposed to be finished by 2013-04-01, but
 was **cancelled** instead.
It might be resurrected one day, but
 not in the form it is now.

## Motivation

When I played more guitar, I always forgot how to change tunings sensibly, which ruined many recordings, sometimes broke strings and once even collapsed a bridge (of the guitar kind).
This program aims to solve such problems.

This project was started as a mandatory work for a programming course.

## License

SITC is licensed under
 the Creative Commons license attribution-noncommercial-sharealike.
Copies and derivative works are permitted as long as
 all the original authors are attributed and
 a compatible license is used.

The note and clef graphics are based on Denelson83's work on Wikimedia Commons.

## Installation

SITC is written in Java and requires
 the Java Platform version 7.

The following examples are from a (freshly installed) Arch Linux.

### Binaries

Binaries only need to be downloaded.

	[user@arch ~]$ wget https://github.com/Tuplanolla/sitc/blob/master/jar/sitc.jar

They may be older than the sources.

### Sources

Building SITC relies on a Java compiler and Apache Ant although
 any other build automation tool should work as well.
Eclipse is recommended since SITC comes with a project description file.

	[user@arch ~]$ pacman -S jdk7-openjdk eclipse

The libraries SITC depends on are
 the Apache Commons Mathematics library `commons-math3` and
 optionally the JUnit testing framework `junit`.
They are packaged with SITC.

Acquiring SITC from GitHub also requires
 SSH and
 Git.

	[user@arch ~]$ pacman -S openssh git

Once the required packages are installed the repository can be cloned

	[user@arch ~]$ git clone git@github.com:Tuplanolla/sitc.git

and SITC can be built.

	[user@arch ~]$ cd sitc
	[user@arch sitc]$ ant -buildfile build.xml

The build can be also invoked by importing the project into Eclipse, clicking build.xml and selecting Run As / Ant Build in the context menu.

The class files go in the `bin` directory and
 the archive in the `jar` directory.

## Running

The project can be run
 by invoking the class files

	[user@arch sitc]$ java -cp bin org.sitc.Main

 or the executable archive.

	[user@arch sitc]$ java -jar jar/sitc.jar

Clicking the archive should execute it automatically.

Example data files can be found in the `xml` directory and
 the schemas for the data files in the `xsd` directory.

## User Interface

Click the File menu and select the Exit option.

See the Screenshots section at the bottom.

More about that later.

## Development

Arbitrary tuning systems (like 53-tet) are a mess.
Please help.

### Directory Structure

Files are named and organized in a typical manner.
The directories are

* `/` for the most important files,
* `/src` for sources,
* `/bin` for build files,
* `/jar` for archives,
* `/lib` for libraries,
* `/res` for resources,
* `/doc` for documentation,
* `/javadoc` for automated documentation,
* `/xml` for databases,
* `/xsd` for schemas and
* `/etc` for other files.

## Attachments

### Screenshots

![Main Window](https://raw.github.com/Tuplanolla/sitc/master/etc/screenshot.png)
