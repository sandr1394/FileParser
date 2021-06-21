# FileParser

service for parsing csv and json files

### How to launch
cd to root of project. Execute ```mvn install```. Then put csv or json files in the same directory as jar of project is located.
Execute ```java -jar jarfilename filename1.extension filename2.extension```

### Input
##### CSV file:
```sh
1,100,USD,оплата заказа
2,123,EUR,оплата заказа
```

##### JSON file:
```sh
{"orderId":5,"amount":1.23,"currency":"USD","comment":"оплата заказа"}
{"orderId":6,"amount":1.24,"currency":"EUR","comment":"оплата заказа"}
```

### Output

```sh
{"id":2,"orderId":"6","amount":"1.24","comment":"оплата заказа","fileName":"orders2.json","lineNumber":"2","result":"OK"}
{"id":1,"orderId":"5","amount":"1.23","comment":"оплата заказа","fileName":"orders2.json","lineNumber":"1","result":"OK"}
{"id":3,"orderId":"1","amount":"100","comment":"оплата заказа","fileName":"orders1.csv","lineNumber":"1","result":"OK"}
{"id":4,"orderId":"2","amount":"123","comment":"оплата заказа","fileName":"orders1.csv","lineNumber":"2","result":"OK"}
```
