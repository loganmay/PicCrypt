# PicCrypt
The goal of this project was to find a simple way to hide a message inside of a picture.

## How does it work?
The color of a pixel in an image is represented with 24 or 32 bits of data, depending on the format of the image.  
If it's a 32-bit pixel, then each of the following fields are assigned 8 bits: Red, Green, Blue, and Alpha (a transparency value).

Because low changes in transparency are undetectable to the naked eye, we can slightly modify the transparency without an untrained
eyes noticing.  So, in our program, we load a picture such that the alpha value ismaximized (an integer value of 255), and then 
subtract from the alpha valuean integer value that maps to a letter (e.g. A = 1, B = 2).   Doing so, we're effectively storing a 
letter inside each pixel (e.g. a pixel with an alpha of 254 contains 'A' and 253 contains 'B' and so on).

Computers can easily detect this change by simply reading the alpha value of each pizel. Therefore, with a symmetric map, the message 
can be pulled back out from the image by checking each pixel's alpha value for it's difference from 255, and converting that integer
back to it's corresponding letter (again A = 1, B = 2, etc.)


