LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := fluidballs
LOCAL_SRC_FILES := fluidballs.cpp

include $(BUILD_SHARED_LIBRARY)
