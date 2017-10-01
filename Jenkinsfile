node {
    git([url: 'https://github.com/vipubhale/mdata', branch: 'AddingTesting'])
      def mvnHome = tool 'maven_3.3.9'
        sh "${mvnHome}/bin/mvn -Dtest=aug.manas.mtconnect.mdata.unit.UTestMDataService test"
        sh "${mvnHome}/bin/mvn -Dtest=aug.manas.mtconnect.mdata.itest.ITestMDataController test"
        sh "${mvnHome}/bin/mvn clean package"
      
}
