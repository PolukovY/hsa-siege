# Getting Started

## Run postgres with pgadmin for database metrics

```
docker-compose up -d
```

## Run the application in docker

```
./mvnw clean install
docker build -t stress-app .   
```


## Run siege -c10 -t2m 127.0.0.1:9000/users

details:
 - cache 1 minutes, refresh after 50 seconds.
 
 
Test result:

```json

{	"transactions":			        8539,
	"availability":			      100.00,
	"elapsed_time":			      119.33,
	"data_transferred":		       19.32,
	"response_time":		        0.14,
	"transaction_rate":		       71.56,
	"throughput":			        0.16,
	"concurrency":			        9.99,
	"successful_transactions":	    8539,
	"failed_transactions":		    0,
	"longest_transaction":		    1.48,
	"shortest_transaction":		    0.01
}
```

## Run siege -c25 -t2m 127.0.0.1:9000/users


Test result:

```json

{	"transactions":			        6558,
	"availability":			      100.00,
	"elapsed_time":			      119.32,
	"data_transferred":		       14.83,
	"response_time":		        0.45,
	"transaction_rate":		       54.96,
	"throughput":			        0.12,
	"concurrency":			       24.94,
	"successful_transactions":	   6558,
	"failed_transactions":		   0,
	"longest_transaction":		   3.74,
	"shortest_transaction":		   0.01
}
```

## Run siege -c50 -t2m 127.0.0.1:9000/users


```
{	"transactions":			        7942,
	"availability":			      100.00,
	"elapsed_time":			      120.00,
	"data_transferred":		       17.97,
	"response_time":		        0.75,
	"transaction_rate":		       66.18,
	"throughput":			        0.15,
	"concurrency":			       49.78,
	"successful_transactions":	   7942,
	"failed_transactions":		   0,
	"longest_transaction":		   4.70,
	"shortest_transaction":		   0.02
}
```

## siege -c100 -t2m 127.0.0.1:9000/users

```
{	"transactions":			        7837,
	"availability":			      100.00,
	"elapsed_time":			      119.98,
	"data_transferred":		       17.73,
	"response_time":		        1.52,
	"transaction_rate":		       65.32,
	"throughput":			        0.15,
	"concurrency":			       99.36,
	"successful_transactions":	   7837,
	"failed_transactions":		   0,
	"longest_transaction":		   5.87,
	"shortest_transaction":		   0.01
}

```


