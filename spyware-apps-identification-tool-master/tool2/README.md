## Dataset generation

The tool comes with a set of machine-learning classifiers trained over the network traffic collected from the following apps: Dropbox, Google Photos, Cerberus, MSpy, TSpy.
The flowDatasetMaker program generates a properly formatted testing dataset starting from a set of newly collected pcap files.
At the end of its execution, the flowDatasetMaker program should have generated the dataset.csv file, which will be given in input to the classifiers. Those will then classify the network traffic received as input.

### Usage
./flowDatasetMaker

### Notes
For testing purposes, the flowDatasetMaker program comes with a set of pcap files which can be used for generating the testing dataset.


## Network traffic
The nestedCV program is designated to classify the newly collected traffic.

## Usage

1. Name the dataset.csv as TestDataset.csv

2. Paste the dataset inside the traffic_analyzer folder, with all the other files.

3. Type the following command: ./nestedCV [postman_id] [postman_name] [username] [password] [case_id] [classifier] [--randomize]


Here, Classifier options are:

rf - Random Forest

knn - K-nn

svm - SVM

lr - Logistic Regression


Randomize need not be typed if shuffling the data is unnecessary.

An example: ./nestedCV id name uname pwd case knn --randomize r

The above command runs a Randomized K-nn.


Another example: ./nestedCV id name uname pwd case svm

The above command runs an SVM classifier with no Randomization on the given dataset.


4. The output is displayed in the terminal. A dataset output file is also created in the folder.

## Logging

The date and time of each run, along with randomization details, is stored in logfile.log
