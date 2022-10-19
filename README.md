# JavaUnit_IntegrationTest

Unit testing : testing any method any layer of project one by one

Integrating test : testing hole of the aplication from Controller to repository layrs,
    and making sure that all is working good.
    without moking service or repository.
( testng API Endpoints POST/GET/DELETE ....)

@Test : JUnit, define a test method.

BDD test : Given + When + Then.

@DataJpaTest : to test repository class.

@afterEach : run code after each test.

@BeforeEach : 

@Disabled : disable test for a method.

@Mock : no need to test methods

verify ( mockito ) : verify that 

Generate Test class for class : shift + cmd + T (or) generate -> Test

Use Memory dataBase for Tests (H2):
    > is opensource sql database, used as in-memory database
    > Setup H2 DataBase : 
        >> <dependency>
                <groupId>com.h2database</groupId>
                <artifactId>h2</artifactId>
                <scope>test</scope>
            </dependency>
    > create ressources folder into test folder, with application.properties
        >> see test/ressources/application.properties