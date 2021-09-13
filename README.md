# SWT focus experiments

The behaviour of focus switching changes between SWT 3.112.0 and 3.113.0.
This app can be used to demonstrate that change.

To build the appropriate version pass it as the property swt.version.

If you put the two windows on different workspaces, then click on one of the buttons:

* on version 3.112.0 you will get a notification from the other window
* on version 3.113.0 you will switch to the desktop of the other window


## Setup

To build, you need to provide the platform on which to build:

    module load java/11
    module load maven
    mvn clean package -Dosgi.platform=gtk.linux.x86_64 -Dswt.version=3.113.0

You also need some magic files in `~/.swt/lib/linux/x86_64`. These must be copied
from an installation of Eclipse that matches the SWT version. In my case:

    cp -r ./eclipse-2021-06/configuration/org.eclipse.osgi/410/0/.cp/* ~/.swt/lib/linux/x86_64/

## Run

    java -jar java -jar target/swt-focus-1.0-SNAPSHOT-shaded.jar

