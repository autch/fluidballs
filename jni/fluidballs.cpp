
#include "FluidBalls.h"
#include "net_autch_android_bouncetest_FluidBallsThread.h"

FluidBalls* pInstance;

/*
 * Class:     net_autch_android_bouncetest_FluidBallsThread
 * Method:    fluidballs_init
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_net_autch_android_bouncetest_FluidBallsThread_fluidballs_1init
  (JNIEnv* env, jobject self, jint count, jint width, jint height)
{
	pInstance = new FluidBalls(count, width, height);
}

/*
 * Class:     net_autch_android_bouncetest_FluidBallsThread
 * Method:    fluidballs_exit
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_net_autch_android_bouncetest_FluidBallsThread_fluidballs_1exit
  (JNIEnv* env, jobject self)
{
	delete pInstance;
	pInstance = NULL;
}

/*
 * Class:     net_autch_android_bouncetest_FluidBallsThread
 * Method:    fluidballs_setaccel
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_net_autch_android_bouncetest_FluidBallsThread_fluidballs_1setaccel
  (JNIEnv* env, jobject self, jfloat ax, jfloat ay)
{
	pInstance->setAccel(ax, ay);
}

/*
 * Class:     net_autch_android_bouncetest_FluidBallsThread
 * Method:    fluidballs_drawballs
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_net_autch_android_bouncetest_FluidBallsThread_fluidballs_1drawballs
  (JNIEnv* env, jobject self)
{
	jmethodID methodID;
	jclass klass;

	klass = env->GetObjectClass(self);
	methodID = env->GetMethodID(klass, "drawBall", "(FFF)V");

	int count = pInstance->getCount();
	for(int i = 0; i < count; i++)
	{
		env->CallNonvirtualVoidMethod(self, klass, methodID,
				pInstance->getPX(i), pInstance->getPY(i), pInstance->getR(i));
	}
}

/*
 * Class:     net_autch_android_bouncetest_FluidBallsThread
 * Method:    fluidballs_update
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_net_autch_android_bouncetest_FluidBallsThread_fluidballs_1update
  (JNIEnv* env, jobject self)
{
	pInstance->update_balls();
}

