import json
import random

random.seed(1738)

def reprocess(data):
    for i in data:
       description = i["description"]
       
    return data

with open('../data/gpt-data.json', 'r') as loaded_data:
  data = json.load(loaded_data)

print(type(data))
random.shuffle(data)

# Separate into training/testing/validation, using 85, 14, 1 split now
training_data = data[:int(0.85*len(data))]
testing_data = data[int(0.85*len(data)):int(0.99*len(data))]
validation_data = data[int(0.99*len(data))]

