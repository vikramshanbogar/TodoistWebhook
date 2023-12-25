# TodoistWebhook
 
- This is a simple Application which uses Todoist application's **webhook** to fetch the events. Which can be used for event based architecture in the later stages.
 - Currently it received the data from todoist and pushes it to AWS SQS.
 - In the later projects we will consume this SQS messages using AWS Lambda to process and save it to Dynamo db for archiving and send email(AWS SES) to user everyday EOD as a Day's review of Todoist activity.
-  Updated with dockerfile to reuse 
  - docker run -p 80:8080 todoistwebhook_v1


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