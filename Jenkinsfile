pipeline{
	agent any
	
	stages{
		stage ('Compile Stage'){
		
			steps {
				withMaven(maven : 'MAVEN_HOME'){
					sh 'mvn clean compile'
				}
			}
		}
		
		stage ('Testing Stage'){
			steps {
					withMaven(maven : 'MAVEN_HOME'){
						sh 'mvn clean test -P regression'
					}
				}
			
		}
		
	}

}