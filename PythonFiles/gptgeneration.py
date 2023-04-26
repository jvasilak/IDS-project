import os
import openai
import requests
import csv
import time
import json

REQUEST_TEXT = "Describe this destination in 5 sentences:\n" # What we are asking GPT-3.5
REQUEST_MAX_TOKENS = 200 # Passed to openai requests
TOTAL_ITERATIONS =  50000 # How many times the code loops before it writes and ends

csv.field_size_limit(800000)  # set the field size limit to handle the data


destinations = list()
with open("./../data/data-cleaned.csv", 'r', encoding='utf-8') as file:
  csvreader = csv.reader(file)
  for row in csvreader:
    destinations.append(row)

# open data file to append to

with open('../data/gpt-data.json', 'r') as loaded_data:
  data = json.load(loaded_data)

with open('../data/too-long.json', 'r') as loaded_toolong:
  too_long_existing = json.load(loaded_toolong)

# Connect to openai
with open('../key.txt', 'r') as file:
  content = file.read()
SECRET_KEY = content
#openai.api_key = SECRET_KEY
openai.api_key = "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"

responses = list()
too_long = list()
permin = 0
iterations = 0
for i in destinations[len(data):]:
  try:
    description = i[1]
    response = {}
    response["name"] = i[0]
    response["latitude"] = i[2]
    response["longitude"] = i[3]
    complete_prompt = REQUEST_TEXT + description
    print(complete_prompt)
    print(len(complete_prompt))
    if len(complete_prompt) > 4500:
      response["description"] = description
      too_long.append(response)
    else:
      api_response = openai.Completion.create(engine="text-ada-001", prompt=complete_prompt, temperature=1, max_tokens=REQUEST_MAX_TOKENS, top_p=0.5)
      response["description"] = api_response["choices"][0]["text"]
      responses.append(response)
      time.sleep(1.5)
    iterations += 1
    if iterations > TOTAL_ITERATIONS:
      break
  except Exception as e:
    print("Ran out of money :(")
    print(e)
    break

combined_list = data + responses
too_long_combined = too_long_existing + too_long
#too_long_combined = too_long
#combined_list = responses
with open("../data/gpt-data.json",  mode='w', newline='', encoding='utf-8') as outputfile:
  json.dump(combined_list, outputfile)

with open("../data/too-long.json",  mode='w', newline='', encoding='utf-8') as toolongoutputfile:
  json.dump(too_long_combined, toolongoutputfile)