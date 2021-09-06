# SWT focus experiments

## Setup

To build, you need to provide the platform on which to build:

    module load java/11
    module load maven
    mvn clean package -Dosgi.platform=gtk.linux.x86_64

You also need some magic files in `~/.swt/lib/linux/x86_64`. These must be copied
from an installation of Eclipse that matches the SWT version. In my case:

    cp -r ./eclipse-2021-06/configuration/org.eclipse.osgi/410/0/.cp/* ~/.swt/lib/linux/x86_64/

## Run

    java -jar java -jar target/swt-focus-1.0-SNAPSHOT-shaded.jar

