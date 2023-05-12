# IDS-project
# Jonathon Vasilak and Joseph Capdevielle

Stage 1 Notes:
The raw data is too big to add to Github and I have not been able to get it to work with Git lfs yet. If you need to see it, the latest version of the data can be found at https://dumps.wikimedia.org/enwikivoyage/latest/enwikivoyage-latest-pages-meta-current.xml.bz2. The version used to train the model was the latest on 4/21/23.

Final Version Notes:
There are two ways to run the code: you can use either main.py or gui.py. main.py runs it with a command line interface of the kind we have been using throughout the semester, while gui.py runs it with a tkinter-based gui.

The model file is also too big to add to Github, similar to the raw data, so we have a script to download it. If you run getmodel.sh, it will download the trained model from Jonathon's Google Drive for you. This should be the only setup necessary.
