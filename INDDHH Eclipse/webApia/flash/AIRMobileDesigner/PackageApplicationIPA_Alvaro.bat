@echo off

:: AIR application packaging
:: More information:
:: http://livedocs.adobe.com/flex/3/html/help.html?content=CommandLineTools_5.html#1035959

:: Path to Flex SDK binaries
set PATH=%PATH%;h:\Development\flex4.6\bin

:: Signature (see 'CreateCertificate.bat')
set CERTIFICATE=iOS_dev.p12
set SIGNING_OPTIONS=-storetype pkcs12 -keystore %CERTIFICATE%
if not exist %CERTIFICATE% goto certificate

:: Output
if not exist air md air
set AIR_FILE=c:/air/FacilisBPMN.ipa

:: Input
set APP_XML=mobile_application_iPad.xml
set FILE_OR_DIR=-C bin .

::IOS PROFILE
set IOS_PPROFILE=Facilis_BPMN.mobileprovision

echo Signing AIR setup using certificate %CERTIFICATE%.
call h:\Development\AdobeAIRSDK\bin\adt -package -target ipa-test -provisioning-profile %IOS_PPROFILE% %SIGNING_OPTIONS% %AIR_FILE% %APP_XML% %FILE_OR_DIR%
if errorlevel 1 goto failed

echo.
echo AIR setup created: %AIR_FILE%
echo.
goto end

:certificate
echo Certificate not found: %CERTIFICATE%
echo.
echo Troubleshotting: 
echo A certificate is required, generate one using 'CreateCertificate.bat'
echo.
goto end

:failed
echo AIR setup creation FAILED.
echo.
echo Troubleshotting: 
echo did you configure the Flex SDK path in this Batch file?
echo.

:end
pause
