# APIRateLimitDemo
implement HTTP service, according to the API requirements

Instructions to run 

1) edit the location of the cvs file in your file system inside project configuration file (\WebContent\WEB-INF\classes\config.properties)

2) In the same file you can configure rateLimit and suspendTime.

3) build the project using mvn clean install command

4) copy the war file and deploy 

5) call http://localhost:8080/AgodaDemo2-1.0/services/keys/generateKey to get the key first.

6) call http://localhost:8080/AgodaDemo2-1.0/services/city/Amsterdam/ASC/aeed1655-04af-4df1-b917-ff37f4350f77 to get the list of details according to the city

NOTE : Format of the webservice call
http://localhost:8080/AgodaDemo2-1.0/services/city/CityName/SortBy/Key
