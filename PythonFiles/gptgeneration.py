import os
import openai
import requests
import csv
import json

REQUEST_TEXT = "Describe this destination in 5 sentences:\n" # What we are asking GPT-3.5
REQUEST_MAX_TOKENS = 200 # Passed to openai requests

csv.field_size_limit(800000)  # set the field size limit to handle the data


destinations = list()
with open("./../data/data-cleaned.csv", 'r', encoding='utf-8') as file:
  csvreader = csv.reader(file)
  for row in csvreader:
    destinations.append(row)



# Connect to openai
with open('../key.txt', 'r') as file:
  content = file.read()
SECRET_KEY = content
openai.api_key = SECRET_KEY


responses = list()
for i in destinations[1:5]:
  try:
    description = i[1]
    response = {}
    response["name"] = i[0]
    response["latitude"] = i[2]
    response["longitude"] = i[3]
    complete_prompt = REQUEST_TEXT + description
    api_response = openai.Completion.create(engine="text-ada-001", prompt=complete_prompt, temperature=1, max_tokens=REQUEST_MAX_TOKENS, top_p=0.5)
    response["description"] = api_response["choices"][0]["text"]
    responses.append(response)
  except Exception as e:
    print("Ran out of money :(")
    print(e)
    break


with open("../data/gpt-data.json",  mode='w', newline='', encoding='utf-8') as outputfile:
  json.dump(responses, outputfile)

