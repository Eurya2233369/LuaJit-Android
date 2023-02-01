LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := luajit
LOCAL_SRC_FILES :=  /storage/emulated/0/AndroidIDEProjects/Application/app/$(LOCAL_PATH)/lib/$(TARGET_ARCH_ABI)/libluajit.a
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/include
include $(PREBUILT_STATIC_LIBRARY)
