# this file use for generate an new app from this base

# check your bash version to avoid errors appearing
if [[ ${BASH_VERSINFO[0]} -lt 4 ]]
then
  echo "You need bash version at least 4.0 to run this file!"
  exit 1
fi

# exit when any error appearing
set -e

# tutorial
if [[ $# -lt 2 ]]
then
  echo "usage: bash gen-new-app.sh packageName appName"
  exit 2
fi

# parameters
PACKAGE_NAME=$1 # ~ App id
APP_NAME=$2 # ~ folder name
APP_PATH=${PACKAGE_NAME//.//} # replace . by /

# implement

for n in $(find . -type d \( -path '*/src/androidTest' -or -path '*/src/test' -or -path '*/src/main' \) )
do
  # create package by APP_PATH
  mkdir -p $n/java/$APP_PATH
  # move all file from old package to new package
  mv $n/java/com/projectbase/* $n/java/$APP_PATH
  # remove old package
  rm -r $n/java/com/projectbase
done

# rename package, imports
find ./ -type f -name "*.kt" -exec sed -i.neko "s/package com.projectbase/package $PACKAGE_NAME/g" {} \;
find ./ -type f -name "*.kt" -exec sed -i.neko "s/import com.projectbase/import $PACKAGE_NAME/g" {} \;
find ./ -type f -name "AndroidManifest.xml" -exec sed -i.neko "s/com.projectbase/$PACKAGE_NAME/g" {} \;
find ./ -type f -name "build.gradle" -exec sed -i.neko "s/applicationId \"com.projectbase/applicationId \"$PACKAGE_NAME/g" {} \;

# rename app
find ./ -type f -name "strings.xml" -exec sed -i.neko "s/ProjectBase/$APP_NAME/g" {} \;

# clean
find . -name "*.neko" -type f -delete
rm -rf gen-new-app.sh
rm -rf .git


# rename folder
cd .. && mv ./android-skeleton ./$APP_NAME