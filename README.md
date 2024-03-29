# ZX-Ed

**ZX-Ed** is a database editor and script generator for [ZXDB](https://github.com/zxdb/ZXDB).

Whenever any change is made to the database using **ZX-Ed**, its corresponding SQL statement is generated and logged into file `/tmp/ZX-Ed.sql`. This approach provides the following advantages:

* The generated SQL works as "auditing" for all [ZXDB](https://github.com/zxdb/ZXDB) changes, so the history behind every single change in the database can be preserved and revised independently.
* It's not necessary to edit the official [ZXDB](https://github.com/zxdb/ZXDB) instance directly. A separate [ZXDB](https://github.com/zxdb/ZXDB) clone can be edited instead, generating the SQL file that can be revalidated later, before applying these changes to official [ZXDB](https://github.com/zxdb/ZXDB).


## How to run ZX-Ed

#### Requirements

* Download and install Java 8 or later, preferably [Java JDK 8](https://www.oracle.com/technetwork/java/javase/downloads/). Don't forget to set environment variable `JAVA_HOME`. You better also set environment variable `JAVA_TOOL_OPTIONS` with value `-Dfile.encoding=UTF8` otherwise certain special characters (such as �) won't work properly.
* Download and install [Apache Tomcat](https://tomcat.apache.org/).
* Download and install [MariaDB](https://downloads.mariadb.org/) (or MySQL), setting UTF8 as default server's charset.


#### Database creation

* Download `ZXDB_mysql.sql` from [ZXDB](https://github.com/zxdb/ZXDB), then execute it using a database client (such as HeidiSQL) as UTF8. It will create database [ZXDB](https://github.com/zxdb/ZXDB) and populate it.
* Execute `scripts/zxed_prepare.sql` using the same database client as UTF8. It will prepare [ZXDB](https://github.com/zxdb/ZXDB) for editing, adding version control to protect against concurrent changes from multiple users (later the original [ZXDB](https://github.com/zxdb/ZXDB) structure can be restored by executing `scripts/zxed_reset.sql`).


#### Tomcat preparation

* Download [MariaDB Java Connector/J](https://downloads.mariadb.org/) 2.3.0 or later, then copy it to `tomcat/lib/`.
* Edit `tomcat/conf/context.xml` to add **ZX-Ed** datasource (replacing username and password as needed):
```
    <Resource name="jdbc/ZX-EdDS" auth="Container" type="javax.sql.DataSource"
              maxTotal="20" maxIdle="5" maxWaitMillis="10000"
              username="root" password="password" driverClassName="org.mariadb.jdbc.Driver"
              url="jdbc:mysql://localhost:3306/zxdb"/>
```


#### ZX-Ed installation

* Download latest release of `ZX-Ed.war`, then copy it to `tomcat/webapps/`.
* Run `tomcat/bin/startup` to start Tomcat.
* Open http://localhost:8080/ZX-Ed/ in a browser.


## How to use ZX-Ed

**ZX-Ed** is a C.R.U.D. application to edit [ZXDB](https://github.com/zxdb/ZXDB) data. Every page in **ZX-Ed** (listed in the left side menu) corresponds to a table in [ZXDB](https://github.com/zxdb/ZXDB).

Each page in **ZX-Ed** has multiple use modes, as detailed below.

* **_List mode_**:

![Group (List mode)](/images/list.png)

Whenever you select a page from the left side menu, it will always start in **_List mode_**. From here, you can locate an existing record on this table, then click either `EDIT` or `DELETE`. Or you can start to create a new record clicking `NEW`.

* **_New mode_**:

![Group (New mode)](/images/new.png)

Whenever you click `NEW`, it will enter **_New mode_**. It will typically start with a blank page, except for certain fields automatically filled with default values. Now you can freely input new content, then click `SAVE` to insert this record into the database. If the record is created successfully, the page will automatically change to **_Edit mode_**.

* **_Edit mode_**:

![Group (Edit mode)](/images/edit.png)

The **_Edit mode_** is almost identical to **_New mode_**, except it's for modifying an existing record in the database, instead of creating a new record. The most visible difference is, when you are editing a record that already exists, you will also have an option to remove it from the database clicking `DELETE`. Or you can make changes, then click `SAVE` to update the record in the database.

* **_Sub-lists_**:

![Member (Group sub-list)](/images/sublist.png)

Although every table in [ZXDB](https://github.com/zxdb/ZXDB) has its own page in **ZX-Ed**, the same table may also appear as sub-list in another related table's page. However this data appears duplicated just for convenience, since editing data from a sub-list will produce exactly the same result as the original table's page.

Each sub-list works independently from the rest of the page, with separate `NEW`, `EDIT` and `SAVE` options. The most visible difference is that each sub-list has its own message "`There are ? records in list`" at the bottom.


## How to recompile ZX-Ed

#### Additional requirements

* Download and install [Apache Ant](https://ant.apache.org/).
* Download and install a Java IDE, preferably [Eclipse IDE for Java EE Developers](http://www.eclipse.org/downloads/packages/).
* Download and install [Lombok](https://projectlombok.org/) 1.18.10 or later, to configure it into the Java IDE.


#### Project preparation

**ZX-Ed** depends on [OpenXava](https://sourceforge.net/projects/openxava/), that doesn't support Maven. Therefore a few "manual" steps are necessary to prepare the development environment and its dependencies:

* Clone **ZX-Ed** repository.
* Download `openxava-6.6.3-*.zip` from [OpenXava](https://sourceforge.net/projects/openxava/), unzip it, then copy its `workspace` content into **ZX-Ed**'s `workspace`.
* Inside directory `workspace/ZX-Ed/`, execute `ant createProject` to complete creating the project.


#### Application development

* Open project "ZX-Ed" using a Java IDE (preferably Eclipse) for editing.
* Inside directory `workspace/ZX-Ed/`, execute `ant compile` to recompile it, then execute `ant createWar` to recreate it.


#### User configuration

* By default, **ZX-Ed** recognizes users "admin" (password "admin") and "guest" (password "guest"). The latter is a special user with read-only permissions only.
* To define different users and/or modify passwords, edit file `properties/naviox-users.properties` (either from `workspace/ZX-Ed/` prior to compilation, or directly inside a released `ZX-Ed.war`).


## License

**ZX-Ed** is open source, freely available under the Apache 2.0 License.


## Credits

**ZX-Ed** was created by Einar Saukas using [OpenXava 6.6.3](https://sourceforge.net/projects/openxava/), [Lombok](https://projectlombok.org/), and [SOL](http://summabr.github.io/sol/).


![ZX-Ed](/images/ZX-Ed_8.png)
