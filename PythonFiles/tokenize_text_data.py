import json
import random
import re
import pickle
import tensorflow as tf
import numpy as np

from tensorflow.keras.preprocessing.text import Tokenizer
from tensorflow.keras.preprocessing.sequence import pad_sequences
from tensorflow.keras.utils import to_categorical
from tensorflow.keras.models import Sequential
from tensorflow.keras import backend as K
from tensorflow.keras.layers import Activation, Dense, Dropout, LSTM, Embedding, Conv1D, Masking, Flatten


random.seed(1738)

VOCAB_SIZE = 10000
MAX_TEXT_LEN = 125
OUTPUT_LEN = 200

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

    return new_data

with open('../data/gpt-data.json', 'r') as loaded_data:
  data = json.load(loaded_data)

random.shuffle(data)

# Separate into training/testing/validation
training_data = data[:int(0.85*len(data))]
testing_data = data[int(0.85*len(data)):]

training_data = reprocess(training_data)
testing_data = reprocess(testing_data)

training_texts = [x[0] for x in training_data]
testing_texts = [x[0] for x in testing_data]

training_tags = [x[1] for x in training_data]
testing_tags = [x[1] for x in testing_data]

tokenizer = Tokenizer(num_words=VOCAB_SIZE, oov_token="UNK")
tokenizer.fit_on_texts(training_texts)

tokenizer_output = tokenizer.to_json()
with open('../data/destination_tok.json', 'w', encoding='utf-8') as output:
    output.write(tokenizer_output)


with open('../data/destination_tok.pkl', 'wb', encoding='utf-8') as handle:
   pickle.dump(tokenizer, handle, protocol=pickle.HIGHEST_PROTOCOL)
