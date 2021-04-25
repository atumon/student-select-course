set "Ymd=%date:~,4%%date:~5,2%%date:~8,2%"  

C:/"Program Files"/MySQL/"MySQL Server 5.7"/bin/mysqldump --opt -u root -p1234 studentselectcourse > D:/backup/db_%Ymd%.sql

forfiles /p "D:\backup" /s /m *.sql /d -5 /c "cmd /c del @path"

pause