import json
import random
import re

random.seed(1738)

def reprocess(data):
    new_data = list()
    for i, entry in enumerate(data):
       description = entry["description"]
       description = description.strip()
       regex = re.compile('[^a-zA-Z]')
       description = regex.sub(' ', description)
       regex = re.compile(' +')
       description = regex.sub(' ', description)
       description = description.lower()
       description = "<s> " + description + "<e>"
       data[i]["description"] = description

    return data

with open('../data/gpt-data.json', 'r') as loaded_data:
  data = json.load(loaded_data)

random.shuffle(data)
data = reprocess(data)

# Separate into training/testing/validation, using 85, 14, 1 split now
training_data = data[:int(0.85*len(data))]
testing_data = data[int(0.85*len(data)):int(0.99*len(data))]
validation_data = data[int(0.99*len(data)):]

