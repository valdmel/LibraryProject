# LibraryProject

A Java Web project I programmed with a friend back in college in 2016 using GlassFish 4.1.1 and JDBC. It was part of a class project where students had to create an application that uses a database and CRUD operations. In our case, we came up with a library idea, where you can control books' information, add authors and more.

## Requirements
- About 10 minutes.
- Your preferred IDE (the project was made using NetBeans) with Java EE installed.
- GlassFish 4.1.1 (unfortunately, it doesn't work with newer versions, as far as I tested).

## How To Execute
- The project may say that there are missing libraries. Just ignore it.
- Go to the 'Services' tab > 'Database' > 'Java DB', right click on it, select 'Create Database...' and name it 'LibraryDB'.
- Double click on 'jdbc:derby://localhost:1527/LibraryDB' and click on 'OK'.
- Go to the 'Files' tab > Queries.sql, right click on it and select 'Run' (or just press Shift+F6).
- Select 'jdbc:derby://localhost:1527/LibraryDB' from the list and click 'OK'.
- Go to the 'Projects' tab and execute 'ProjetoWeb'. Done!
