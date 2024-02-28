This is a simple project to verify the runtime behavior of tomcat with JPMS modules. This maven project consists of a module-info.java and is a module itself. 

This project depends on another sample library(modularised) called modulelibrary(check pom.xml) that has 2 packages com.modulelib.exposed and com.modulelib.hidden.

In the version 1 of modulelibrary dependency, both the com.modulelib.exposed and com.modulelib.hidden are exported in module-info.java. So the imports of classes from these 2 packages in the HelloWorldServlet.java works well during compile time.
And the war file is generated, deployed on tomcat works fine. Both compile time and run time behaviors are as expected.

Now I modified modulelibrary to *not* export com.modulelib.hidden package and release version 2. 

I came back to webhello3, and changed the pom.xml to depend on version 2 of modulelibrary and rebuilt the project. The expectation here is that IDE will start complaining about the usage of a class that is not exported and compilation would fail.
The compile time behavior was as expected. The IDE started to complain as soon as the pom.xml started depending on version 2 of modulelib.

![Screenshot 2024-02-28 at 1 37 52 AM](https://github.com/rvfunky/webhello3/assets/11734009/947f28d3-8128-4495-a9a3-845a9deb2baf)


Now, to verify the run time behavior in tomcat - I stopped the tomcat, swapped the version 1 jar of modulelib with version 2, and expected things to break upon tomcat restart. 
However, things were still working proving that the package encapsulation in jpms modules is not honored by classloaders in tomcat.

![Screenshot 2024-02-28 at 1 57 37 AM](https://github.com/rvfunky/webhello3/assets/11734009/75fd1b2d-d028-42e6-88d8-98ad3bee1224)

![Screenshot 2024-02-28 at 1 58 25 AM](https://github.com/rvfunky/webhello3/assets/11734009/92f9176e-3486-4495-9ec8-6838dd7e41d6)
