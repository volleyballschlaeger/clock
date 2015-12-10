#!/usr/bin/env jython

import time
import HttpClock

clk = HttpClock( "https://www.google.de" )
clk.sync()

while True:
	time.sleep( 1 )
	c = clk.getTime() / 1000
	print time.strftime( "%a %b %d %H:%M:%S %Z %Y", time.localtime( c ) )
