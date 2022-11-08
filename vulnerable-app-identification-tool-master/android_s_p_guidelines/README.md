## Usage

To decompile an app APK file, save the APK under the android_s_p_guidelines/server/apps_source_code/ directory. 

From the root of the project, launch: 

```
./apk2src/jadx/bin/jadx ./server/apps_source_code/<apk_file_name> -d ./server/apps_source_code/<output_directory_name>
```

To analyse the app, go to the `server/` directory and run:

```
python3 Scan.py -s <output_directory_name> [-r RULES [RULES ...]] [-t TIMEOUT] [-g] [-q] [-D DATABASE]
```

where:

* `-s` : source code or apk path (*required*)
* `-r` : rules to be used for the analysis
* `-t` : set a timeout value
* `-g` : display good practices
* `-q` : quiet mode
* `-D` : specify the name of a *MongoDB* database where to store the analysis results; data is stored in the following two collections:
	* *rawoutput*: it contains the raw output from the analysis. This is for precision (true/false positive) plots.
	* *rulestats*: it contains the time spent for each run and for each APK. This is used for performances evaluation.
