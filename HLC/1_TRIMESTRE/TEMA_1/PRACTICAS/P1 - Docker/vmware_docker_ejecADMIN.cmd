@echo off
echo  1    ACTIVAR VMWARE
echo  2    ACTIVAR WSL (DOCKER)

SET /p var= ^> Seleccione una opcion [1-2]:

if "%var%"=="1" goto op1
if "%var%"=="2" goto op2

:op1
echo bcdedit /set hypervisorlaunchtype off
bcdedit /set hypervisorlaunchtype off
if %errorlevel% equ 1 goto error
goto:fin

:op2
echo bcdedit /set hypervisorlaunchtype auto
bcdedit /set hypervisorlaunchtype auto
if %errorlevel% equ 1 goto error
goto:fin


:fin
color 27
echo ES NECESARIO REINICIAR!!!!
pause
shutdown /r /t 1
goto final


:error
cls
color 47
echo ERROR
echo HAY QUE EJECUTAR COMO ADMINISTRADOR PARA QUE FUNCIONE!!!
pause


:final