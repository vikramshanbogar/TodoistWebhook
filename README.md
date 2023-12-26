# TodoistWebhook
 
## Module 1:- EventWriterToSQS
 
- This is a simple Application which uses Todoist application's **webhook** to fetch the events. Which can be used for event based architecture in the later stages.
 - Currently it received the data from todoist and pushes it to AWS SQS.
 - In the later projects we will consume this SQS messages using AWS Lambda to process and save it to Dynamo db for archiving and send email(AWS SES) to user everyday EOD as a Day's review of Todoist activity.
-  Updated with dockerfile to reuse 
  - docker run -p 80:8080 todoistwebhook_v1
- docker image: **vikramsvk1/todoistwebhook_v1:latest**

 Steps :-
 1. Register the application in Todoist App registeration page with webhook information and the type of Events expected.(I have selected only item created/updated/completed)
 2. It expects a proper url with port 80, dev localhost:8080 wont work

    I have deploed the app in ec2 and configured the port using the below steps:-

        HOW CAN I RUN A SPRING BOOT APPLICATION ON PORT 80-SPRINGBOOT

        sudo apt-get install apache2

        run with sudo rights:-

        a2enmod proxy
        a2enmod proxy_http   


        cd /etc/apache2/sites-enabled
        sudo nano 000-default.conf

        Edit the file

        <virtualhost *:80>

            proxypreservehost on

            # ...

            proxypass / http://localhost:8080/
        </virtualhost>

        finally
        sudo service apache2 restart

        Ref:- https://stackoverflow.com/questions/33703965/how-can-i-run-a-spring-boot-application-on-port-80
		

## Module 2: SQSReaderWriteToDynamoDb

        aws dynamodb  describe-table --table-name TodoistEvents
        {
            "Table": {
                "AttributeDefinitions": [
                    {
                        "AttributeName": "event_name",
                        "AttributeType": "S"
                    },
                    {
                        "AttributeName": "itemId",
                        "AttributeType": "S"
                    }
                ],
                "TableName": "TodoistEvents",
                "KeySchema": [
                    {
                        "AttributeName": "itemId",
                        "KeyType": "HASH"                                                                                                                                                                    
                    },
                    {
                        "AttributeName": "event_name",
                        "KeyType": "RANGE"
                    }
                ],
                "TableStatus": "ACTIVE",
                "CreationDateTime": "2023-12-26T12:29:19.450000+05:30",
                "ProvisionedThroughput": {
                    "LastDecreaseDateTime": "2023-12-26T12:31:20.193000+05:30",                                                                                                                              
                    "NumberOfDecreasesToday": 2,                                                                                                                                                             
                    "ReadCapacityUnits": 1,                                                                                                                                                                  
                    "WriteCapacityUnits": 1
                },
                "TableSizeBytes": 0,
                "ItemCount": 0,
                "TableArn": "arn:aws:dynamodb:ap-south-1:309305317617:table/TodoistEvents",
                "TableId": "dedf15e1-b6c3-42aa-b8de-b82509447ba8",
                "TableClassSummary": {
                    "TableClass": "STANDARD"
                },
                "DeletionProtectionEnabled": false
            }
        }

