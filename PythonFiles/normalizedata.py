import os
import csv


csv.field_size_limit(800000)  # set the field size limit to handle the data


destinations = list()
with open("./../data/data.csv", 'r', encoding='utf-8') as file:
  csvreader = csv.reader(file)
  for row in csvreader:
    destinations.append(row)

len3s = 0
index = 0
for i in destinations:
  if len(i) == 2:
    destinations[index] = [i[0], i[1], 999, 999] # add values that cannot be long/lat values to mark they are missing
  elif len(i) == 3:
    destinations[index] = [i[0] + i[1], i[2], 999, 999] # combine destination name + add values that cannot be long/lat values to mark they are missing
  elif len(i) > 4:
    coords = i[2:]
    latsum = 0
    longsum = 0
    for j in range(0, len(coords), 2):
      latsum += float(coords[j])
      longsum += float(coords[j+1])
    latavg = latsum / (len(coords) / 2)
    longavg = longsum / (len(coords) / 2)
    destinations[index] = [i[0], i[1], latavg, longavg]
  index += 1

with open("../data/data-cleaned.csv",  mode='w', newline='', encoding='utf-8') as outputfile:
  writer = csv.writer(outputfile)
  writer.writerows(destinations)