Car Reservation system.

How to Run:
Install apache tomcat (developed and tested with 7 version),
MySQl server,
Run database creation script from resources/DatabaseSchema.sql,
Add database login password in WEB-INF/mvc-dispatcher-servlet.xml,  
Run=>Edit Configurations select Tomcat server.

Known major bugs:
After editing user or car reservation Id becomes 0 (!ACID)
UI is could be much better.
Deadline has come and I haven`t time to fix it.

Unfinished parts:
Spring Validation,
Correct input fields(calendar & dropdowns for time)
Login service,
Audit Fields (need login),
History table & page.
Correct messaging(currently no messages displays if editing reservation and car or user already have reservation)
mb REST implementation.

Next time I`ll definetly use testing frameworks;
