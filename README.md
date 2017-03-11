# PicCrypt
The goal of this project was to find a simple way to hide a message inside of a picture.

## How does it work?
The color of a pixel in an image is represented with 24 or 32 bits of data, depending on the format of the image.  
If it's a 32-bit pixel, then each of the following fields are assigned 8 bits: Red, Green, Blue, and Alpha (a transparency value).

Because minute changes in color are undetectable to the human eye, we can slightly modify the transparency value of pixels in an image
without detection. If we modify those values in a meaningful way, we can store a message.

## Storing the message
In our program, we load a picture such that the alpha value is maximized (an integer value of 255), and then subtract from the alpha value an integer value that mapped to a letter (e.g. A = 1, B = 2).   Wwe're effectively storing a letter inside each pixel (e.g. a pixel with an alpha of 254 contains 'A' and 253 contains 'B' and so on).

Humans can't see it, but computers can easily detect this change by simply reading the alpha value of each pixel. Therefore, with a symmetric map, the message can be pulled back out from the image by checking each pixel's alpha value for it's difference from 255, and converting that integerback to it's corresponding letter (again A = 1, B = 2, etc.).

## Who should use this method of secretly sending messages?
1) People who aren't and won't be under any legitimate intelligent investigation
2) Enemies of the free world


