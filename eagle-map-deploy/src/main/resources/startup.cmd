@echo off
rem Copyright 1999-2018 Alibaba Group Holding Ltd.
rem Licensed under the Apache License, Version 2.0 (the "License");
rem you may not use this file except in compliance with the License.
rem You may obtain a copy of the License at
rem
rem      http://www.apache.org/licenses/LICENSE-2.0
rem
rem Unless required by applicable law or agreed to in writing, software
rem distributed under the License is distributed on an "AS IS" BASIS,
rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
rem See the License for the specific language governing permissions and
rem limitations under the License.
if not exist "%JAVA_HOME%\bin\java.exe" echo Please set the JAVA_HOME variable in your environment, We need java(x64)! jdk8 or later is better! & EXIT /B 1
set "JAVA=%JAVA_HOME%\bin\java.exe"

setlocal enabledelayedexpansion

set BASE_DIR=%~dp0
rem added double quotation marks to avoid the issue caused by the folder names containing spaces.
rem removed the last 5 chars(which means \bin\) to get the base DIR.
set BASE_DIR="%BASE_DIR:~0,-5%"

set CUSTOM_CONFIG_LOCATIONS=file:%BASE_DIR%/conf/

set SERVER=eagle-map-server

rem set EagleMap options
set "EAGLE_JVM_OPTS=-server -Xms512m -Xmx512m -Xmn256m -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=128m -XX:-OmitStackTraceInFastThrow -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=%BASE_DIR%\logs\java_heapdump.hprof -XX:-UseLargePages"
set "EAGLE_OPTS=%EAGLE_OPTS% -Deagle.home=%BASE_DIR%"
set "EAGLE_OPTS=%EAGLE_OPTS% -jar %BASE_DIR%\target\%SERVER%.jar"

rem set EagleMap spring config location
set "EAGLE_CONFIG_OPTS=--spring.config.additional-location=%CUSTOM_CONFIG_LOCATIONS%"

rem set eagle log4j file location
set "EAGLE_LOG4J_OPTS=--logging.config=%BASE_DIR%/conf/logback.xml"


set COMMAND="%JAVA%" %EAGLE_JVM_OPTS% %EAGLE_OPTS% %EAGLE_CONFIG_OPTS% %EAGLE_LOG4J_OPTS% eagle.eagle %*

rem start eagle command
%COMMAND%
