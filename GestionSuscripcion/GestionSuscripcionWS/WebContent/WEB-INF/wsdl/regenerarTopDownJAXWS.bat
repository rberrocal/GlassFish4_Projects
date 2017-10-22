set wsdl=GestionSuscripcionWS.wsdl

"%JAVA_HOME%\bin\wsimport" %wsdl% -s . -verbose -Xendorsed -extension -Xnocompile > resultadoGeneracion.log
pause

