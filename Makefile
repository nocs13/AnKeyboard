all: build

build:
	ant clean && ant debug

release:
	ant clean && ant release

clean:
	ant clean
	rm -rf obj libs assets/*
run:
	~/Install/android-sdk-linux/platform-tools/adb uninstall com.kgm.kAnKeyboard
	~/Install/android-sdk-linux/platform-tools/adb install bin/kAnKeyboard-debug.apk

