import sys
import json
import tensorflow as tf
from tensorflow.keras.preprocessing.sequence import pad_sequences
from keras.preprocessing.text import Tokenizer, tokenizer_from_json



# Do we want to read a filename from the cli with data, or we can just input the data using the input function for the milestone
# Then allow input with UI for final?
args = sys.argv
args.pop(0)

if len(args) > 0:
    user_input = args[0]
    args.pop(0)
else:
    print("Please enter a description.")
    quit()


longitude = 0
latitude = 0
population = 0
'''
print("Enter Longitude:")
longitude = input()
print("Enter Latitude:")
latitude = input()
print("Enter Population:")
population = input()
'''

model = tf.keras.models.load_model('../data/destinations_lstm.h5')
model.summary()

with open('../data/destination_tok.json', 'r', encoding='utf-8') as tokenFile:
    tokenData = json.load(tokenFile)


output = list()
output.append('<s> ' + user_input)
tokenizer = tokenizer_from_json(json.dumps(tokenData))
for i in range(150):
    tokenized = tokenizer.texts_to_sequences(output)
    outsampd = pad_sequences(outsampd, padding="post", truncating="pre", maxlen=125)

    print(output)

    pred = model.predict(outsampd)

    pred = pred.argmax(axis=1)[0]

    output[0] = output[0] + ' ' + tokenizer.index_word[pred]
