LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := lua

LOCAL_CFLAGS    := -Wall -DLUA_COMPAT_ALL -DLUA_USE_LINUX

LOCAL_SRC_FILES := src/lapi.c \
	src/ldebug.c \
	src/linit.c \
	src/loadlib.c \
	src/lstate.c \
	src/ltm.c \
	src/lzio.c \
    src/lauxlib.c \
	src/ldo.c \
	src/liolib.c \
	src/lobject.c \
	src/lstring.c \
	src/print.c \
    src/lbaselib.c \
	src/ldump.c \
	src/llex.c \
	src/lopcodes.c \
	src/lstrlib.c \
    src/lcode.c \
	src/lfunc.c \
	src/lmathlib.c \
	src/loslib.c \
	src/ltable.c \
	src/lundump.c \
    src/ldblib.c \
	src/lgc.c \
	src/lmem.c \
	src/lparser.c \
	src/ltablib.c \
	src/lvm.c \

LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/src
include $(BUILD_STATIC_LIBRARY)
