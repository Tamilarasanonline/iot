#!/usr/bin/env python

import cv2;
import sys;

print "This is the name of the script: ", sys.argv[0]
print "Number of arguments: ", len(sys.argv)
print "The arguments are: " , str(sys.argv)

fileName = '/home/pi/one.jpeg';
targetName = '/home/pi/iot/target/one.jpg';
cascPath = '/home/pi/opencv-3.4.0/data/haarcascades/';
index = 1 ;
print (index) ;	
if int(index) == 0:
        cascPath+= 'haarcascade_frontalface_alt.xml';
elif int(index) == 1:
			cascPath+= 'haarcascade_frontalface_alt2.xml';
elif int(index) == 2:
			cascPath+= 'haarcascade_frontalface_alt_tree.xml';
elif int(index) == 3:
                        cascPath+= 'haarcascade_frontalcatface_extended.xml';
elif int(index) == 4:
                        cascPath+= 'haarcascade_frontalcatface.xml';
else:
			cascPath+= 'haarcascade_frontalface_default.xml';
print (cascPath) ;
				
faceCascade = cv2.CascadeClassifier(cascPath)
#Read the image
image = cv2.imread(fileName)
gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
# Detect faces in the image
faces = faceCascade.detectMultiScale(
                        gray,
                        scaleFactor=1.1,
                        minNeighbors=5,
                        minSize=(30, 30),
                        flags = cv2.CASCADE_SCALE_IMAGE
                )
print ("Found {0} faces!".format(len(faces)))

                # Draw a rectangle around the faces
for (x, y, w, h) in faces:
    cv2.rectangle(image, (x, y), (x+w, y+h), (0, 255, 0), 2)
    cv2.imwrite(targetName ,image);
