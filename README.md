# MDataDashboard 

This application is a dashboard for MTConnect® stream Agent that connects with MTConnect® Adapter for Fanuc CNC machines. 

It acts as dashboard and invokes the endpoint of MTConnect® stream Agent to get the current state from all adapaters registered with it. In turn those adapaters poll the information from the CNC. 
Currently this application supports the data from CNC controls specific to Fanuc having Fanuc Focas 0id enabled*.
 
\* Fanuc(0iTF essentially 0id) CNC machines(compatible with any version 30i) are currently compatible with this application. 

![MdataDashboard](dashboard.png)

### Features
- Serves number of machines served by agent.
- Dashboard shows few attributes such as name , id , part count, servo speed, etc.
- Easy to use and maintain.


### Data Storage 
**MTConnect Agents by themselves are not storage applications.
This is made clear in the MTConnect Standard. Instead the purpose of MTConnect Agents is to serve data to client applications when requested. While the Agent does keep a small buffer, this buffer is not intended to be used for data storage but rather to retain data between connection interruptions. MDataDashboard fulfills the role of requesting this data and then represents the data in tabular form on browser. This application doesn't store the data from agent in any database as of now. 

### Installation

To install this dashboard you would need maven and java 1.8 installed on your machine. 

* Download the code from github 

    ``` git
    git clone https://github.com/vipubhale/mdata.git
    ```
* In case your agent is running on different machine modify the src/main/resources/config/application-prod.properties as below:
	```
    agent.endpoint=http://localhost:5020/current   #change localhost:5020 to domain or ip:port combination     
    ```
    If you are testing application on local using SOAP UI keep the properties file as it is
* Do the maven build
	``` maven
    mvn clean package
    ```
* This will create a zip file in the target directory of your workspace
	``` sh
    cp mdata-0.0.8-beta-distribution.zip
    unzip mdata-0.0.8-beta-distribution.zip
    ```
* Zip file contains 2 directories 
    * unix
    	* Inside this directory run run.sh
    	``` sh
        mdata-0.0.8-beta/bin/run.sh 		
        ```
        \**assuming after unzipping directory is not changed*

    * windows
       * After unzipping copy the directory mdata-0.0.8 to C:/Program Files. So directory would be c:\Program Files\mdata-0.0.8-beta.
	   * Open command prompt(as administrator) and issue below commands:
          ``` bat
          cd c:\Program Files\mdata-0.0.8-beta\windows\
          mdata.exe install 
          ```
It will install the job as a service. Once the service is started, mdata.out will have system out logs for java app, mdata.err will have system error logs for java app.

* For more advanced users - use the **Jenkinsfile** in the repository to integrate with Jenkins and do automated builds.The Jenkinsfile has sample steps it can be enhanced as per use.  

### Testing locally
If you want to evaluate this application, you can do the testing with SOAP UI as backend instead of agent.

* Install SOAP UI
* Import the project file **dashboard-soapui-project.xml** from this repository. 
* Run the mock service
	* Open/expand the project mdata-mock-agent in SOAP UI
	* Righclick on mock service mdata-mock-service and select 'start minimized'
	* Ensure in browser http://localhost:5020/current gives an xml
* Run the application as per the Install section mentiond above.

### Enhancement for future
* Support for multiple attributes rather than supported right now for Fanuc Facas.
* UI enhancement for search capabilities.
* Rearchitecting the whole flow and including database to persist the data for analytics purpose in future.

Any feedback on this is appreciated. Also please report bugs in issues section here. 

Happy information driven manufacturing :+1:
