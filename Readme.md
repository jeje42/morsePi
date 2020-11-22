# MorsePi

This is a light project to command light messages from a GPIO capable device. Tested from a raspberry pi.

Technology stack:
  - spring-boot
  - pi4j

By providing application.properties/yml file, it is possible to control:
  - gpio used to output the signal. Please see the wiring numbering used by pi4j project.
  - morse signal settings for short, long and pause time in seconds.
