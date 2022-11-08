pyinstaller nestedCV.py --hidden-import sklearn.neighbors._typedefs --hidden-import sklearn.utils._weight_vector --hidden-import sklearn.neighbors._quad_tree


## Usage

1. Name your data-set as: TestDataset.csv

2. Paste the data-set inside the nestedCV Folder, with all the other files.

3. Right-click in the folder and click Open in Terminal.

4. Type the following command: ./nestedCV [postman_id] [postman_name] [username] [password] [case_id] [classifier] [--randomize]


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


5. Hit Enter and let it run.

6. The output is displayed in the terminal. A dataset output file is also created in the folder.

## Logging

The date and time of each run, along with randomization details, is stored in logfile.log
