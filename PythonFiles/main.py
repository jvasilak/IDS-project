import sys
import json
import tensorflow as tf
from tensorflow.keras.preprocessing.sequence import pad_sequences
from keras.preprocessing.text import Tokenizer, tokenizer_from_json
import pickle


# Do we want to read a filename from the cli with data, or we can just input the data using the input function for the milestone
# Then allow input with UI for final?

model = tf.keras.models.load_model('../data/destinations_lstm.h5')
model.summary()
with open('../data/destination_tok.pkl', 'rb') as tokenizer_file:
   tokenizer = pickle.load(tokenizer_file) 
user_input = input("Describe a destination: ")
output = list()
output.append('<s> ' + user_input)
consecutive_unks = 0
for i in range(150):
    tokenized = tokenizer.texts_to_sequences(output)
    outsampd = pad_sequences(tokenized, padding="post", truncating="pre", maxlen=125)

    print(output)

    pred = model.predict(outsampd)

    pred = pred.argmax(axis=1)[0]
    if tokenizer.index_word[pred] == "UNK":
        consecutive_unks += 1
    else:
        consecutive_unks = 0
    if consecutive_unks >= 3:
        break

    output[0] = output[0] + ' ' + tokenizer.index_word[pred]
output = output[0][4:]
print(output)