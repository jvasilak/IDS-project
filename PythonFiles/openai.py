import os
import openai
import requests
import csv

REQUEST_TEXT = "Make this description 5 sentences:\n" # What we are asking GPT-3.5
REQUEST_MAX_TOKENS = 100 # Passed to openai requests

csv.field_size_limit(800000)  # set the field size limit to handle the data


destinations = list()
with open("./../data/data.csv", 'r', encoding='utf-8') as file:
  csvreader = csv.reader(file)
  for row in csvreader:
    destinations.append(row)



# Connect to openai
SECRET_KEY_ND = "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"
SECRET_KEY_PERSONAL = "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"
openai.api_key = SECRET_KEY_PERSONAL

response = openai.Completion.create(model="gpt-3.5-turbo", prompt="Say this is a test", temperature=0, max_tokens=7)
