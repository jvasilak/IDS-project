import json
import random
import re
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
       #data[i]["description"] = description

    return new_data

with open('../data/gpt-data.json', 'r') as loaded_data:
  data = json.load(loaded_data)

random.shuffle(data)

# Separate into training/testing/validation
training_data = data[:int(0.85*len(data))]
testing_data = data[int(0.85*len(data)):]
#testing_data = data[int(0.85*len(data)):int(0.99*len(data))]
#validation_data = data[int(0.99*len(data)):]

training_data = reprocess(training_data)
testing_data = reprocess(testing_data)
#validation_data = reprocess(validation_data)

training_texts = [x[0] for x in training_data]
testing_texts = [x[0] for x in testing_data]
#validation_texts = [x[0] for x in validation_data]

training_tags = [x[1] for x in training_data]
testing_tags = [x[1] for x in testing_data]
#validation_tags = [x[1] for x in validation_data]

tokenizer = Tokenizer(num_words=VOCAB_SIZE, oov_token="UNK")
tokenizer.fit_on_texts(training_texts)

tokenizer_output = tokenizer.to_json()
with open('../data/destination_tok.json', 'w', encoding='utf-8') as output:
    output.write(tokenizer_output)

num_labels = len(tokenizer.word_index) + 1

x_train = tokenizer.texts_to_sequences(training_texts)
x_test = tokenizer.texts_to_sequences(testing_texts)
#x_validation = tokenizer.texts_to_sequences(validation_texts)

x_train = pad_sequences(x_train, padding="post", truncating="pre", maxlen=MAX_TEXT_LEN)
x_test = pad_sequences(x_test, padding="post", truncating="pre", maxlen=MAX_TEXT_LEN)
#x_validation = pad_sequences(x_validation, padding="post", truncating="pre", maxlen=100)

y_train = list()
for t in training_tags:
   try:
      t = tokenizer.word_index[t]
   except KeyError:
      t = tokenizer.word_index['UNK']
   t = to_categorical(t, num_classes=num_labels)
   y_train.append(t)
y_train = np.asarray(y_train)

y_test = list()
for t in testing_tags:
   try:
      t = tokenizer.word_index[t]
   except KeyError:
      t = tokenizer.word_index['UNK']
   t = to_categorical(t, num_classes=num_labels)
   y_test.append(t)
y_test = np.asarray(y_test)
'''
y_validation = list()
for t in validation_tags:
   try:
      t = tokenizer.word_index[t]
   except KeyError:
      t = tokenizer.word_index['UNK']
   t = to_categorical(t, num_classes=num_labels)
   y_validation.append(t)
y_validation = np.asarray(y_validation)
'''
model = Sequential()

model.add(Embedding(VOCAB_SIZE, OUTPUT_LEN, input_length=MAX_TEXT_LEN))
model.add(Masking())
model.add(LSTM(OUTPUT_LEN, return_sequences=True))
model.add(Flatten())
model.add(Dropout(0.2))
model.add(Dense(num_labels, activation='softmax'))

model.summary()

model.compile(loss='categorical_crossentropy',
            optimizer='adam',
            metrics=['accuracy'])

K.set_value(model.optimizer.learning_rate, 0.001)
batch_size = 100
history = model.fit(x_train, y_train,
                  batch_size=batch_size,
                  epochs=4,
                  verbose=1,
                  validation_split=0.01)

model.save('../data/destinations_lstm.h5')