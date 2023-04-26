import json
import random
import re
import tensorflow as tf

from tensorflow.keras.preprocessing.text import Tokenizer

random.seed(1738)

VOCAB_SIZE = 10000

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
       description = description.strip()
       words = description.split(' ')
       words.insert(0, '<s>')
       words.append('<e>')
       for i in range(len(words)-1):
          ne = ' '.join(words[:i+1])
          nw = words[i+1]
          new_data.append((ne, nw))
       #data[i]["description"] = description

    return new_data

with open('../data/gpt-data.json', 'r') as loaded_data:
  data = json.load(loaded_data)

random.shuffle(data)

# Separate into training/testing/validation, using 85, 14, 1 split now
training_data = data[:int(0.85*len(data))]
testing_data = data[int(0.85*len(data)):int(0.99*len(data))]
validation_data = data[int(0.99*len(data)):]

training_data = reprocess(training_data)
testing_data = reprocess(testing_data)
validation_data = reprocess(validation_data)

training_texts = [x[0] for x in training_data]
testing_texts = [x[0] for x in testing_data]
validation_texts = [x[0] for x in validation_data]

training_tags = [x[1] for x in training_data]
testing_tags = [x[1] for x in testing_data]
validation_tags = [x[1] for x in validation_data]

tokenizer = Tokenizer(num_words=VOCAB_SIZE, oov_token="UNK")
tokenizer.fit_on_texts(training_texts)