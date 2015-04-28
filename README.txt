SalesTaxUtility - This utility calculates tax based on input file and generates receipt. After printing receipt it is emailed to the customer. 

Assumptions
1. File will be input to the program. Each Item listing will start from number and end with number.
2. Sales Tax for Books, Medicines and Food will be 0%. For imported (key word) items this will be    5%. Rest all items will have 10% as sales tax.
3. If the sales tax needs some change this can be done in Constants file.
4. Only $ and trailing white spaces will be delimited. No currency printed on receipt.
5. Additionally receipt will be emailed to TO_MAIL_ID as specified in Email.properties. Please ensure before running the program you are connected to internet and able to access google.com (SMTP server) and port 465. 
6. Location of zip is present in Mycart.properties for demo purpose. We will use Google API for getting the Latitude and Longitude of store.
7. These coordinates will be used in tweeting location.
8. Find updated tweets at account https://twitter.com/anurag_sandbox
9. Twitter restricts same tweets multiple times. In that case you can change the input to program. These input files are in InputFile directory.

Description

1. SalesTaxApplication.java - This has the main method. Call Customer for calculating tax and generating receipt.

2. Customer.java - This class creates Product Factory based on input given. This instantiates each product based on category, description,cost,quantity and ifImported. 

ShoppingCart adds all the products and computes the tax and total.

3. ItemCategoryLookup.java - This is a hash map for main products in catalogue like books,food,medicine etc. If the item description matches any part of the hash map then the tax is calculated as specified else the tax is defaulted to 10%.

4. FileParser - This contains logic of parsing the file by taking file path as input. Only accepted file extension is txt file.

5. ReceiptDetails - This takes each product add up the BaseCostTotal along with SalesTaxTotal and displays as sys out. This class also calls Mail.java to email the receipt to the customer.

6. ShoppingCartI and ShoppingCart - Interface and Actual Implementation class respectively.

7. BookItem,ProductItem,MedicalItem,OtherItem extend Product class. Has their own implementation of salesTaxPercentage.

8. AddressConverter.java and TweetUtil.java are util classes for geolocation and twitter API respectively.

8. Junits are present in com.mycart.salestax.tests directory.

FAQ

1. How to run the program?

1.1 - Standalone shell script - Run SalesTax.ksh. You may check the permissions of the script. The path of input files are given in shell script.
   
Anurags-MacBook-Pro-3:ksh anuragb$ ./SalesTax.ksh 
JAVAHOME: 
::../lib/activation.jar:../lib/commons-io-1.1.jar:../lib/commons-io-2.4.jar:../lib/gson-2.2.2.jar:../lib/jackson-core-asl-1.8.5.jar:../lib/jackson-mapper-asl-1.8.5.jar:../lib/mail.jar:../lib/salestax.jar:../lib/twitter4j-core-4.0.3.jar
SalesTaxJob
---------------------------- MyShopping Cart Receipt -----------------------------
1 book : 12.49
1 music cd : 16.49
1 chocolate bar : 0.85

Sales Taxes:	1.50
Total:	29.83

Printed on: Mon Apr 27 23:56:38 PDT 2015
--------------------------Thank You-----------------------------
Email sent to bagga.anurag@gmail.com
 Please check your inbox..
Lattitude of address is :37.371859
Longitude of address is :-122.0212337
Location is APPROXIMATE
Successfully updated the status in Twitter.
---------------------------- MyShopping Cart Receipt -----------------------------
1 imported box of chocolates : 10.50
2 imported bottle of perfume : 108.70

Sales Taxes:	14.70
Total:	119.20

Printed on: Mon Apr 27 23:56:42 PDT 2015
--------------------------Thank You-----------------------------
Email sent to bagga.anurag@gmail.com
 Please check your inbox..
Lattitude of address is :37.371859
Longitude of address is :-122.0212337
Location is APPROXIMATE
GeoLocation{latitude=37.371859, longitude=-122.0212337}
Successfully updated the status in Twitter.
Check https://twitter.com/anurag_sandbox
---------------------------- MyShopping Cart Receipt -----------------------------
1 imported bottle of perfume : 32.19
1 bottle of perfume : 20.89
1 packet of headache pills : 9.75
1 box of imported chocolates : 11.85

Sales Taxes:	6.70
Total:	74.68

Printed on: Mon Apr 27 23:56:46 PDT 2015
--------------------------Thank You-----------------------------
Email sent to bagga.anurag@gmail.com
 Please check your inbox..
Lattitude of address is :37.371859
Longitude of address is :-122.0212337
Location is APPROXIMATE
Successfully updated the status in Twitter.


 1.2 - Import project in Eclipse and run SalesTaxApplication.java with input file as run time argument. You can include the relevant jars in class path.

2. I didn’t get email after running the program?
(A) Please check if you are connected to internet and able to telnet smtp.gmail.com and port 465.
If the program hangs after printing the receipt details check you firewall settings. Sharing sample mail as reference.

3. How to change tax percentage?
(A) Taxes are hardcoded in Constants.java. You may change it accordingly. Better way would have been to store these in database.

4. Does the program take all file extensions?
(A) Currently I have limited the input to .txt format only.

5. Why am i getting mails from mailsender1099@gmail.com. Is it actual gmail account?
(A) Yes, we are using Gmail SMTP server with SSL authentication. Depending on smtp server we can change implementation (with or without authentication). 

6. Why no loggers only sys outs?
(A) For test purpose I have put sys outs. These can be put to log file as well.

7. Can i give multiple file inputs?
(A) No. Only 1 file as input now. 

8. How to change the tweet account?
(A) Twitter credentials i.e consumerKey,  consumerSecKey,accessTokenStr and accessTokenSecretStr are mentioned in Mycart.properties. These need to be changed for respective 
account. If the credentials are valid then you can see the latest tweet.

9. How to change location?
(A) For demo purpose, the store.zip value is kept in Mycart.properties file. This can be changed accordingly.

10. Why not a maven project?
(A) The demo is done as a shell script so included the jars so that it can be run easily. 
