
#include <stdio.h>
#include "FluidBalls.h"

int main()
{
  FluidBalls* fb;

  fb = new FluidBalls(100, 640, 480);


  delete fb;
}
