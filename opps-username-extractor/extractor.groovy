// import groovy.json.*

String filename = args[0]

if (filename.length() == 0) {
  println("Filename required as argument")
  return(0)
}

String fileContents = new File(filename).text

def fileLines = fileContents.split('\n');

def numberLines = fileLines.size();

def stackroxUsername = "";
def stackroxPassword = "";
def stackroxURL = "";
def AWSAccessKey = "";
def AWSSecret = "";
def topLevelDomain = "";
def bastionHostname = "";
def bastionSshUsername = "";
def bastionSshPassword = "";

printf("= Credentials and URLs for Users\n");
printf(":navtitle: Credentials and URLs\n\n");

printf("The information presented below will show the specific URL's and credentials to use for your specific environment.\n\n");

printf("== Application Migration & Modernisation\n\n");

printf("For the Application Migration & Modernisation section you will share a cluster with others performing the workshop.\n\n");

printf ("Your username will be in the format user1, user2 etc. A User ID will be allocated for you by the facilitators.\n\n");

printf("The password to access the cluster is *openshift*\n\n");

printf("To access the cluster select the Application Migration & Modernisation section in the menu displayed in the top left of the screen.\n\n");

printf("Read the instructions in that section and you will find all links that are required to operate the workshop.\n\n");

printf("== Security & Infrastructure\n\n");

printf("For the Security & Infrastructure section you will have a dedicated OpenShift cluster for each user.\n\n");

printf("Information on specfic URL's and credentials is shown below. You will be gven a user number by the facilitators. Locate the appropriate section below and use the credentials and links indicated. It is a good idea to take a copy of the appropriate section for your user ID and store that in a notepad on your laptop for the duration of the workshop.\n\n");

def userCounter = 1;

for (def counter = 0; counter < numberLines; counter++) {
    def line = fileLines[counter]
    /* printf(line + "\n"); */

    if (line.contains("OpenShift Console: ")) {

        def ocpConsole = line.substring("OpenShift Console: ".length());

        def clusterForHeader = ocpConsole.split(/\./);

        def headerURL = "";

        for (headerCounter = 2; headerCounter < clusterForHeader.size(); headerCounter++) {
            headerURL += clusterForHeader[headerCounter];
            headerURL +=".";
        }

        headerURL = headerURL.substring(0, headerURL.length() - 1);

        printf("=== USER %s\n\n", userCounter++);

        printf("==== Workshop web page header links\n\n");

        printf("image::index-01-workshop-introduction-cluster-and-userid.png[Setting cluster and username,800,align=\"center\"]\n\n");

        printf("Enter the information into the two textboxes at the top of page. Remember to press *RETURN* after entering text into each field.\n\n");

        printf("Cluster subdomain : *%s*\n\n", headerURL);

        printf("Project name : *username provided by facilitators*\n\n")

        printf("==== OpenShift cluster information\n\n");

        printf("[cols=\"1,1\"]\n");

        printf("|===\n");

        printf("|OpenShift cluster console URL\n");
        
        

        printf("|%s\n", ocpConsole);

    } else if (line.contains("Login with")) {
  
        // printf("%s\n", line);

        def splitCreds = line.split(' ');

        // printf("%d\n", splitCreds.size());

        def username = splitCreds[2];
        def password = splitCreds[4];

        username = username.substring(1, username.length() - 1);
        password = password.substring(1, password.length() - 1);

        printf("\n\n");

        printf("|Username\n|%s\n\n|Password\n|%s\n", username, password);
        printf("|===\n\n");

    } else if (line.contains("central-stackrox")) {
        stackroxURL = line;

        

    } else if (line.contains("RHACS portal username")) {
        def splitCreds = line.split(' ');

        stackroxUsername = splitCreds[3];
    } else if (line.contains("RHACS portal password")) {
        def splitCreds = line.split(' ');

        stackroxPassword = splitCreds[3];

        printf("==== Advanced Cluster Security console URL\n\n");

        printf("[cols=\"1,1\"]\n");

        printf("|===\n");

        printf("|ACS Console URL\n");
        printf("|%s\n\n", stackroxURL);

        printf("|Username\n|%s\n\n|Password\n|%s\n", stackroxUsername, stackroxPassword);
        printf("|===\n\n");
       

/*    } else if (line.contains("AWS_ACCESS_KEY_ID")) {
        def splitCreds = line.split(' ');

        AWSAccessKey = splitCreds[1];
    
    } else if (line.contains("AWS_SECRET_ACCESS_KEY")) {
        def splitCreds = line.split(' ');

        AWSSecret = splitCreds[1];
      
    } else if (line.contains("Top level domain")) {
        def splitCreds = line.split(' ');

        topLevelDomain = splitCreds[3].substring(1);
 
        printf("==== AWS credentials for cluster creation\n\n");

        printf("[cols=\"1,1\"]\n");

        printf("|===\n");

        printf("|Access key\n|%s\n\n|Secret access key\n|%s\n\n|Top level domain\n|%s\n", AWSAccessKey, AWSSecret, topLevelDomain);
        printf("|===\n\n"); */

    } else if (line.contains("bastion_public_hostname:")) {
        def splitBastionHost = line.split(' ');
        bastionHostname = splitBastionHost[3];

    } else if (line.contains("bastion_ssh_password:")) {
        def splitBastionPassword = line.split(' ');
        bastionSshPassword = splitBastionPassword[3];

    } else if (line.contains("bastion_ssh_user_name:")) {
        def splitBastionUsername = line.split(' ');
        bastionSshUsername = splitBastionUsername[3];

        printf("==== Bastion host URL and credentials\n\n");

        printf("[cols=\"1,1\"]\n");
        printf("|===\n");

        printf("|Bastion host address\n");
        printf("|%s\n\n", bastionHostname);

        printf("|ssh username\n|%s\n\n|ssh password\n|%s\n", bastionSshUsername, bastionSshPassword);
        printf("|===\n\n");


    
    } else if (line.contains("sandboxes")) {
        printf"'''\n\n";

    } else {
        // newSet.add(line);
    }



} 
