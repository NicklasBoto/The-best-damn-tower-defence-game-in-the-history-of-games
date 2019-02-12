
README for TowerDefence
-----------------------

Additional (non Java standard) software required

NOTE: All software located in /lib directory.
Possibly must register the lib's in IntelliJ
File > Project Structure > Libraries > click on + sign > Java, browse to select

Lombok
------
Used for generating getter/setters using annotations (@Getter)
See: https://projectlombok.org/

Install Lombok Plugin: File > Settings > Plugins. Search for lombok, not
found, click Search in Repositories. When found click Install.
Plugin should be visible (in the list) after installation.

To enable annotation processing in IntelliJ
File > Settings... > Build, Execution, Deployment > Compiler >
Annotation Processors > Click Enable annotation processing

Gson
----
Gson is a library for serialize/deserialize Java Object to/from JSON
Level files are in JSON format. https://github.com/google/lib

Libtiled
--------
Used to read Tile maps created with Tiled.
https://www.mapeditor.org/

JUnit
-----
The JUnit 4 is used for testing. Supplied with
IntelliJ but possible most add to class path. Click red light bulb at error
mark and select "add Junit 4 to class path".