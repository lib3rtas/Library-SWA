# Library-SWA
A modular spring library application for Software Architecture HS2020

Matriculation Number: (17-550-708)

#### Minimum requirements 

- [X] List the entities
- [X] Create an entity
- [X] Edit an entity

#### Possible extensions

- [X] Delete
- [ ] Entity: `Magazine` (as extension of `Book`)
- [ ] Image upload (in its own *module*)

#### Data persistency
- [X] Simple In-Memory per java.util.List
- [X] Restart persistent data storage (H2 Database with File)

#### Checklist
- [ ] Description (POM file, index.html, Markdown file, Component/class diagram)
- [X] It works
- [X] License
- [X] View component exchangeable
- [X] Repository component exchangeable
- [X] Minimum requirements
- [X] Other (extensions)
- [X] Commit log
- [X] Tests (4 Integrationtests)
- [ ] Comments, documentation
- [ ] Good naming
- [ ] Artistic value
- [ ] Particular effort, technical challenge


## Project delivery
(_to be filled by student_)???

How to start the project:

```
./gradlew bootRun

or

./gradlew clean build && ./gradlew bootRun
```
How to test the project:

`./gradlew clean test`

External contributions:
* Architecture ideas from (also simply wanted to try building something like that, was a good opportunity) [myshop](https://github.com/ribeaud/blog-code-samples/tree/master/myshop)
* How to best modularize spring projects [https://spring.io/guides/gs/multi-module/](https://spring.io/guides/gs/multi-module/)
* Simple Java random string generation [https://stackoverflow.com/a/157202](https://stackoverflow.com/a/157202)
* Not really an external contribution, but generally used the official documentations of Spring, H2, JUnit ...

Other comments:

I implemented the Integration Tests in a single module (app) and it got me thinking. How would one approach this best?
I think Unit Test would definitely be best implemented in the regarding module. But where would be "the best" place for integration tests?

I'm particular proud of:

The data repository switching. Different data sources can be selected in *spring/../BookConfiguration.java*
```
IBookService bookService(@Qualifier("h2") IBookRepository bookRepository)
	
or

IBookService bookService(@Qualifier("list") IBookRepository bookRepository)
```
Theoretically it is also possible, to change the storage type of the H2 database from File to InMemory. Sadly Springs BeanFactory produces a really 
unusual problem with this configuration and cannot call the schema.sql and data.sql successfully. Therefore it technically and 
architecturally could work, but I couldn't find the issue and didn't want to waste more time on it.

To change from H2 File to H2 InMemory simply replace the **spring.datasource.url** property with **jdbc:h2:mem:data** in *app/../resources/application.properties*
```
...
spring.datasource.url=jdbc:h2:mem:data
...
```
and move the **@Primary** annotation in *spring/../BookConfiguration.java* from **h2InFileDataSource** to **h2InMemDataSource**
```
---	@Primary
	@Bean
	DataSource h2InMemDataSource() {

        ...

+++	@Primary
	@Bean
	DataSource h2InMemDataSource() {
```
## Project grading

(_to be filled by lecturer_)
