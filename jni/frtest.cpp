
#include <stdio.h>
#include "FixedReal.h"

#define PI (3.14159265358979)

typedef FixedReal<int, 8> FR;

int main()
{
  FR x(PI), y(2.00);
  double r;

  printf("sizeof FixedReal: %d\n", sizeof(FR));

  printf("x: %f, y: %f\n", (double)x, (double)y);

	r = x + y;
  printf("x + y:\t%f\n", r);

	r = x + 10.0;
  printf("x + 10.0:\t%f\n", r);

	r = x + -y;
  printf("x + -y:\t%f\n", r);

	r = x - y;
  printf("x - y:\t%f\n", r);

  r = x * y;
  printf("x * y:\t%f\n", r);

  r = x / y;
  printf("x / y:\t%f\n", r);

  r = x / 1.00;
  printf("x / 1.00:\t%f\n", r);

  printf("x == y:\t%d\n", x == y);
  printf("x != y:\t%d\n", x != y);

  printf("x < y:\t%d\n", x < y);
  printf("x > y:\t%d\n", x > y);

  printf("x <= y:\t%d\n", x <= y);
  printf("x >= y:\t%d\n", x >= y);

  printf("y == 2.0:\t%d\n", y == 2.0);

}
