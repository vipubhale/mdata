node {
    git([url: 'https://github.com/vipubhale/mdata', branch: 'AddingTesting'])
      def mvnHome = tool 'maven_3.3.9'
        sh "${mvnHome}/bin/mvn -B verify"
}
